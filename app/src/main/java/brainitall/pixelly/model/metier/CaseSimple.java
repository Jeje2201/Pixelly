package brainitall.pixelly.model.metier;

/**
 * Classe représentant une case simple
 * @author Loïc Ezrati
 */
public class CaseSimple extends Case {

    /**
     * constructeur de la classe CaseSimple
     * qui positionne la case dans la grille, lui donne
     * une couleur blanche et la case n'est pas dans un chemin
     * @param x l'abcisse de la case simple
     * @param y l'ordonnée de la case simple
     */
    public CaseSimple(int x, int y){
        super(x,y);
    }

    /**
     * Permet de modifier la couleur de la case Simple
     * @param r le code du rouge
     * @param g le code du vert
     * @param b le code du bleu
     */
    public void setRGB(int r, int g, int b){
        setR(r);
        setB(b);
        setG(g);
    }
}
