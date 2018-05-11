package brainitall.pixelly.model.metier;

public class Terminaison extends Case {

    /**
     * taille du chemin compris entre deux terminaisons
     */
    private int mTailleChemin;


    /**
     * constructeur de la classe Terminaison
     * @param tailleChemin
     * @param x
     * @param y
     * @param r
     * @param g
     * @param b
     */
    public Terminaison(int tailleChemin, int x, int y, int r, int g, int b) {
        super(x,y,r,g,b);
        mTailleChemin = tailleChemin;
    }

    /**
     *  getter qui retourne la valeur de la taille du chemin
     *  @return mTailleChemin
     */
    public int getTailleChemin() {
        return mTailleChemin;
    }

    /**
     * setter qui permet de modifier la taille du chemin
     * @param tailleChemin
     */
    public void setTailleChemin(int tailleChemin) {
        mTailleChemin = tailleChemin;
    }

}

