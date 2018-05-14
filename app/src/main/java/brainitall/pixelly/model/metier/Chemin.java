package brainitall.pixelly.model.metier;


import java.util.List;
import java.util.Vector;

public class Chemin {

    /**
     * taille max du chemin
     */
    private int mTailleMax;

    /**
     * liste des cases comprises dans un chemin
     */
    private List<Case> mCasesChemin;

    /**
     * contructeur de la classe Chemin
     * @param terminaison
     */
    public Chemin(Terminaison terminaison){
        mTailleMax = terminaison.getTailleChemin();
        mCasesChemin = new Vector<>();
        mCasesChemin.add(terminaison);
        terminaison.setDansChemin(true);
    }

    /**
     * méthode qui ajoute une case dans le chemin si
     * verifierTaille est vrai
     * @param c
     */
    public void ajouterCase(Case c){
        if(verifierTaille()){
            c.setDansChemin(true);
            mCasesChemin.add(c);
        }
    }

    /**
     * méthode qui supprime la dernière case du chemin
     */
    public void supprimerCase(){
        if(mCasesChemin.size()>0){
            mCasesChemin.get(mCasesChemin.size()-1).setDansChemin(false);
            mCasesChemin.remove(mCasesChemin.size()-1);

        }
    }

    /**
     * méthode qui supprime un chemin de case tout entier
     */
    public void supprimerTout(){
        if(mCasesChemin.size()>0){
            for(Case c : mCasesChemin){
                c.setDansChemin(false);
            }
            mCasesChemin.removeAll(mCasesChemin);
        }
    }

    /**
     * booléen qui retourne vrai si le chemin de case est plus
     * petit que la taille maximale possible du chemin
     * @return
     */
    public boolean verifierTaille(){
        return mTailleMax > mCasesChemin.size();
    }

    /**
     * Permet d'obtenir la case contenue dans le chemin
     * @param x l'abcisse de la case dans la grille
     * @param y l'ordonnée de la case dans la grille
     * @return la case recherchée
     */
    public Case getCaseChemin(int x, int y){
        Case laCase = null;
        if(isCaseChemin(x,y)){
            laCase = mCasesChemin.get(getIndexCase(x,y));
        }
        return laCase;
    }

    /**
     * Permet d'obtenir l'index de la case dans le chemin
     * @param x l'abcisse de la case dans la grille
     * @param y l'ordonnée de la case dans la grikke
     * @return l'index de la case dans le chemin
     */
    public int getIndexCase(int x, int y){
        int index = -1;
        for(int i = 0; i < mCasesChemin.size(); i++){
            if(mCasesChemin.get(i).getX() == x && mCasesChemin.get(i).getY() == y){
                index = i;
            }
        }
        return index;
    }

    /**
     * Permet de savoir si la case aux coordonnées (x,y) fait partie du chemin ou non
     * @param x l'abcisse de la case de la grille
     * @param y l'ordonnée de la case de la grille
     * @return true si elle fait partie de ce chemin, false sinon
     */
    public boolean isCaseChemin(int x, int y){
        for(Case c : mCasesChemin){
            if(c.getX() == x && c.getY() == y)
                return true;
        }
        return false;
    }
}
