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
开启增强动画时的流畅度优化、打开动画因Navigation而差异的优化、继续优化封装成库...

### 演示
![截图](/src/shot.jpg)

### 实用 在[APP](https://github.com/Chiu-xaH/HFUT-Schedule)上的效果(暂时阉割模糊、缩放、阴影等)

![截图](/src/HFUT-Schedule%20Sample.jpg)

### 模块 :transition Import后。配置 TransitionState
```Kotlin
// 控制参数
object TransitionState {
    // 是否使用透明背景 无需改
    var transplantBackground = false
    // 从此处修改动画曲线
    @OptIn(ExperimentalSharedTransitionApi::class)
    val curveStyle = TransitionCurveStyle()
    // 从此处修改背景模糊/缩放情况
    val transitionBackgroundStyle = TransitionBackgroundStyle()
    // 应用启动开屏第一页的Route 不配置会导致第一次进入APP时需要等待speedMs
    var firstStartRoute : String? = null
    // 是否完成第一次启动 无需改
    var started = false
}

// 动画曲线 boundsTransform决定运动曲线 speedMs决定时长
@OptIn(ExperimentalSharedTransitionApi::class)
data class TransitionCurveStyle(
    var boundsTransform : BoundsTransform = DefaultTransitionStyle.defaultBoundsTransform,
    var speedMs : Int = DefaultTransitionStyle.DEFAULT_ANIMATION_SPEED
)

data class TransitionBackgroundStyle (
    var motionBlur : Boolean = true,
    var forceTransition : Boolean = true,
    var blurRadius : Dp = 10.dp,
    var backgroundColor : Color = Color.Black.copy(.5f),
    var scaleValue : Float = 0.875f
)
```