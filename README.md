# Transition Sample On Compose Multiplatform

使用Compose Multiplatform创建的一个转场动画示例

## 平台
Wasm(JS)、Desktop(JVM)、iOS、Android

## 命令
```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun  # Wasm Run In Development
./gradlew :composeApp:wasmJsBrowserProductRun  # Wasm Run In Product
./gradlew :composeApp:packageDistributionForCurrentOS # 打包
./gradlew :composeApp:wasmJsBrowserDistribution # 以Web打包
./gradlew :composeApp:run --no-configuration-cache # Jvm/Desktop Run
```

## 计划
开启增强动画时的流畅度优化、打开动画因Navigation而差异的优化、继续优化封装成库...

### 演示
![截图](/src/shot.jpg)

### Import 模块 :transition 即可食用 [具体使用案例](https://github.com/Chiu-xaH/HFUT-Schedule)