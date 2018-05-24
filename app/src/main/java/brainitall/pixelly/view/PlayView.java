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

import brainitall.pixelly.controller.PlayActivity;

import static android.graphics.Color.rgb;


/**
 * Android widget
 */
public class PlayView extends SurfaceView implements SurfaceHolder.Callback, GestureDetector.OnGestureListener{


    /* ATTRIBUTS D'INSTANCE ============================================================ */

    /**
     * Référence vers le controleur de cette vue (PlayActivity)
     */
    PlayActivity mController;

    // Elements Techniques ----------------------------------------------------------
    /**
     * Thread dans lequel le dessin se fera
     */
    private DrawingThread mThread;


    // Elements graphiques ---------------------------------------------------------
    /**
     * Couche d'abstraction pour dessiner
     */
    private SurfaceHolder mSurfaceHolder;
    /**
     * Le pinceau
     */
    private Paint mPaint;

    /**
     * Capture des évenements touch
     */
    private GestureDetector mDetector;


    // Eléments dimensions réelles Grille ---------------------------------------
    /**
     * Largeur de la grille en cases
     */
    private int mLargeurGrilleCases;
    /**
     * Largeur réelle de la grille
     */
    private float mLargeurGrille;
    /**
     * Largeur du trait
     */
    private float mTailleSeparateur;
    /**
     * Largeur réelle d'une case
     */
    private float mLargeurCellule;

    // Elements évènements CASES
    /**
     * Coordonnées x de la case intermédiaire
     */
    private int mXInter;
    /**
     * Coordonnées y de la case intermédiaire
     */
    private int mYInter;


    //Elements fin niveau --------------------------------------------------------
    /**
     * Indique si le niveau est terminé
     */
    private boolean estFini;

    /**
     * Nom de l'image pixel associée au niveau
     */
    private String mNomImageNiv;
    /**
     * Texte associé à la fin du niveau
     */
    private String mTexteNextNiv;
    /**
     * Positionnemnent réel en x du texte (nom de l'image)
     */
    private float mXNomImg;
    /**
     * Positionnemnent réel en y du texte (nom de l'image)
     */
    private float mYNomImg;
    /**
     * Positionnemnent réel en x du texte (débloquage niveau)
     */
    private float mXNextNiv;
    /**
     * Positionnemnent réel en y du texte (débloquage niveau)
     */
    private float mYNextNiv;




    /* CONSTRUCTEUR ================================++++++============================ */
    /**
     * Constructeur de PlayView utilisé pour construire la vue en Java
     * @param context contexte hébergeant la vue
     */
    public PlayView(Context context) {
        super(context);
        mController = (PlayActivity) context;
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
        estFini = false;
        mNomImageNiv = "";
        mTexteNextNiv = "";
    }

    /**
     * Controleur de PlayView pour construire la vue depuis XML sans style
     * @param context le contexte qui héberge la vue
     * @param attrs les attributs définis en xml
     */
    public PlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mController = (PlayActivity) context;
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





    /* METHODES ======================================================================== */

    // ----------------------- METHODES LIEES A LA SURFACEVIEW -------------------------


    @Override
    /**
     * Appelée quand la taille de la vue change
     * @param w largeur de l'écran
     * @param h hauteur de l'écran
     * @param oldw ancienne largeur
     * @param oldh ancienne hauteur
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Calculs des différentes tailles
        mLargeurGrilleCases = mController.getLaGrille().getLargeurGrille();
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
    /**
     * Dessine chaque élement graphique sur le canvas avec le pinceau
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Nettoie le canvas
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);


        /* TRACE DES CHEMINS -------------------------------------------------------------------- */
        // Tracer à partir des coord du modele

        //Pour chaque chemin
        if(!mController.getLaGrille().getLesChemins().isEmpty())
        {
            for (int i = 0; i < mController.getLaGrille().getLesChemins().size(); i++) {

                //Pour chaque case
                for (int j = 0; j < mController.getLaGrille().getLesChemins().get(i).getCasesChemin().size() - 1; j++) {

                    //Case début trait
                    int x1 = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getX();
                    int y1 = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getY();
                    //Case fin trait
                    int x2 = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j + 1).getX();
                    int y2 = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j + 1).getY();


                    //Tracé du trait du centre de la case 1 vers celui de la case 2
                    float cX1 = (y1 + 1) * mLargeurCellule - mLargeurCellule / 2;
                    float cY1 = (x1 + 1) * mLargeurCellule - mLargeurCellule / 2;
                    float cX2 = (y2 + 1) * mLargeurCellule - mLargeurCellule / 2;
                    float cY2 = (x2 + 1) * mLargeurCellule - mLargeurCellule / 2;


                    // Dessin du trait
                    if (j == 0) {
                        int r = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getR();
                        int g = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getG();
                        int b = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getB();
                        mPaint.setColor(rgb(r, g, b));
                    }


                    mPaint.setStrokeWidth(50f);
                    canvas.drawLine(cX1, cY1, cX2, cY2, mPaint);


                }


