# CircleShape

## Position

- `moveXBy(float differenceX)`
- `moveYBy(float differenceY)`

- `setCenterX(float centerX)`
- `setCenterY(float centerY)`

- `setRadius(float radius)`

## Limits

- `.setMinX(float minX)`
- `.setMaxX(float maxX)`
- `.setMinY(float minY)`
- `.setMaxY(float maxY)`

## Decoration

- `.setBorderColor(int borderColor)`
- `.setBorderWidth(float borderWidth)`

## Animation

this will use **RectShapeAnimation**

- `.animate().centerX(float... values)`
- `.animate().centerXTo(float... values)`
- `.animate().centerXPlus(float... values)`

- `.animate().centerY(float... values)`
- `.animate().centerYTo(float... values)`
- `.animate().centerYPlus(float... values)`

- `.animate().radius(float... values)`
- `.animate().radiusTo(float... values)`
- `.animate().radiusBy(float... values)`
- `.animate().radiusPlus(float... values)`

- `.animate().borderWidthTo(int... colors)`
- `.animate().borderColorTo(int... colors)`
