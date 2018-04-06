# ArcShape

Inherits from [RectShape](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/RectShape.md)

## Position

- `setCenterX(float centerX)`
- `setCenterY(float centerY)`
- `setRadius(float radius)`

## Decoration

- `.setStartAngle(float startAngle)`
- `.setEndAngle(float endAngle)`

- `.setStrokeWidth(float strokeWidth)`

## Animation

it inherit methods from [RectShapeAnimation](https://github.com/florent37/MyLittleCanvas/blob/master/documentation/RectShape.md)

Added methods:
- `.animate().startAngle(float... values)`
- `.animate().startAngleTo(float... values)`
- `.animate().startAngleBy(float... values)`
- `.animate().startAnglePlus(float... values)`

- `.animate().endAngle(float... values)`
- `.animate().endAngleTo(float... values)`
- `.animate().endAngleBy(float... values)`
- `.animate().endAnglePlus(float... values)`