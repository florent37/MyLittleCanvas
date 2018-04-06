# RectShape

## Position

- `.setRect(RectF rectF)`
- `.setRect(float left, float top, float right, float bottom)`

- `setBottom(float bottom)`
- `setTop(float top)`
- `setLeft(float left)`
- `setRight(float right)`

- `marginBottom(float margin)`
- `marginTop(float margin)`
- `marginLeft(float margin)`
- `marginRight(float margin)`

- `below(RectShape other)`
- `above(RectShape other)`

- `setWidth(float width)`
- `setHeight(float height)`

- `centerVertical(float parentHeight)`
- `centerHorizontal(float parentWidth)`

- `alignBottom(RectShape other)`
- `alignTop(RectShape other)`
- `alignLeft(RectShape other)`
- `alignRight(RectShape other)`

- `moveXBy(float differenceX)`
- `moveYBy(float differenceY)`

- `moveTopTo(float newTop)`
- `moveBottomTo(float newBottom)`
- `moveLeftTo(float newLeft)`
- `moveRightTo(float newRight)`

- `moveCenterXTo(float centerX)`
- `moveCenterYTo(float centerY)`

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

- `.animate().left(float... values)`
- `.animate().leftTo(float... values)`
- `.animate().leftBy(float... values)`
- `.animate().leftPlus(float... values)`
- `.animate().moveLeftTo(float... values)`

- `.animate().right(float... values)`
- `.animate().rightTo(float... values)`
- `.animate().rightBy(float... values)`
- `.animate().rightPlus(float... values)`
- `.animate().moveRightTo(float... values)`

- `.animate().top(float... values)`
- `.animate().topTo(float... values)`
- `.animate().topBy(float... values)`
- `.animate().topPlus(float... values)`
- `.animate().moveTopTo(float... values)`

- `.animate().bottom(float... values)`
- `.animate().bottomTo(float... values)`
- `.animate().bottomBy(float... values)`
- `.animate().bottomPlus(float... values)`
- `.animate().moveBottomTo(float... values)`

- `.animate().borderWidthTo(int... colors)`
- `.animate().borderColorTo(int... colors)`
