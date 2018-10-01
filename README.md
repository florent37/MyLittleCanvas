# MyLittleCanvas

🎨 Need to create a custom view ?

You don't know how to use Canvas, use MyLittleCanvas instead !

# Examples

[![arc](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/sample_arc.gif)](https://github.com/florent37/MyLittleCanvas)
[![dots](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/dots_sample.gif)](https://github.com/florent37/MyLittleCanvas)
[![slider](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/slider_sample.gif)](https://github.com/florent37/MyLittleCanvas)
[![tree](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/sample_tree.gif)](https://github.com/florent37/MyLittleCanvas)

Codes :
- [Animated arcs](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/ArcView.java)
- [Animated dots](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/DotsView.java)
- [Custom Slider](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/SliderView.java)
- [Tree](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/MyTreeView.java)
- [Custom Switch](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/SwitchView.java)
- [Custom RadioButton](https://github.com/florent37/MyLittleCanvas/blob/master/app/src/main/java/canvastoolbox/florent37/github/com/canvastoolbox/views/RadioView.java)

# Download

[ ![Download](https://api.bintray.com/packages/florent37/maven/mylittlecanvas/images/download.svg) ](https://bintray.com/florent37/maven/mylittlecanvas/_latestVersion)
```java
dependencies {
    compile 'com.github.florent37:mylittlecanvas:2.0.2'
}
```

# Available shapes

**TODO : NEED TO FILL THOSE DOCUMENTATIONS**

| Shapes    | link       |
|-----------|------------|
| Rect      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/RectShape.md)  |
| Circle    | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/CircleShape.md)  |
| Text      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/TextShape.md)  |
| Arc       | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/ArcShape.md)  |
| Line      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/LineShape.md)  |
| Triangle  | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/TriangleShape.md)  |
| Drawable  | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/DrawableShape.md)  |
| Path      | [documentation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/PathShape.md)  |

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

[![move_center_x](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/move_center_x.gif)](https://github.com/florent37/MyLittleCanvas)

### Difference between animated methods

-----

#### .top(values)

This method will change the shape `top` values, **ignoring its previous height**

For example, for a Rect `[left: 0, top:50, right: 200, bottom:90]`

if you use `.animate().top(50, 0)`

The final values of the Rect will be `[left: 0, top:50, right: 200, bottom:90]` then `[left: 0, top:0, right: 200, bottom:90]`,
**it will not change the bottom of the rect**

[![tree](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/top.gif)](https://github.com/florent37/MyLittleCanvas)

-----

#### .topTo(values)

It's the same as `top` except it automatically set the first value as the current value

For example, for a Rect `[left: 0, top:50, right: 200, bottom:90]`

if you use `.animate().topTo(0)`, it will animate the top from `50` to `0`

-----

#### .moveTopTo(values)

This method will change the shape `top` value, **keeping the shape height**

For example, for a Rect `[left: 0, top:10, right: 200, bottom:90]`, the height is **80**

if you use `.animate().moveTop(0)`

The final values of the Rect will be `[left: 0, top:0, right: 200, bottom:80]`,
**it will also change the bottom of the rect to keep the height of 80**

[![tree](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/move_top_to.gif)](https://github.com/florent37/MyLittleCanvas)

-----

#### .topBy(values)

This method will change the shape `top` value, **ignoring its previous height**

**Multiplying** his top by the values

For example, for a Rect `[left: 0, top:10, right: 200, bottom:90]`

if you use `.animate().topBy(0, 0.5, 1f)`

The values of the Rect will be

`[left: 0, top:0, right: 200, bottom:90]` then

`[left: 0, top:5, right: 200, bottom:90]` then

`[left: 0, top:10, right: 200, bottom:90]`

**it will not change the bottom of the rect**

[![tree](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/top_by.gif)](https://github.com/florent37/MyLittleCanvas)

-----

#### .topPlus(values)

This method will change the shape `top` value, **ignoring its previous height**

**Adding** his top by the values

For example, for a Rect `[left: 0, top:10, right: 200, bottom:90]`

if you use `.animate().topPlus(0, 10, 0)`

The values of the Rect will be

`[left: 0, top:10, right: 200, bottom:90]` then

`[left: 0, top:20, right: 200, bottom:100]` then

`[left: 0, top:10, right: 200, bottom:90]`

**it will not change the bottom of the rect**

[![tree](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/top_plus.gif)](https://github.com/florent37/MyLittleCanvas)

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

[![on_click](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/on_click.gif)](https://github.com/florent37/MyLittleCanvas)

## Listen touch

To handle easier the onTouchEvent, use `shapeEventManager.ifClicked(shape, touchSetup)`

The touchSetup gives you an item `EventHelper`, which help you bind an action with an user interaction

### Custom Actions

Use `eventHelper.onDown((event) -> {/* code */})` to execute a custom action to execute on user finger down

Use `eventHelper.onMove((event) -> {/* code */})` to execute a custom action to execute on user finger move

Use `eventHelper.onUp((event) -> {/* code */})` to execute a custom action to execute on user finger up

### Bound Actions

EventHelper can automatically bind a shape action to an user interaction

For example, you can move move the *CenterX* of a shape to the event.x using

```java
.move(myRectShape, RectShape.Pos.CENTER_X, EventPos.X)
```

Please look at implemented `RectShape.Pos` and `CircleShape.Pos`

[![tree](https://raw.githubusercontent.com/florent37/MyLittleCanvas/master/medias/example/sample_tree.gif)](https://github.com/florent37/MyLittleCanvas)

# How to Contribute

We welcome your contributions to this project.

You can submit any idea or improvement for this project.

The best way to submit a patch is to send us a [pull request](https://help.github.com/articles/about-pull-requests/).

To report a specific problem or feature request, open a new issue on Github.

# Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

Blog : [http://www.tutos-android-france.com/](http://www.tutos-android-france.com/)

Fiches Plateau Moto : [https://www.fiches-plateau-moto.fr/](https://www.fiches-plateau-moto.fr/)

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
