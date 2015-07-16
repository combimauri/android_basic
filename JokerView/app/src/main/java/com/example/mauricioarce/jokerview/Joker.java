package com.example.mauricioarce.jokerview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Mauricio Arce on 14/07/2015.
 */
public class Joker {

    private float mapX;
    private float mapY;
    private float speedX;
    private float speedY;

    private Bitmap bitmap;

    public Joker(Resources resources, int xPos, int yPos) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon);
        Random random = new Random();
        mapX = xPos - bitmap.getWidth() / 2;
        mapY = yPos - bitmap.getWidth() / 2;
        speedX = random.nextInt(5) - 2;
        speedY = random.nextInt(5) - 2;
    }

    public void doDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, mapX, mapY, null);
    }

    public void animate() {
        mapX += speedX;
        mapY += speedY;
        checkBorders();
    }

    private void checkBorders() {
        if (mapX <= 0) {
            speedX = -speedX;
            mapX = 0;
        } else if (mapX + bitmap.getWidth() >= JokerView.jokerWidth) {
            speedX = -speedX;
            mapX = JokerView.jokerWidth - bitmap.getWidth();
        }

        if (mapY <= 0) {
            speedY = -speedY;
            mapY = 0;
        } else if (mapY + bitmap.getHeight() >= JokerView.jokerHeight) {
            speedY = -speedY;
            mapY = JokerView.jokerHeight - bitmap.getHeight();
        }
    }
}
