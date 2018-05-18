package brainitall.pixelly.model.metier;

/**
 * Classe représentant une Terminaison
 * @author Loïc Ezrati
 */
public class Terminaison extends Case {

    /**
     * taille du chemin compris entre deux terminaisons
     */
    private int mTailleChemin;


    /**
     * constructeur de la classe Terminaison
     * @param tailleChemin la taille du chemin que porte la terminaison
     * @param x l'abcisse de la terminaison
     * @param y l'ordonnée de la terminaison
     * @param r le code du rouge
     * @param g le code du vert
     * @param b le code du bleu
     */
    public Terminaison(int tailleChemin, int x, int y, int r, int g, int b) {
        super(x,y,r,g,b);
        mTailleChemin = tailleChemin;
        setTerminaison(true);
    }

    /**
     *  Permet d'obtenir la taille du chemin
     *  @return mTailleChemin la taille du chemin
     */
    public int getTailleChemin() {
        return mTailleChemin;
    }

}

