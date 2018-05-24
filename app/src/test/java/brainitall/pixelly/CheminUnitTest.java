package brainitall.pixelly;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import brainitall.pixelly.model.metier.Chemin;
import brainitall.pixelly.model.metier.Terminaison;

public class CheminUnitTest {

    //                        Test unitaire placé en commentaire



    /**
     * taille max du chemin
     */
    private int mTailleMax ;

    /**
     * liste des cases comprises dans un chemin
     */
     private List<Integer> mCasesChemin;

    public CheminUnitTest(Terminaison terminaison){
       this.mTailleMax=6;

    }

    /**
     * méthode de test de la suppresion de case de la classe Chemin
     */
   // @Test
   /* public void testSupprimerCase() throws Exception {

        mCasesChemin.add(1);
        mCasesChemin.add(2);
        mCasesChemin.add(3);
        System.out.println(mCasesChemin); // affichage du chemin avant la suppression d'une case
        Chemin chemin = new Chemin(5);
        chemin.supprimerCase();
        System.out.println(mCasesChemin); // affichage du chemin après la suppresion d'une case
        Assert.assertArrayEquals(chemin.supprimerCase());
    }*/


    /**
     * méthode de test de l'ajout de case de la classe Chemin
     */
   /* @Test
    public void testAjouterCase(){
        mCasesChemin.add(1);
        mCasesChemin.add(2);
        mCasesChemin.add(3);
        System.out.println(mCasesChemin);
        Chemin c = new Chemin();
        c.ajouterCase();
        System.out.println(mCasesChemin);
        Assert.assertArrayEquals(c.ajouterCase());
    }*/

    /**
     * retourne vrai si la taille du chemin est inférieur
     * à la taille maximale
     * @return mTailleMax > mCasesChemin.size();
     */
    @Test
    public boolean testVerifTaille(){
        return mTailleMax > mCasesChemin.size();

    }


    /**
     * méthode test de la classe Chemin qui va vérifier
     */
    /*
    @Test
    public boolean testVerifierDerniereCase(int x, int y){
        return (getIndexCase(x,y) == mCasesChemin.size()-1);
    }*/


}

