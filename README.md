# Animation Sample
使用Kotlin Multiplatform(KMP)和 Compose Multiplatform(CMP)创建的一个转场动画过渡示例，用于测试自用，然后投入生产环境

## 平台
Wasm(JS)、Desktop(JVM)、iOS、Android

## 命令
```bash
./gradlew wasmJsBrowserDevelopmentRun -t # Wasm Run In Development
./gradlew wasmJsBrowserProductRun -t # Wasm Run In Product
./gradlew packageDistributionForCurrentOS # 打包
./gradlew run # Desktop Run
```


### 演示
![截图](/src/shot.jpg)