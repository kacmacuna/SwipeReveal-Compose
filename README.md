# SwipeReveal-Compose

A layout that you can swipe to show action buttons.

## Getting Started

you need to have Android Studio 4.0 and Jetpack-Compose focused project.

### Gradle setup

Step 1. Add the JitPack repository to your build file
```
maven { url 'https://jitpack.io' }
```
Step 2. Add the dependency to your app level build file
```
implementation 'com.github.kacmacuna:SwipeReveal-Compose:0.1.4'
```

### Quickstart
Call SwipeReveal in your @Composable annotated method. and pass height and action buttons you want to show when your view is swiped.
```
@Composable
fun FirstSwipable() {
    Column {
        val current = ContextAmbient.current
        val swipeActionButtons = listOf(
            swipeActionButton {
                name = "Delete"
                textColorRes = R.color.colorAccent
                color = Color.Red
                onClick = {
                    Toast.makeText(current, name, Toast.LENGTH_SHORT).show()
                }
            }
        )
        val squareHeight = 100.dp
        SwipeReveal(squareHeight, swipeActionButtons) {
            Box(
                gravity = ContentGravity.Center,
                modifier = Modifier
                    .preferredHeight(squareHeight)
                    .fillMaxWidth()
                    .drawBackground(Color.White)
            ) {
                Text(
                    text = "Cool service but not as cool as me",
                    style = TextStyle(color = Color.Black, fontSize = 18.sp)
                )
            }
        }
    }
}
```

