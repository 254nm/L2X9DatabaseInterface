package me.l2x9.data.platform;

import me.l2x9.data.config.DataConfig;
import org.slf4j.Logger;

/**
 * @author 254n_m
 * @since 2023/06/27 9:30 PM
 * This file was created as a part of CoreData
 */
public interface MCServer {
    <S> S getHandle();

    void shutdown();
    Logger logger();
    DataConfig config();

}
