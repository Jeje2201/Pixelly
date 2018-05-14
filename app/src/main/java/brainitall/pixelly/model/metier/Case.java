package brainitall.pixelly.model.metier;

public abstract class Case {

    /**
     * code couleur informatique représentant du rouge
     */
    private int mR;

    /**
     * code couleur informatique représentant du vert
     */
    private int mG;

    /**
     * code couleur informatique représentant du bleu
     */
    private int mB;
    /**
     * coordonnées x d'une case
     */
    private int mX;

    /**
     * coordonnées y d'une case
     */
    private int mY;

    /**
     * boolean pour savoir si une case est dans un chemin
     */
    private boolean mDansChemin;

    /**
     * Permet de savoir s'il s'agit d'une case Terminaison ou non
     */
    private boolean mTerminaison;


    /**
     * constructeur par default de la classe Case
     */
    public Case(){
        mR = 0;
        mG = 0;
        mB = 0;
        mX = 0;
        mY = 0;
        mDansChemin = false;
        mTerminaison = false;

    }


    /**
     * constructeur de la classe Case utilisé dans la classe Terminaison
     * @param r
     * @param g
     * @param b
     * @param x
     * @param y
     */
    public Case(int x, int y, int r, int g, int b) {
        mR = r;
        mG = g;
        mB = b;
        mX = x;
        mY = y;
        mDansChemin = false;
        mTerminaison = false;
    }


    /**
     * constructeur de la classe Case utilisé dans la classe CaseVide
     * @param x
     * @param y
     */
    public Case(int x, int y) {
        mR = 0;
        mG = 0;
        mB = 0;
        mX = x;
        mY = y;
        mDansChemin = false;
    }

    /**
     * getter mR
     * @return mR
     */
    public int getR() {
        return mR;
    }

    /**
     * setter mR
     * @param r
     */
    public void setR(int r) {
        mR = r;
    }


    /**
     * getter mG
     * @return mG
     */
    public int getG() {
        return mG;
    }

    /**
     * setter mG
     * @param g
     */
    public void setG(int g) {
        mG = g;
    }

    /**
     * getter mB
     * @return mB
     */
    public int getB() {
        return mB;
    }

    /**
     * setter mB
     * @param b
     */
    public void setB(int b) {
        mB = b;
    }

    /**
     * getter mX
     * @return mX
     */
    public int getX() {
        return mX;
    }

    /**
     * setter mX
     * @param x
     */
    public void setX(int x) {
        mX = x;
    }

    /**
     * getter mY
     * @return mY
     */
    public int getY() {
        return mY;
    }

    /**
     * setter mY
     * @param y
     */
    public void setY(int y) {
        mY = y;
    }

    /**
     *
     * @return mDansChemin
     */
    public boolean isDansChemin() {
        return mDansChemin;
    }

    /**
     * setter dansChemin
     * @param dansChemin
     */
    public void setDansChemin(boolean dansChemin) {
        mDansChemin = dansChemin;
    }

    /**
     *
     * @return
     */
    public boolean isTerminaison() {
        return mTerminaison;
    }

    public void setTerminaison(boolean terminaison) {
        mTerminaison = terminaison;
    }
}
