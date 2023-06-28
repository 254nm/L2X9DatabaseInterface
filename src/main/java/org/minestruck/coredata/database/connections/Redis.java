package org.minestruck.coredata.database.connections;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.minestruck.coredata.MCServer;
import org.minestruck.coredata.database.Database;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Getter
@Accessors(fluent = true)
public class Redis implements Database {
    private MCServer server;
    private JedisPool pool;

    public Redis(String hostname, int port, String user, String password, MCServer server) {
        try {
            this.pool = new JedisPool(hostname, port, user, password);
            this.server = server;
        } catch (Throwable t) {
            server.logger().error("Failed to setup Redis", t);
            server.shutdownServer();
        }
    }

    private Jedis getResource() {
        try (Jedis jedis = this.pool.getResource()) {
            return jedis;
        } catch (Throwable t) {
            server.logger().error("Failed to get resource from pool", t);
            server.shutdownServer();
            return null;
        }
    }

    public void set(String key, String value) {
        this.getResource().set(key, value);
    }

    public void unset(String key, String value) {
        this.getResource().del(key);
    }

    public String get(String key) {
        return this.getResource().get(key);
    }

    public void publish(String channel, byte[] data) {
        this.getResource().publish(channel.getBytes(StandardCharsets.UTF_8), data);
    }

    public void publish(String channel, ByteBuffer data) {
        this.publish(channel, data.array());
    }

    public AutoCloseable subscribe(List<String> channels, BiConsumer<String, ByteBuffer> callback) {
        byte[][] channelsBytes = channels.stream().map(String::getBytes).toList().toArray(new byte[0][0]);

        BinaryJedisPubSub sub = new BinaryJedisPubSub() {
            @Override
            public void onMessage(byte[] channel, byte[] message) {
                callback.accept(new String(channel, StandardCharsets.UTF_8), ByteBuffer.wrap(message));
            }
        };

        CompletableFuture.runAsync(() -> {
            try {
                this.getResource().subscribe(sub, channelsBytes);
            } catch (Throwable t) {
                server.logger().error("Failed to subscribe to redis channels", t);
                server.shutdownServer();
            }
        });

        return () -> sub.unsubscribe(channelsBytes);
    }

    @Override
    public void close() throws Exception {
        if (this.pool != null && !this.pool.isClosed()) {
            this.pool.close();
        }
    }
}
