# Transition Sample On Compose Multiplatform

使用Compose Multiplatform创建的一个转场动画过渡示例

## 平台
Wasm(JS)、Desktop(JVM)、iOS、Android

## 命令
```bash
./gradlew wasmJsBrowserDevelopmentRun -t # Wasm Run In Development
./gradlew wasmJsBrowserProductRun -t # Wasm Run In Product
./gradlew packageDistributionForCurrentOS # 打包
./gradlew run # Desktop Run
```

## 计划
开启增强动画时的流畅度优化、打开动画因Navigation而差异的优化、封装成库...

### 演示
![截图](/src/shot.jpg)