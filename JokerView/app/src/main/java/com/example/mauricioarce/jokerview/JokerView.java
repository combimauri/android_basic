package com.example.mauricioarce.jokerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio Arce on 14/07/2015.
 */
public class JokerView extends SurfaceView implements SurfaceHolder.Callback {

    public static float jokerWidth;
    public static float jokerHeight;

    private JokerThread jokerThread;
    private List<Joker> joker= new ArrayList<>();

    public JokerView(Context context) {
        super(context);
        getHolder().addCallback(this);
        jokerThread = new JokerThread(this);
    }

    public void doDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        synchronized (joker) {
            for (Joker jack : joker) {
                jack.doDraw(canvas);
            }
        }
    }

    public void animateJoker() {
        synchronized (joker) {
            for (Joker jack : joker) {
                jack.animate();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!jokerThread.isAlive()) {
            jokerThread = new JokerThread(this);
            jokerThread.setRunning(true);
            jokerThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        jokerWidth = width;
        jokerHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (jokerThread.isAlive()) {
            jokerThread.setRunning(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (joker) {
            joker = new ArrayList<>();
            joker.add(new Joker(getResources(), (int) event.getX(), (int) event.getY()));
        }
        return super.onTouchEvent(event);
    }
}
