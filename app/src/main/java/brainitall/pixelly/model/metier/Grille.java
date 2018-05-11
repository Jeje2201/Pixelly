package brainitall.pixelly.model.metier;

public class Grille {
    private int mNumGrille;
    private int mLargeurGrille;
    private int mHauteurGrille;
    private Case[][] mLesCases;

    public Grille(){
        mNumGrille = 0;
        mLargeurGrille = 1;
        mHauteurGrille = 1;
        initCases();
    }

    public Grille(int numGrille, int largeurGrille, int hauteurGrille) {
        mNumGrille = numGrille;
        mLargeurGrille = largeurGrille;
        mHauteurGrille = hauteurGrille;
        initCases();
    }

    private void initCases(){
        mLesCases = new Case[mHauteurGrille][mLargeurGrille];
        for(int i = 0; i< mHauteurGrille;i++){
            for(int j = 0; j < mLargeurGrille ;j++){
                mLesCases[i][j] = new CaseVide(i,j);
            }
        }
    }

    public void ajouterTerminaison(int tailleChemin, int x, int y, int r, int g, int b){
        mLesCases[x][y] = new Terminaison(tailleChemin, x,y,r,g,b);
    }
}
