package br.edu.ifsc.sj.poo29004.desenhocanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Classe para representar um componente que poderá ser desenhado em uma SurfaceView, no caso,
 * na classe GameView.
 */
public abstract class Componente {
    protected Context context;
    protected Bitmap bitmap;
    protected int x;
    protected int y;

    public Componente(Context context, int drawableResource, int x, int y) {
        this.x = x;
        this.y = y;
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), drawableResource);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(int drawableResource) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), drawableResource);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
