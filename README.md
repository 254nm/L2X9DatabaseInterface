# Adding as a dependency

### Gradle (KTS)
```kotlin
repositories {
    maven { url = uri("https://repo.txmc.me/releases") }
}

dependencies {
    compileOnly("org.minestruck:coredata:1.0-SNAPSHOT")
}
```
### plugin.yml
```yaml
depend:
  - MinestruckCoreData
```