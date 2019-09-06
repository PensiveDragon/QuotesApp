package com.example.quotesapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OnTouchGestureListener extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat gDetector;
    private View movingView;
    private TextView gestureText;

    public OnTouchGestureListener(Context context, TextView gestureText, View movingView) {
        this.gDetector = new GestureDetectorCompat(context, this);

        Log.i("OnTouchGestureListener", "Constructor running...");

        gDetector.setOnDoubleTapListener(this);
        gDetector.setIsLongpressEnabled(false);

        this.movingView = movingView;
        this.gestureText = gestureText;



    }



    public void activateImage() {
        movingView.animate().scaleX(.8f).scaleY(.8f).setDuration(150);
        movingView.setElevation(5);
        //movingView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        ObjectAnimator colorfade = ObjectAnimator.ofObject(movingView, "backgroundColor", new ArgbEvaluator(), 0x00FFFFFF, 0xBFFFFFFF);
        colorfade.setDuration(150);
        colorfade.start();
    }

    public void deactivateImage() {

        movingView.animate().scaleX(1f).scaleY(1f).translationY(0).translationX(0).setDuration(150);
        movingView.setElevation(0);
        //movingView.setBackgroundColor(Color.parseColor("#00FFFFFF"));

        ObjectAnimator colorfade = ObjectAnimator.ofObject(movingView, "backgroundColor", new ArgbEvaluator(), 0xBFFFFFFF, 0x00FFFFFF);
        colorfade.setDuration(150);
        colorfade.start();
    }

    public void scrollImage(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        switch (e2.getAction()) {

            case MotionEvent.ACTION_MOVE:

                //Log.i("OnTouchGestureListener", "Coords: e1 Y = " + e1.getRawY() + " " + e1.getY() + " " + e1.getY());
                //Log.i("OnTouchGestureListener", "Coords: e2 Y = " + e2.getRawY() + " " + e2.getY() + " " + e2.getY());
                //Log.i("OnTouchGestureListener", "Coords: Dt X = " + (e1.getRawX() - e2.getRawX()) + " " + (e1.getX() - e2.getX()) + " " + (e1.getX() - e2.getX()));
                //Log.i("OnTouchGestureListener", "Coords: X = " + (e1.getRawX() - e2.getRawX()) + " Y = " + (e1.getRawY() - e2.getRawY()));
                Log.i("OnTouchGestureListener", "Coords: X = " + distanceX + " Y = " + distanceY);


                movingView.animate()

                        .translationX(-(e1.getX() - e2.getX()))
                        .translationY(-(e1.getY() - e2.getY()))
                        //.x(-(e1.getX() - e2.getX()))
                        //.y(-(e1.getY() - e2.getY()))
                        .setDuration(0)
                        .start();
                break;
        }

    }

    public void onSwipeRight() {

        movingView.animate()
                .scaleX(.5f)
                .scaleY(.5f)
                .translationX(1000)
                .translationY(0)
                .setDuration(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        movingView.animate()
                                .translationX(-1000)
                                .translationY(0)
                                .setDuration(0)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {

                                        movingView.animate()
                                                .scaleX(1f)
                                                .scaleY(1f)
                                                .translationX(0)
                                                .translationY(0)
                                                .setDuration(500);
                                    }
                                });
                    }
                })
                .start();


    }

    public void onSwipeLeft() {

        movingView.animate()
                .scaleX(.5f)
                .scaleY(.5f)
                .translationX(-1000)
                .translationY(0)
                .setDuration(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        movingView.animate()
                                .translationX(1000)
                                .translationY(0)
                                .setDuration(0)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {

                                        movingView.animate()
                                                .scaleX(1f)
                                                .scaleY(1f)
                                                .translationX(0)
                                                .translationY(0)
                                                .setDuration(500);
                                    }
                                });
                    }
                })
                .start();

    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Log.i("OnTouchGestureListener", "OnTouchEvent");

        // This bit of code can interpret 'onUp' events from the gestures, and feeds them to the onUp method below.   I think this was created to solve the longpress
        // problem, but that was also solved by the setLongPressEnabled setting above.   This might also solve another problem that I cannot recall at the moment.
        boolean detectedUp = event.getAction() == MotionEvent.ACTION_UP;
        if (!gDetector.onTouchEvent(event) && detectedUp) {
            return onUp(event);
        }

        this.gDetector.onTouchEvent(event);
        return true;

        //return super.onTouchEvent(event);
    }

    public boolean onUp(MotionEvent e) {

        if (gestureText != null) {gestureText.setText("onUp");}
        Log.i("Gesture", "onUp");
        deactivateImage();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (gestureText != null) {gestureText.setText("onDown");}
        Log.i("Gesture", "onDown");
        Log.i("OnTouchGestureListener", "Coords: X = " + (e.getRawX()) + " Y = " + (e.getRawY()));
        activateImage();
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (gestureText != null) {gestureText.setText("onFling");}
        Log.i("Gesture", "onFling");



        final int SWIPE_DISTANCE_THRESHOLD = 100;
        final int SWIPE_VELOCITY_THRESHOLD = 100;

        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (distanceX > 0) {
                Log.i("OnFling", "Swiped Right!");
                onSwipeRight();
            } else {
                Log.i("OnFling", "Swiped Left!");
                onSwipeLeft();
            }
            //deactivateImage();
            return true;
        }

        deactivateImage();
        return false;

        //return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if (gestureText != null) {gestureText.setText("onLongPress");}
        Log.i("Gesture", "onLongPress");

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (gestureText != null) {gestureText.setText("onScroll");}
        //Log.i("Gesture", "onScroll");
        scrollImage(e1, e2, distanceX, distanceY);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        if (gestureText != null) {gestureText.setText("onShowPress");}
        Log.i("Gesture", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (gestureText != null) {gestureText.setText("onSingleTapUp");}
        Log.i("Gesture", "onSingleTapUp");
        //deactivateImage();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (gestureText != null) {gestureText.setText("onDoubleTap");}
        Log.i("Gesture", "onDoubleTap");
        deactivateImage();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        if (gestureText != null) {gestureText.setText("onDoubleTapEvent");}
        Log.i("Gesture", "onDoubleTapEvent");
        deactivateImage();
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (gestureText != null) {gestureText.setText("onSingleTapConfirmed");}
        Log.i("Gesture", "onSingleTapConfirmed");
        deactivateImage();
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        onTouchEvent(motionEvent);
        return true;
        //return false;
    }
}
