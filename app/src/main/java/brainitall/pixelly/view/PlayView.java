package brainitall.pixelly.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import brainitall.pixelly.R;
import brainitall.pixelly.controller.Manager;

import static android.graphics.Color.rgb;


public class PlayView extends SurfaceView implements SurfaceHolder.Callback, GestureDetector.OnGestureListener{

    // Elements Techniques
    private DrawingThread mThread;


    // Elements graphiques
    private SurfaceHolder mSurfaceHolder;
    private GestureDetector mDetector;
    private Paint mPaint;
    private int mLargeurGrilleCases;
    private float mLargeurGrille;
    private float mTailleSeparateur;
    private float mLargeurCellule;

    // Elements évènements CASES
    int mXInter, mYInter;       // Coord de la case intermédiaire


    //Message fin niveau
    private String mNomImageNiv;
    private String mTexteNextNiv;

    private float mXNomImg;
    private float mYNomImg;
    private float mXNextNiv;
    private float mYNextNiv;



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

        //Init texte fin
        mNomImageNiv = "";
        mTexteNextNiv = "";
    }

    public PlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
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

        //Position du texte fin niveau
        mXNomImg = w / 2;
        mYNomImg = h - 80;
        mXNextNiv = w /2;
        mYNextNiv = h - 30;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Nettoie le canvas
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);


        /* TRACE DES CHEMINS ==================================================================== */
        // Tracer à partir des coord du modele

        //Pour chaque chemin
        if(!Manager.getInstance().getLaGrille().getLesChemins().isEmpty())
        {
            for (int i = 0; i < Manager.getInstance().getLaGrille().getLesChemins().size(); i++) {

                //Pour chaque case
                for (int j = 0; j < Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().size() - 1; j++) {

                    //Case début trait
                    int x1 = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getX();
                    int y1 = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getY();
                    //Case fin trait
                    int x2 = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j + 1).getX();
                    int y2 = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j + 1).getY();


                    //Tracé du trait du centre de la case 1 vers celui de la case 2
                    float cX1 = (y1 + 1) * mLargeurCellule - mLargeurCellule / 2;
                    float cY1 = (x1 + 1) * mLargeurCellule - mLargeurCellule / 2;
                    float cX2 = (y2 + 1) * mLargeurCellule - mLargeurCellule / 2;
                    float cY2 = (x2 + 1) * mLargeurCellule - mLargeurCellule / 2;


                    // Dessin du trait
                    if (j == 0) {
                        int r = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getR();
                        int g = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getG();
                        int b = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getB();
                        mPaint.setColor(rgb(r, g, b));
                    }


                    mPaint.setStrokeWidth(50f);
                    canvas.drawLine(cX1, cY1, cX2, cY2, mPaint);


                }


                // Dessin des chemins complets
                if(Manager.getInstance().getLaGrille().getLesChemins().get(i).isTermine()){

                    for(int j=0; j<Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().size(); j++)
                    {

                        //Coordonnes case
                        int xCase = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getX();
                        int yCase = Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getY();

                        //Dessin du rectangle
                        canvas.drawRect(yCase * mLargeurCellule,
                                xCase * mLargeurCellule,
                                (yCase + 1) * mLargeurCellule,
                                (xCase + 1) * mLargeurCellule,
                                mPaint);
                    }
                }
            }

            /* GESTION DE LA FIN DU NIVEAU ------------------------------------- */
            if(Manager.getInstance().getLaGrille().niveauFini())
            {
                System.out.println(" ==========> FINI");
                mNomImageNiv = Manager.getInstance().getLaGrille().getNomGrille();
                mTexteNextNiv = "Nouveau niveau débloqué !";

            }
            else
            {
                mNomImageNiv = "";
                mTexteNextNiv = "";

            }

            mPaint.setTextSize(50);
            mPaint.setColor(Color.BLACK);
            canvas.drawText(mNomImageNiv, mXNomImg, mYNomImg, mPaint);
            mPaint.setTextSize(50);
            mPaint.setColor(Color.GREEN);
            canvas.drawText(mTexteNextNiv, mXNextNiv, mYNextNiv, mPaint);

            System.out.println(mNomImageNiv);
            System.out.println(mTexteNextNiv);
        }






        /* CREATION DE LA GRILLE (initiale) ===================================================== */
        float rayonCercle = mLargeurCellule / 2 - mLargeurCellule / 10;

        for (int y = 0; y < mLargeurGrilleCases; y++) {
            for (int x = 0; x < mLargeurGrilleCases; x++) {


                // POSITIONNEMENT DES TERMINAISONS (chiffre + couleur) --------------------------------

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

                    // Chiffre de la case
                    mPaint.setColor(Color.BLACK);
                    mPaint.setTextAlign(Paint.Align.CENTER);
                    mPaint.setTextSize(mLargeurCellule * 0.7f);

                    // Disparaissent à la fin du niveau
                    if(!Manager.getInstance().getLaGrille().niveauFini()) {
                        canvas.drawText("" + Manager.getInstance().getLaGrille().getLesTerminaisons().get(indice).getTailleChemin(),
                                x * mLargeurCellule + mLargeurCellule / 2,
                                y * mLargeurCellule + mLargeurCellule * 0.75f, mPaint);
                    }
                    else{
                        canvas.drawText("",
                                x * mLargeurCellule + mLargeurCellule / 2,
                                y * mLargeurCellule + mLargeurCellule * 0.75f, mPaint);
                    }
                }



                // SI SAUVEGARDE EXISTE -------------------------


            }
        }

        /* CREATION DES BORDURES ----------------------------------------- */
        for(int i=0; i<= mLargeurGrilleCases; i++ )
        {
            // Bordures épaisses aux extremités de la grilles
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

    private void touchStart(int x, int y) {

        //Initialisation des coord
        mXInter = x;
        mYInter = y;
    }

    private void touchMove(int x, int y) {

        // Si détectection d'un changement de coordonnées
        if(x != mXInter || y != mYInter)
        {
            System.out.println(" ============> IF CHANGEMENT ");
            System.out.println("CHEMIN : " + mXInter + "," + mYInter +"    "+ x + "," + y);

            //Manager.getInstance().getLaGrille().ajouterCaseChemin(mXInter, mYInter, x, y);
            Manager.getInstance().getLaGrille().modifierChemins(mXInter, mYInter, x, y);

            //MAJ coord
            mXInter = x;
            mYInter = y;

        }
    }

    private void touchUp(int x, int y) {

        System.out.println("Nb de chemins : "+Manager.getInstance().getLaGrille().getLesChemins().size());
        //Vérif affichage
        for(int i=0; i<Manager.getInstance().getLaGrille().getLesChemins().size(); i++){
            System.out.println("Chemin "+ (i+1) + ": ");
            for(int j=0; j<Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().size(); j++){
                System.out.print("("+Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getX() + "," + Manager.getInstance().getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getY()+")    ");
            }
            System.out.println();
        }




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Coord réelles
        float x = event.getX();
        float y = event.getY();

        // Convertir en coordonnées Grille
        int numColonne = (int)(x / mLargeurCellule);
        int numLigne = (int)(y / mLargeurCellule);


        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN :
                touchStart(numLigne, numColonne);
                break;

            // Vérification dépassement de la grille
            case MotionEvent.ACTION_MOVE :
                if(!(numLigne > mLargeurGrilleCases-1))
                    touchMove(numLigne, numColonne);
                break;

            case MotionEvent.ACTION_UP :
                touchUp(numLigne, numColonne);
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

        //Coord réelles
        float x = e.getX();
        float y = e.getY();

        // Converties en coordonnées Grille
        int numColonne = (int)(x / mLargeurCellule);
        int numLigne = (int)(y / mLargeurCellule);

        // Vérification dépassement de la grille
        if(!(numLigne > mLargeurGrilleCases-1))
            Manager.getInstance().supprimerChemin(numLigne, numColonne);
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




    public void interruptedThread(){
        mThread.interrupt();
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
                        // on dessine
                        draw(canvas);
                    }
                }
                finally {
                    // Notre dessin fini, on relâche le Canvas pour que le dessin s'affiche
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
                // Pour redessiner
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }

        // ---------------------------------------- GETTER & SETTER -----------------------------------------------



        public boolean isKeepDrawing() {
            return keepDrawing;
        }

        public void setKeepDrawing(boolean keepDrawing) {
            this.keepDrawing = keepDrawing;
        }
    }

    public String getNomImageNiv() {
        return mNomImageNiv;
    }

    public String getTexteNextNiv() {
        return mTexteNextNiv;
    }
}

