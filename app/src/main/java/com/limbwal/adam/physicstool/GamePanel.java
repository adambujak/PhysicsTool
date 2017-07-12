package com.limbwal.adam.physicstool;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



import java.util.concurrent.TimeUnit;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    public GamePanel (Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        player = new ball(new RectF(50, 50, 100, 100), Color.rgb(255, 0 , 0));
        sun = new ball (new RectF(50, 50, 200, 200), Color.rgb(242, 198, 36));
        ground = new ground(new Rect(0, 6000, 75, 0), Color.rgb(105, 40, 33));
        grass = new ground(new Rect(50, 12, 13, 10), Color.rgb(33, 127, 11));


        groundPoint = new Point(0,0);
        sunPoint = new Point(this.getWidth()-150, this.getHeight()-150);
        playerPoint = new Point(50, 50);
        grassPoint = new Point (0,0);
        setFocusable(true);
    }
    private ball player;
    private ball sun;
    private ground ground;
    private Point sunPoint;
    private Point groundPoint;
    private Point playerPoint;
    private ground grass;
    private Point grassPoint;
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while (retry){ //he says true
            try{
                thread.setRunning(false);
                thread.join();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                double cnt2 =0;
                for (int cnt = 0; cnt < 80; cnt++) {
                    cnt2 +=0.25;
                    System.out.println(cnt2);
                    int y =Integer.parseInt(""+Math.round(refresh(cnt2)))+75;
                    if (y<60){
                        break;
                    }
                    playerPoint.set(y, (Integer.parseInt(""+Math.round(refresh1(cnt2)))));

                    try {
                        TimeUnit.MILLISECONDS.sleep(40);
                    }
                    catch (InterruptedException w){}
                }
        }
        return true;
    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.rgb(51,135,211));
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        sunPoint = new Point(this.getWidth()-150, this.getHeight()-150);
        canvas.drawText("Velocity of 10 m/s at 45° above the horizontal. Tap anywhere to simulate.", this.getWidth()/2-400, 40, paint);
        player.draw(canvas);
        sun.draw(canvas);
        ground.draw(canvas);
        grass.draw(canvas);

        for (int cnt = 0; cnt < 3000; cnt +=10) {
            grass = new ground(new Rect(50, 12+cnt, 13, 10+cnt), Color.rgb(33, 127, 11));
            grass.draw(canvas);
        }


    }

    public void update(){
        player.update(playerPoint);
        sun.update(sunPoint);
        ground.update(groundPoint);


    }
    public double refresh(double cnt){
        return cnt*cnt*-5+92*cnt;
    }
    public double refresh1(double cnt){
        return cnt*92;
    }

}