                // Dessin des chemins complets
                if(mController.getLaGrille().getLesChemins().get(i).isTermine()){

                    for(int j=0; j<mController.getLaGrille().getLesChemins().get(i).getCasesChemin().size(); j++)
                    {

                        //Coordonnes case
                        int xCase = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getX();
                        int yCase = mController.getLaGrille().getLesChemins().get(i).getCasesChemin().get(j).getY();

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
            if(mController.getLaGrille().niveauFini())
            {
                estFini = true;
                mNomImageNiv = mController.getLaGrille().getNomGrille();
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

        }






        /* CREATION DE LA GRILLE (initiale) ------------------------------------------------------*/
        float rayonCercle = mLargeurCellule / 2 - mLargeurCellule / 10;

        for (int y = 0; y < mLargeurGrilleCases; y++) {
            for (int x = 0; x < mLargeurGrilleCases; x++) {


                // POSITIONNEMENT DES TERMINAISONS (chiffre + couleur) --------------------------------

                // Si la case courante est une terminaison
                if (mController.getLaGrille().getCase(y, x).isTerminaison()) {

                    // Recherche de l'indice dans la liste des terminaisons
                    int indice = mController.getLaGrille().donneIndiceTerminaison(y,x);

                    // Couleur de la case
                    int r = mController.getLaGrille().getLesTerminaisons().get(indice).getR();
                    int g = mController.getLaGrille().getLesTerminaisons().get(indice).getG();
                    int b = mController.getLaGrille().getLesTerminaisons().get(indice).getB();
                    mPaint.setColor(rgb(r, g, b));

                    // CERCLE si taille de la terminaison != 1
                    if( mController.getLaGrille().getLesTerminaisons().get(indice).getTailleChemin() != 1) {
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
                    if(!mController.getLaGrille().niveauFini()) {
                        canvas.drawText("" + mController.getLaGrille().getLesTerminaisons().get(indice).getTailleChemin(),
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
    /**
     * Appelée à la création de la surface et permet de commencer à dessiner
     * @param holder la surface sur laquelle on dessine
     */
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.setKeepDrawing(true);
        mThread.start();
    }

    @Override
    /**
     * Appelé quand la surface change - maj l'image (ex si on tourne le téléphone)
     * @param holder la surface sur laquelle on dessine
     * @param format le format PixelFormat
     * @param width largeur
     * @param heigh hauteur
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    /**
     * Appelée dès que la surface est détruite (utile pour quand arreter le thread) et bloque la surface
     * @param holder la surface sur laquelle on dessine
     */
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

    @Override
    /**
     * Détecte les actions utilisateur quand qui intéragit avec l'écran
     * @param event évenement touch
     */
    public boolean onTouchEvent(MotionEvent event) {

        //Coord réelles
        float x = event.getX();
        float y = event.getY();

        // Convertir en coordonnées Grille
        int numColonne = (int)(x / mLargeurCellule);
        int numLigne = (int)(y / mLargeurCellule);


        switch(event.getAction())
        {
            // Premier appui
            case MotionEvent.ACTION_DOWN :
                touchStart(numLigne, numColonne);
                break;

            // Slide
            case MotionEvent.ACTION_MOVE :
                // Vérification dépassement de la grille
                if(!(numLigne > mLargeurGrilleCases-1))
                    touchMove(numLigne, numColonne);
                break;

            // Fin
            case MotionEvent.ACTION_UP :
                break;
        }

        return mDetector.onTouchEvent(event);
    }


    /**
     * Initialise les coordonnées grille de la case courante détectée
     * @param x coordonnée x
     * @param y coordonnée y
     */
    private void touchStart(int x, int y) {

        //Initialisation des coord
        mXInter = x;
        mYInter = y;
    }

    /**
     * Met à jour les coordonnées de la case courante détectée lors d'un changement de coordonnées grille
     * @param x coordonnée x
     * @param y coordonnée y
     */
    private void touchMove(int x, int y) {

        // Si détectection d'un changement de coordonnées
        if(x != mXInter || y != mYInter)
        {
            //Manager.getInstance().getLaGrille().ajouterCaseChemin(mXInter, mYInter, x, y);
            mController.getLaGrille().modifierChemins(mXInter, mYInter, x, y);

            //MAJ coord
            mXInter = x;
            mYInter = y;
        }
    }







    @Override
    /**
     * Détecte un appui
     * @param e event
     */
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    /**
     * Détecte un appui sans avoir bougé ni enlévé son doigt
     * @param e event
     */
    public void onShowPress(MotionEvent e) {

    }

    @Override
    /**
     * Détecte un seul appui (down puis up)
     * @param e event
     */
    public boolean onSingleTapUp(MotionEvent e) {

        // On bloque le tactil si le niveau est terminé
        if(!estFini) {
            //Coord réelles
            float x = e.getX();
            float y = e.getY();

            // Converties en coordonnées Grille
            int numColonne = (int) (x / mLargeurCellule);
            int numLigne = (int) (y / mLargeurCellule);

            // Vérification dépassement de la grille
            if (!(numLigne > mLargeurGrilleCases - 1))
                mController.supprimerChemin(numLigne, numColonne);
        }

        return true;
    }

    @Override
    /**
     * Détecte le mouvement de l'évenement initiale et l'evement courant
     * @param e1 evenement initiale
     * @param e2 evenement courant
     * @param distanceX distance en X parcourue
     * @param distanceY distance en Y parcourue
     */
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    /**
     * Détecte une longue pression
     * @param e evenement
     */
    public void onLongPress(MotionEvent e) {

    }

    @Override
    /**
     * Détecte un mouvement de 'lancé'
     * @param e evenement
     */
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    /**
     * Interrompt le thread
     */
    public void interruptedThread(){
        mThread.interrupt();
    }






    // ---------------------------------- CLASSE INTERNE : THREAD -----------------------

    /**
     * Permet d'arrêter le dessin quand il le faut
     */
    public class DrawingThread extends Thread {

        private boolean keepDrawing = true;

        @Override
        /**
         *
         */
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
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                }
            }
        }


        /**
         * Modifier
         * @param keepDrawing boolean
         */
        public void setKeepDrawing(boolean keepDrawing) {
            this.keepDrawing = keepDrawing;
        }
    }




}

