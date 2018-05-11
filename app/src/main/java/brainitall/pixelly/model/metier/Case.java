package brainitall.pixelly.model.metier;

public abstract class Case {

    /**
     * code couleur informatique représentant du rouge
     * @param mR
     */
    private int mR;

    /**
     * code couleur informatique représentant du vert
     * @param mG
     */
    private int mG;

    /**
     * code couleur informatique représentant du bleu
     * @param mB
     */
    private int mB;
    /**
     * coordonnées x d'une case
     *@param mX
     */
    private int mX;

    /**
     * coordonnées y d'une case
     * @param mY
     */
    private int mY;

    /**
     * boolean pour savoir si une case est dans un chemin
     * @param mDansChemin
     */
    private boolean mDansChemin;


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
    }

<<<<<<< HEAD
    /**
     * constructeur de la classe Case utilisé dans la classe Terminaison
     * @param r
     * @param g
     * @param b
     * @param x
     * @param y
     */
    public Case(int r, int g, int b, int x, int y) {
=======
    public Case(int x, int y, int r, int g, int b) {
>>>>>>> 5ff581d69530b52f1599c67a89b5ecf7ac899f19
        mR = r;
        mG = g;
        mB = b;
        mX = x;
        mY = y;
        mDansChemin = false;
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
}
