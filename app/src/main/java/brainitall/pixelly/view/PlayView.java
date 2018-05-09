package brainitall.pixelly.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import brainitall.pixelly.model.metier.Grille;

public class PlayView extends View {

    // Modele
    Grille mGrille;

    // Elements graphiques
    private Paint mPaint;
    private float mLargeurGrille;
    private float mTailleSeparateur;
    private float mLargeurCellule;


    public PlayView(Context context) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
        for( int i=0; i<=3; i++ ) {
            canvas.drawLine( i*(mLargeurCellule*3), 0, i*(mLargeurCellule*3), mLargeurCellule*9, mPaint );
            canvas.drawLine( 0,i*(mLargeurCellule*3), mLargeurCellule*9, i*(mLargeurCellule*3), mPaint );
        }


    }
}
