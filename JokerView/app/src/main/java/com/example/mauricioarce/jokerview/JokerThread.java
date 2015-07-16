package com.example.mauricioarce.jokerview;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Mauricio Arce on 14/07/2015.
 */
public class JokerThread extends Thread {

    private JokerView jokerView;
    private SurfaceHolder holder;
    private boolean running;

    public JokerThread(JokerView jokerView) {
        this.jokerView = jokerView;
        holder = this.jokerView.getHolder();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                jokerView.animateJoker();
                jokerView.doDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
