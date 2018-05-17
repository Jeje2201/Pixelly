package brainitall.pixelly.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import brainitall.pixelly.controller.Manager;

import static android.graphics.Color.rgb;

public class PlayView extends SurfaceView implements SurfaceHolder.Callback, GestureDetector.OnGestureListener{

    // Elements Techniques
    private DrawingThread mThread;



    // Elements graphiques
    private static final float TOUCH_TOLERANCE = 4;
    private SurfaceHolder mSurfaceHolder;
    private GestureDetector mDetector;
    private Paint mPaint;
    private int mLargeurGrilleCases;
    private float mLargeurGrille;
    private float mTailleSeparateur;
    private float mLargeurCellule;

    // Elements Positionnement
    private float mX;
    private float mY;



    public PlayView(Context context) {
        super(context);
        mDetector = new GestureDetector(getContext(),this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mThread = new DrawingThread();

        // Initialisation du pinceau
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


    }

    public PlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDetector = new GestureDetector(getContext(),this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mThread = new DrawingThread();

        // Initialisation du pinceau
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    // ----------------------- METHODES LIEES A LA SURFACEVIEW -------------------------


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Calculs des différentes tailles
        mLargeurGrilleCases = Manager.getInstance().getLaGrille().getLargeurGrille();
        mTailleSeparateur = (w /9f) / 20f;
        mLargeurGrille = w;
        mLargeurCellule = mLargeurGrille / mLargeurGrilleCases;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        /* CREATION DE LA GRILLE ------------------------- */
        float rayonCercle = mLargeurCellule / 2 - mLargeurCellule / 10;

        for (int y = 0; y < mLargeurGrilleCases; y++) {
            for (int x = 0; x < mLargeurGrilleCases; x++) {


                // Positionnement des terminaisons : chiffre + couleur

                // Si la case courante est une terminaison
                if (Manager.getInstance().getLaGrille().getCase(y, x).isTerminaison()) {

                    // Recherche de l'indice dans la liste des terminaisons
                    int indice = Manager.getInstance().getLaGrille().donneIndiceTerminaison(y,x);


                    // Couleur de la case
                    int r = Manager.getInstance().getLaGrille().getLesTerminaisons().get(indice).getR();
                    int g = Manager.getInstance().getLaGrille().getLesTerminaisons().get(indice).getG();
                    int b = Manager.getInstance().getLaGrille().getLesTerminaisons().get(indice).getB();
                    mPaint.setColor(rgb(r, g, b));


                    // CERCLE si taille de la terminaison != 1
                    if( Manager.getInstance().getLaGrille().getLesTerminaisons().get(indice).getTailleChemin() != 1) {
                        canvas.drawCircle((x + 1) * mLargeurCellule - mLargeurCellule/2,
                                (y +1) * mLargeurCellule - mLargeurCellule/2,
                                rayonCercle,
                                mPaint);
                    }else{
                        canvas.drawRect(x * mLargeurCellule,
                                y * mLargeurCellule,
                                (x + 1) * mLargeurCellule,
                                (y + 1) * mLargeurCellule,
                                mPaint);
                    }

                    // Chiffre terminaison
                    mPaint.setColor(Color.BLACK);
                    mPaint.setTextAlign(Paint.Align.CENTER);
                    mPaint.setTextSize(mLargeurCellule * 0.7f);
                    canvas.drawText("" + Manager.getInstance().getLaGrille().getLesTerminaisons().get(indice).getTailleChemin(),
                            x * mLargeurCellule + mLargeurCellule / 2,
                            y * mLargeurCellule + mLargeurCellule * 0.75f, mPaint);
                }


            }
        }

        // Création des bordures
        for(int i=0; i<= mLargeurGrilleCases; i++ )
        {
            // Bordures épaisses = extremités de la grilles
            if(i==0 || i== mLargeurGrilleCases) {
                mPaint.setColor(Color.BLACK);
                mPaint.setStrokeWidth(mTailleSeparateur);
            }
            else{
                mPaint.setColor( Color.GRAY );
                mPaint.setStrokeWidth( mTailleSeparateur/2 );
            }
            // Bordures verticales
            canvas.drawLine(i * mLargeurCellule, 0, i * mLargeurCellule, mLargeurCellule * mLargeurGrilleCases, mPaint);
            // Bordures horizontales
            canvas.drawLine(0, i * mLargeurCellule, mLargeurCellule * mLargeurGrilleCases, i * mLargeurCellule, mPaint);
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.setKeepDrawing(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // A utiliser si on change l'orientation du téléphone
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.keepDrawing = false;
        boolean joined = false;
        while (!joined) {
            try {
                mThread.join();
                joined = true;
            } catch (InterruptedException e) {}
                // Gestion Exception
        }
    }

    // -------------------------------- METHODES LIEES AU TACTIL -----------------------

    private void touchStart(float x, float y) {
        //mPath = new Path();
        //Trace ligne = new Trace(Color.RED, 10, mPath);
        // mLignes.add(ligne);

        //mPath.reset();
        //mPath.moveTo(x, y);


        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            //mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            //mPath.lineTo(x,y);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        //mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                break;
        }

        return mDetector.onTouchEvent(event);
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

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    // ---------------------------------- CLASSE INTERNE : THREAD -----------------------

    public class DrawingThread extends Thread {
        private boolean keepDrawing = true;

        @Override
        public void run() {
            while(keepDrawing){
                Canvas canvas = null;
                try {
                    // On récupère le canvas pour dessiner dessus
                    canvas = mSurfaceHolder.lockCanvas();
                    // On s'assure qu'aucun autre thread n'accède au holder
                    synchronized (mSurfaceHolder) {
                        // Et on dessine
                        draw(canvas);
                    }
                } finally {
                    // Notre dessin fini, on relâche le Canvas pour que le dessin s'affiche
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }

                // Pour redessiner
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
            }
        }

        public boolean isKeepDrawing() {
            return keepDrawing;
        }

        public void setKeepDrawing(boolean keepDrawing) {
            this.keepDrawing = keepDrawing;
        }
    }


}

