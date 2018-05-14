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

    // Constructeur à appeler pour créer la grille après lecture des 3 premières lignes du fichier JSon
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

    // Methode à utiliser pour ajouter les terminaisons lues dans le fichier Json
    public void ajouterTerminaison(int tailleChemin, int x, int y, int r, int g, int b){
        mLesCases[x][y] = new Terminaison(tailleChemin, x,y,r,g,b);
    }

    public int getNumGrille() {
        return mNumGrille;
    }

    public void setNumGrille(int numGrille) {
        mNumGrille = numGrille;
    }

    public int getLargeurGrille() {
        return mLargeurGrille;
    }

    public void setLargeurGrille(int largeurGrille) {
        mLargeurGrille = largeurGrille;
    }

    public int getHauteurGrille() {
        return mHauteurGrille;
    }

    public void setHauteurGrille(int hauteurGrille) {
        mHauteurGrille = hauteurGrille;
    }

    public Case[][] getLesCases() {
        return mLesCases;
    }

    public void setLesCases(Case[][] lesCases) {
        mLesCases = lesCases;
    }
}
