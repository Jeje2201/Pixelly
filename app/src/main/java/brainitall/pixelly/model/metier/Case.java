package brainitall.pixelly.model.metier;

/**
 * Classe abstraite représentant une case
 * @author Loïc Ezrati
 */
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
     * @param r le code du rouge
     * @param g le code du vert
     * @param b le code du bleu
     * @param x abcisse de la case dans la grille
     * @param y ordonnée de la case dans la grille
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
     * constructeur de la classe Case utilisé dans la classe CaseSimple
     * @param x abcisse de la case dans la grille
     * @param y ordonnée de la case dans la grille
     */
    public Case(int x, int y) {
        mR = 0;
        mG = 0;
        mB = 0;
        mX = x;
        mY = y;
        mDansChemin = false;
    }

    // --------------------------------------- GETTER & SETTER -----------------------------------------
    /**
     * Permet d'obtenir le code du rouge
     * @return le code du rouge
     */
    public int getR() {
        return mR;
    }

    /**
     * pemet de modifier le code du rouge
     * @param r le nouveau code du rouge
     */
    public void setR(int r) {
        mR = r;
    }


    /**
     * Permet d'obtenir le code du vert
     * @return le code du vert
     */
    public int getG() {
        return mG;
    }

    /**
     * Permet de modifier le code du vert
     * @param g le nouveau code du vert
     */
    public void setG(int g) {
        mG = g;
    }

    /**
     * Permet d'obtenir le code du bleu
     * @return le code du bleu
     */
    public int getB() {
        return mB;
    }

    /**
     * Permet de modifier le code du bleu
     * @param b le nouveau code du bleu
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
     * getter mY
     * @return mY
     */
    public int getY() {
        return mY;
    }

    /**
     * Permet de savoir si une case est dans un chemin ou non
     * @return true si la case est dans un chemin, false sinon
     */
    public boolean isDansChemin() {
        return mDansChemin;
    }

    /**
     * Permet de modifier l'état de la case
     * @param dansChemin le nouvel état (true est dans un chemin, false sinon)
     */
    public void setDansChemin(boolean dansChemin) {
        mDansChemin = dansChemin;
    }

    /**
     * Permet de savoir si la case est une terminaison ou non
     * @return true si c'est une terminaison, false sinon
     */
    public boolean isTerminaison() {
        return mTerminaison;
    }

    /**
     * Permet de modifier l'état de la case
     * @param terminaison le nouvel état (true = est une terminaison, false sinon)
     */
    public void setTerminaison(boolean terminaison) {
        mTerminaison = terminaison;
    }
}
