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
    Grille laGrille;

    // Elements graphiques
    private Paint paint;
    private float largeurGrille;
    private float tailleSeparateur;
    private float largeurCellule;


    public PlayView(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public PlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Calculs des différentes tailles
        tailleSeparateur = (w /9f) / 20f;
        largeurGrille = w;
        largeurCellule = largeurGrille /9f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextAlign(Paint.Align.CENTER);
        // Dessin des cellules
        for(int y = 0; y < 3; y++){
            for(int x = 0; x <3; x++){
                paint.setColor(Color.WHITE);
                canvas.drawRect(x*largeurCellule,y*largeurCellule,(x+1)*largeurCellule,(y+1)*largeurCellule,paint);
            }
        }
        // Dessin des lignes de la grille

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(tailleSeparateur);
        for( int i=0; i<=3; i++ ) {
            canvas.drawLine( i*(largeurCellule*3), 0, i*(largeurCellule*3), largeurCellule*9, paint );
            canvas.drawLine( 0,i*(largeurCellule*3), largeurCellule*9, i*(largeurCellule*3), paint );
        }


    }
}
