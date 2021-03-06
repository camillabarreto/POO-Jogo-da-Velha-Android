package br.edu.ifsc.sj.poo29004.desenhocanvas;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


/**
 * Essa classe estende uma View para permitir desenhar objetos diretamente da mesma forma
 * que é feito quando se programa com Java 2D.
 *
 * Veja mais na documentação oficial:
 *
 * https://developer.android.com/training/custom-views/custom-drawing
 *
 *
 */
public class TabuleiroView extends View {

    private Paint mTextoGenerico, mJogadorO, mJogadorX;
    private final static String TAG = "Tabuleiro";
    private boolean mClicouNoX;
    private Botao mBotao;

    private ArrayList<Integer[]> posições;


    public TabuleiroView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        int alturaDispositivo = displayMetrics.heightPixels; // não está considerando a área disponível para desenho

        this.mClicouNoX = false;

        mTextoGenerico = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextoGenerico.setColor(Color.BLACK);
        mTextoGenerico.setStyle(Paint.Style.FILL);
        mTextoGenerico.setTextSize(getResources().getDimensionPixelSize(R.dimen.tamanhoDaFonte));
        mTextoGenerico.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        mJogadorO = new Paint(Paint.ANTI_ALIAS_FLAG);
        mJogadorX = new Paint(Paint.ANTI_ALIAS_FLAG);

        mJogadorO.setColor(Color.BLUE);
        mJogadorO.setStyle(Paint.Style.STROKE);
        mJogadorO.setStrokeWidth(10);

        mJogadorX.setColor(Color.RED);
        mJogadorX.setStyle(Paint.Style.STROKE);
        mJogadorX.setStrokeWidth(10);

        mBotao = new Botao(context, 0, alturaDispositivo-500);

        //gerar posições válidas
//        posições = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            Integer[] aux = new Integer[2];
//            aux[0] = 100+(i*500);
//            aux[1] = 200+(i*500);
//            posições.add(aux);
//        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //linhas
        canvas.drawLine(0, this.getHeight()/4, this.getWidth(), getHeight()/4, new Paint());
        canvas.drawLine(0, 2*this.getHeight()/4, this.getWidth(), 2*getHeight()/4, new Paint());
        canvas.drawLine(0, 3*this.getHeight()/4, this.getWidth(), 3*getHeight()/4, new Paint());

        //colunas
        canvas.drawLine(this.getWidth()/3, this.getHeight()/4, this.getWidth()/3, this.getHeight(), new Paint());
        canvas.drawLine(2*this.getWidth()/3, this.getHeight()/4, 2*this.getWidth()/3, this.getHeight(), new Paint());


        //Exemplo de desenho na tela
//        canvas.drawCircle(100,100,50, mJogadorO);
//        canvas.drawLine(200, 200, 300, 300 , mJogadorX);
//        canvas.drawLine(200, 300, 300, 200 , mJogadorX);


        if (mClicouNoX){
            canvas.drawText("Clicou no X", 400, 400, mTextoGenerico);
        }

        canvas.drawBitmap(mBotao.getBitmap(), mBotao.getX(), mBotao.getY(), mTextoGenerico);
        Log.d(TAG, "altura: " + this.getHeight());


    }



    @Override
    public boolean onTouchEvent(MotionEvent event){

        logicaDoJogo(event);


        return super.onTouchEvent(event);
    }

    private void logicaDoJogo(MotionEvent event){

        float px = event.getX();
        float py = event.getY();

        if (mBotao.clicouNoBotao(px, py)){
            mBotao.pressionar();
            invalidate(); // para redesenhar a View
        }

        if ((px > 200 && px < (300)) && (py > 200 && py < (300))){
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setMessage("Clicou no X?")
                    .setCancelable(false)
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mClicouNoX = true;
                            invalidate();// para redesenhar a View
                        }
                    })
                    .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mClicouNoX = false;
                            dialog.cancel();
                            invalidate(); // para redesenhar a View
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }



}
