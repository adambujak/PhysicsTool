package com.limbwal.adam.physicstool;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;


public class ball implements GameObject{
    private RectF rectangle;
    private int color;
    public ball(RectF rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;

    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawOval(rectangle, paint);
    }


    public void update(Point point) {
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
