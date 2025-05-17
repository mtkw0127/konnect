# Konnect

**Konnect** is a Jetpack Compose library that enables drawing visual connections between
composables — with support for lines, arrows, and more.

---

## ✨ Features

- 🔗 Connect arbitrary composables with lines or arrows
- 🎯 Anchor lines to specific positions (topStart, center, bottomEnd, etc.)
- 🎨 Style connections with color and stroke width
- 🔄 Supports recomposition and state save/restore

---

## 📦 Installation

(Coming soon on MavenCentral)

```kotlin
dependencies {
    implementation("io.github.mtkw.konnect:konnect:<version>")
}
```

---

## 🚀 Usage

### 1. Create a `KonnectState`

```kotlin
val konnectState = rememberKonnectState(
    style = KonnectStyle.Line.Default
)
```

Or for arrow-style:

```kotlin
val konnectState = rememberKonnectState(
    style = KonnectStyle.Arrow.Default
)
```

---

### 2. Attach `Modifier.konnect()` to each target

```kotlin
Box(
    modifier = Modifier
        .konnect(konnectState, anchor = RectAnchor.Center)
)
```

Attach this to at least two composables you want to connect.

---

### 3. Draw connection using `Modifier.drawKonnect()`

```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .drawKonnect(konnectState)
)
```

This draws the visual connections on top of the layout.

---

## 🧩 Anchors

Use `RectAnchor` to specify where the line connects to:

```kotlin
RectAnchor.Center
RectAnchor.TopStart
RectAnchor.TopEnd
RectAnchor.BottomStart
RectAnchor.BottomEnd
...
```

---

## 🎨 Styles

Currently supported styles:

- `KonnectStyle.Line` – simple line
- `KonnectStyle.Arrow` – directional arrow

The arrow direction can be customized (e.g. start, end, both, or none).

---

## 🛠️ Customization

You can define your own `KonnectStyle` and implement custom rendering logic via `DrawScope`.

This allows support for curves, dashed lines, animated paths, and more.

---

## 🧪 Example

```kotlin
val konnectState = rememberKonnectState(KonnectStyle.Arrow.Default)

Box(modifier = Modifier.fillMaxSize()) {
    Box(
        modifier = Modifier
            .align(Alignment.TopStart)
            .size(32.dp)
            .background(Color.Red)
            .konnect(konnectState, anchor = RectAnchor.Center)
    )

    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .size(32.dp)
            .background(Color.Blue)
            .konnect(konnectState, anchor = RectAnchor.Center)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawKonnect(konnectState)
    )
}
```

---

## 📌 License

MIT License

---

## 👤 Author

Created by [@mtkw0127](https://github.com/mtkw)
