package brainitall.pixelly.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import brainitall.pixelly.controller.Manager;
import brainitall.pixelly.model.metier.Grille;

public class PlayView extends View implements GestureDetector.OnGestureListener{

    // Modele
    Grille mGrille;

    // Elements graphiques
    private Paint mPaint;
    private float mLargeurGrille;
    private float mTailleSeparateur;
    private float mLargeurCellule;
    private boolean ok;
    private GestureDetector mDetector;


    public PlayView(Context context) {
        super(context);
        mDetector = new GestureDetector(getContext(),this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ok = false;
    }

    public PlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Calculs des différentes tailles
        mTailleSeparateur = (w /9f) / 20f;
        mLargeurGrille = w;
        mLargeurCellule = mLargeurGrille /9f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextAlign(Paint.Align.CENTER);
        // Dessin des cellules
        /*for(int y = 0; y < 3; y++){
            for(int x = 0; x <3; x++){
                paint.setColor(Color.WHITE);
                canvas.drawRect(x*largeurCellule,y*largeurCellule,(x+1)*largeurCellule,(y+1)*largeurCellule,paint);
            }
        }*/
        // Dessin des lignes de la grille

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(mTailleSeparateur);
        int i;
        for (i = 0; i <= 3; i++) {
            canvas.drawLine(i * (mLargeurCellule * 3), 0, i * (mLargeurCellule * 3), mLargeurCellule * 9, mPaint);
            canvas.drawLine(0, i * (mLargeurCellule * 3), mLargeurCellule * 9, i * (mLargeurCellule * 3), mPaint);
        }
        if (ok) {
            canvas.drawText("Numéro de la grille "+ Manager.getInstance().getmLaGrille().getNumGrille(),
                    i * mLargeurCellule + mLargeurCellule / 2,
                    i * mLargeurCellule + mLargeurCellule * 0.75f, mPaint);
        }
        else  {
            canvas.drawText("C'EST PAS OK !!!!! ",
                    i * mLargeurCellule + mLargeurCellule / 2,
                    i * mLargeurCellule + mLargeurCellule * 0.75f, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mDetector.onTouchEvent(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if(ok){
            ok = false;
        }
        else{
            ok = true;
        }
        postInvalidate();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}

