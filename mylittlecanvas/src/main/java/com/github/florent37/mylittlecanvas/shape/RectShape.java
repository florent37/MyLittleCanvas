package com.github.florent37.mylittlecanvas.shape;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public abstract class RectShape extends Shape  {

    protected final RectF rectF = new RectF();
    protected final Path path = new Path();

    @Override
    protected void draw(Canvas canvas) {
        final int save = canvas.save();

        final PointF rotationPivot = getRotationPivot();
        if(getRotation() != 0) {
            canvas.rotate(getRotation(), rotationPivot.x, rotationPivot.y);
        }
        canvas.drawRect(rectF, paint);

        canvas.restoreToCount(save);
    }

    protected void update(){

    }

    public RectF getRectF() {
        return new RectF(rectF);
    }

    public RectShape setRectF(RectF rectF) {
        this.rectF.set(rectF);
        update();
        return this;
    }

    public RectShape copyPosition(RectShape other) {
        return this.setRectF(other.getRectF());
    }

    public RectShape setRectF(float left, float top, float right, float bottom) {
        this.rectF.set(left, top, right, bottom);
        update();
        return this;
    }

    public RectShape setWidth(float width){
        setRight(getLeft() + width);
        return this;
    }

    public RectShape setHeight(float height){
        setBottom(getTop() + height);
        return this;
    }

    public float getTop(){
        return rectF.top;
    }

    public float getBottom(){
        return rectF.bottom;
    }

    public float getLeft(){
        return rectF.left;
    }

    public float getRight(){
        return rectF.right;
    }

    public RectShape setLeft(float left){
        rectF.left = left;
        update();
        return this;
    }

    public RectShape setRight(float right){
        rectF.right = right;
        update();
        return this;
    }

    public RectShape setBottom(float bottom){
        rectF.bottom = bottom;
        update();
        return this;
    }

    public RectShape setTop(float top){
        rectF.top = top;
        update();
        return this;
    }

    public RectShape marginTop(float margin){
        rectF.top += margin;
        update();
        return this;
    }

    public RectShape marginLeft(float margin){
        rectF.left += margin;
        update();
        return this;
    }

    public RectShape marginRight(float margin){
        rectF.right += margin;
        update();
        return this;
    }

    public RectShape marginBottom(float margin){
        rectF.bottom += margin;
        update();
        return this;
    }

    public RectShape below(RectShape other) {
        setTop(other.getBottom());
        return this;
    }

    public RectShape above(RectShape other) {
        setBottom(other.getTop());
        return this;
    }

    public ValueAnimator animateLeft(float...newLeft){
        return ObjectAnimator.ofFloat(this, "left", newLeft);
    }

    public ValueAnimator animateRight(float...newRight){
        return ObjectAnimator.ofFloat(this, "right", newRight);
    }

    public ValueAnimator animateTop(float...newTop) {
        return ObjectAnimator.ofFloat(this, "top", newTop);
    }

    public ValueAnimator animateBottom(float...newBottom) {
        return ObjectAnimator.ofFloat(this, "bottom", newBottom);
    }

    @Override
    public int getCenterX() {
        return (int) rectF.centerX();
    }

    @Override
    public int getCenterY() {
        return (int) rectF.centerY();
    }

    public float getWidth(){
        return Math.max(0, getRight() - getLeft());
    }

    public float getHeight(){
        return Math.max(0, getBottom() - getTop());
    }

    public RectShape centerHorizontal(int parentWidth) {
        final float width = getWidth();
        final float left = parentWidth/2f - width/2f;
        setLeft(left);
        setRight(left + width);
        return this;
    }

    public void centerVertical(int parentHeight) {
        final float height = getHeight();
        final float top = parentHeight/2f - height / 2f;
        setTop(top);
        setBottom(top + height);
    }

    public RectShape alignTop(float top) {
        final float height = getHeight();
        setTop(top);
        setBottom(getTop() + height);
        return this;
    }

    public RectShape alignTop(RoundRectShape other) {
        alignTop(other.getTop());
        return this;
    }

    public RectShape alignBottom(float bottom) {
        final float height = getHeight();
        setBottom(bottom);
        setTop(getBottom() - height);
        return this;
    }

    public RectShape alignBottom(RoundRectShape other) {
        alignBottom(other.getBottom());
        return this;
    }

    public RectShape moveXBy(float differenceX){
        final float width = getWidth();
        setLeft(getLeft() + differenceX);
        setRight(getLeft() + width);
        return this;
    }

    public RectShape moveYBy(float differenceY){
        final float height = getHeight();
        setTop(getTop() + differenceY);
        setBottom(getTop() + height);
        return this;
    }

    public RectShape moveBy(float differenceX, float differenceY){
        moveXBy(differenceX);
        moveYBy(differenceY);
        return this;
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return rectF.contains(x, y);
    }
}
