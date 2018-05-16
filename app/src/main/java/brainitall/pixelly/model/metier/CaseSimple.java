package brainitall.pixelly.model.metier;

public class CaseSimple extends Case {

    /**
     * constructeur de la classe CaseSimple
     * qui positionne la case dans la grille, lui donne
     * une couleur blanche et la case n'est pas dans un chemin
     * @param x
     * @param y
     */
    public CaseSimple(int x, int y){

        super(x,y);
    }

    public void setRGB(int r, int g, int b){
        setR(r);
        setB(b);
        setG(g);
    }
}
