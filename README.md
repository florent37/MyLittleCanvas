# MyLittleCanvas

🎨 Need to create a vustom view ?
You don't know how to use Canvas, use MyLittleCanvas instead !

# Examples

**ADD A SAMPLE GIF**

**ADD A SAMPLE GIF**

# Download

[ ![Download](https://api.bintray.com/packages/florent37/maven/mylittlecanvas/images/download.svg) ](https://bintray.com/florent37/maven/mylittlecanvas/_latestVersion)
```java
dependencies {
    compile 'com.github.florent37:mylittlecanvas:2.0.0'
}
```

# Available shapes

| Shapes    | link       |
|-----------|------------|
| Rect      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/RectShape.md)  |
| Circle    | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/CircleShape.md)  |
| Text      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/TextShape.md)  |
| Arc       | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/ArcShape.md)  |
| Line      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/LineShape.md)  |
| Triangle  | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/TriangleShape.md)  |
| Drawable  | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/DrawableShape.md)  |
| Path      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/dev/documentation/PathShape.md)  |

# Animation

Follow the example of [SwitchView](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/SwitchView.java)

Shape animations are executed by an instance of `ShapeAnimator` attached to your view

```java
private final ShapeAnimator shapeAnimator = new ShapeAnimator(this);
```

All animated methods of shapes are wrapped into the method `.animate()`

For example, for a `CircleShape`, you can animate his position (centerX) using
```java
myCircleShape.animate().centerXTo(15);
```

Then use your `ShapeAnimator` to execute this animation

```java
shapeAnimator.play(myCircleShape.animate().centerXTo(15))
    .setDuration(500)
    .start()
```

**ADD A SAMPLE GIF**

**ADD A SAMPLE GIF**

### Difference between animated methods

#### .top(values)
//TODO
#### .topBy(values)
//TODO
#### .topPlus(values)
//TODO
#### .moveTop(values)
//TODO

# Event handling

Follow the example of [Tree View](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/MyTreeView.java)

User events handling like *onClick* or *onTouch* are handled by an instance of `ShapeEventManager` attached to your view

```java
private final ShapeEventManager shapeAnimator = new ShapeEventManager(this);
```

## Listen click event

You can listen for a shape click using `shapeEventManager.ifClicked(shape, listener)`

```java
shapeEventManager.ifClicked(myShape, clickedShape -> {
    //your action
    myShape.setColor(Color.BLACK);
});
```

**ADD A SAMPLE GIF**

**ADD A SAMPLE GIF**

## Listen touch

To handle easier the onTouchEvent, use `shapeEventManager.ifClicked(shape, touchSetup)`

The touchSetup gives you an item `EventHelper`, which help you bind an action with an user interaction

### Custom Actions

Use `eventHelper.onDown((event) -> {/* code */})` to execute a custom action to execute on user finger down

Use `eventHelper.onMove((event) -> {/* code */})` to execute a custom action to execute on user finger move

Use `eventHelper.onUp((event) -> {/* code */})` to execute a custom action to execute on user finger up

### Bounded Actions

EventHelper can automatically bind a shape action to an user interraction

For example, you can move move the *CenterX* of a shape to the event.x using

```java
.move(myRectShape, RectShape.Pos.CENTER_X, EventPos.X)
```

Please look at implemented `RectShape.Pos` and `CircleShape.Pos`

**ADD A SAMPLE GIF**

**ADD A SAMPLE GIF**


# How to Contribute

We welcome your contributions to this project.

You can submit any idea or improvement for this project.

The best way to submit a patch is to send us a [pull request](https://help.github.com/articles/about-pull-requests/).

To report a specific problem or feature request, open a new issue on Github.

# Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

Blog : [http://www.tutos-android-france.com/](http://www.tutos-android-france.com/)

<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/florentchampigny">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>

# License

    Copyright 2018 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
