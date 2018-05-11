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
}
