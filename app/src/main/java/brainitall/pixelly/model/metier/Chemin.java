package brainitall.pixelly.model.metier;


import java.util.List;
import java.util.Vector;

public class Chemin {
    private int mTailleMax;
    private List<Case> mCasesChemin;

    public Chemin(Terminaison terminaison){
        mTailleMax = terminaison.getTailleChemin();
        mCasesChemin = new Vector<>();
        mCasesChemin.add(terminaison);
        terminaison.setDansChemin(true);
    }

    public void ajouterCase(Case c){
        if(verifierTaille()){
            c.setDansChemin(true);
            mCasesChemin.add(c);
        }
    }

    public void supprimerCase(){
        if(mCasesChemin.size()>0){
            mCasesChemin.get(mCasesChemin.size()-1).setDansChemin(false);
            mCasesChemin.remove(mCasesChemin.size()-1);

        }
    }

    public void supprimerTout(){
        if(mCasesChemin.size()>0){
            for(Case c : mCasesChemin){
                c.setDansChemin(false);
            }
            mCasesChemin.removeAll(mCasesChemin);
        }
    }

    public boolean verifierTaille(){
        return mTailleMax > mCasesChemin.size();
    }
}
