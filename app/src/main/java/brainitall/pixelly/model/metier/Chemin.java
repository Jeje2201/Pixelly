package brainitall.pixelly.model.metier;


import java.util.List;
import java.util.Vector;

/**
 * Classe représentant un Chemin
 * @author Loïc Ezrati, Mélodie Meissel
 */
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
     * Contructeur de la classe Chemin
     * @param terminaison
     * @author Loïc Ezrati
     */
    public Chemin(Terminaison terminaison){
        mTailleMax = terminaison.getTailleChemin();
        mCasesChemin = new Vector<>();
        mCasesChemin.add(terminaison);
        terminaison.setDansChemin(true);
    }



    /**
     * Permet d'ajouter une case dans la liste des cases d'un chemin
     * @param c la case à ajouter
     * @author Loïc Ezrati
     */
    public void ajouterCase(Case c){
        if(verifierTaille()){
            c.setDansChemin(true);
            mCasesChemin.add(c);
        }
    }

    /**
     * Permet de supprimer la dernière case du chemin
     * @author Loïc Ezrati
     */
    public void supprimerCase(){
        if(mCasesChemin.size()>0){
            mCasesChemin.get(mCasesChemin.size()-1).setDansChemin(false);
            mCasesChemin.remove(mCasesChemin.size()-1);

        }
    }

    /**
     * Méthode qui supprime un chemin de case tout entier
     * @author Loïc Ezrati
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
     * @return true si la liste des cases chemin est inférieure à la taille max du chemin
     * @author Loïc Ezrati
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
     * Permet d'obtenir la première terminaison qui crée le chemin
     * @return la première terminaison du chemin
     */
    public Terminaison getPremiereTerminaison(){
        return (Terminaison) mCasesChemin.get(0);
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


    /**
     * Permet de savoir si le chemin est complet ou non
     * @return true si le chemin est complet, false sinon
     */
    public boolean isComplet(){
        return (mCasesChemin.size() == mTailleMax) && (mCasesChemin.get(mCasesChemin.size()-1).isTerminaison());
    }

    /**
     * Permet de savoir si un t
     * @param xStart
     * @param yStart
     * @param x
     * @param y
     */
    public void isExistant(int xStart, int yStart, int x, int y){

    }

    // ---------------------------------------------- GETTER & SETTER ----------------------------------------

    /**
     * Permet d'obtenir la couleur du chemin
     * @return un tableau d'entier représentant la couleur sous forme de rgb
     * @author Mélodie Meissel
     */
    public int[] getCouleurChemin(){
        int [] lesCouleurs = new int[3];
        lesCouleurs[0] = mCasesChemin.get(0).getR();
        lesCouleurs[1] = mCasesChemin.get(0).getG();
        lesCouleurs[2] = mCasesChemin.get(0).getB();
        return lesCouleurs;
    }

    /**
     * Permet d'obtenir la liste des cases contenues dans le chemin
     * @return la liste des cases contenues dans le chemin
     * @author Loïc Ezrati
     */
    public List<Case> getCasesChemin() {
        return mCasesChemin;
    }

    /**
     * Permet d'obtenir la taille maximale du chemin
     * @return la taille maximale du chemin
     * @author Loïc Ezrati
     */
    public int getTailleMax() {
        return mTailleMax;
    }


}
