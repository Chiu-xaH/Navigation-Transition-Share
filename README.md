# Navigation-Transition-Share

使用Compose Multiplatform对Navigation和Modifier.shareElement/shareBounds封装创建的一个容器过渡转场动画，支持在NavHost下无限打开N级界面，有四档背景特效调节(模糊、缩放、压暗)

## 平台
Wasm(JS)、Desktop(JVM)、iOS、Android

## 命令
```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun  # Wasm Run In Development
./gradlew :composeApp:wasmJsBrowserProductRun  # Wasm Run In Product
./gradlew :composeApp:wasmJsBrowserDistribution # Wasm打包
./gradlew :composeApp:packageDistributionForCurrentOS # 打包
./gradlew :composeApp:run --no-configuration-cache # Jvm/Desktop Run
./gradlew :composeApp:jsBrowserDevelopmentRun  # Wasm Run In Development
./gradlew :composeApp:jsBrowserProductRun  # Wasm Run In Product
./gradlew jsBrowserDistribution # Js打包
```

## 计划
开启增强动画时的流畅度优化、打开动画因Navigation而差异的优化、继续优化封装成库...

### 演示
![截图](/src/shot.jpg)


### Import 模块 :transition
[具体使用案例](https://github.com/Chiu-xaH/HFUT-Schedule)

![截图](/src/shot2.jpg)