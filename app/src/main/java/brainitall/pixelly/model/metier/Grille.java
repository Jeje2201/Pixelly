package brainitall.pixelly.model.metier;

import java.util.List;
import java.util.Vector;

public class Grille {
    /**
     * Numéro de la grille (niveau)
     */
    private int mNumGrille;
    /**
     * Largeur de la grille
     */
    private int mLargeurGrille;
    /**
     * Hauteur de la grille
     */
    private int mHauteurGrille;
    /**
     * Tableau de cases représentant la grille
     */
    private Case[][] mLesCases;
    /**
     * Liste des chemins contenus dans la grille
     */
    private List<Chemin> mLesChemins;

    /**
     * Liste des différentes terminaisons de la grille
     */
    private List<Terminaison> mLesTerminaisons;

    public Grille(){
        mNumGrille = 0;
        mLargeurGrille = 1;
        mHauteurGrille = 1;
        initCases();
        initLesChemins();
        initLesTerminaisons();
    }


    // Constructeur à appeler pour créer la grille après lecture des 3 premières lignes du fichier JSon
    public Grille(int numGrille, int largeurGrille, int hauteurGrille) {
        mNumGrille = numGrille;
        mLargeurGrille = largeurGrille;
        mHauteurGrille = hauteurGrille;
        initCases();
        initLesChemins();
        initLesTerminaisons();
    }

    // ----------------------------------------- Initialisations -------------------------------------

    /**
     * Permet d'initialiser le tableau de cases représentant la grille
     */
    private void initCases(){
        mLesCases = new Case[mHauteurGrille][mLargeurGrille];
        for(int i = 0; i< mHauteurGrille;i++){
            for(int j = 0; j < mLargeurGrille ;j++){
                mLesCases[i][j] = new CaseSimple(i,j);
            }
        }
    }

    /**
     * Permet d'initialiser la liste des chemins connus par la grille
     */
    private void initLesChemins() {
        mLesChemins = new Vector<>();
    }

    /**
     * Permet d'initialiser la liste des terminaisons de la grille
     */
    private void initLesTerminaisons() {
        mLesTerminaisons = new Vector<>();
    }

    // ---------------------------------------- Ajouts / Suppressions --------------------------------


    /**
     * Permet d'ajouter une case à un chemin
     * @param x l'abcisse de la case
     * @param y l'ordonnée de la case
     */
    public void ajouterCaseChemin(int x, int y){
        if(donneIndiceChemin(x,y) == -1){

        }

    }

    /**
     * Permet de supprimer la Case d'un chemin
     * @param x l'abcisse de la case
     * @param y l'ordonnée de la case
     */
    public void supprimerCaseChemin(int x, int y){

    }

    /**
     * Permet de supprimer un chemin dans sa totalité
     * @param x abcisse de l'une des terminaison du chemin
     * @param y ordonnée de l'une des terminaison du chemin
     */
    public void supprimerChemin(int x, int y){
        // Recherche l'index du chemin dans la liste des chemins connus par la grille
        int index = donneIndiceChemin(x,y);
        // Si le chemin existe :
        if(index != -1 ){
            // Si les coordonnées correspondent à une terminaison, alors on supprime tout le chemin
            if(isTerminaison(mLesChemins.get(index).getCaseChemin(x,y))){
                mLesChemins.get(index).supprimerTout();
                // On supprime le chemin vide de la liste des chemins connus par la grille
                mLesChemins.remove(index);
            }
        }
    }

    // Methode à utiliser pour ajouter les terminaisons lues dans le fichier Json
    public void ajouterTerminaison(int tailleChemin, int x, int y, int r, int g, int b){
        Terminaison t =  new Terminaison(tailleChemin, x,y,r,g,b);
        mLesCases[x][y] = t;
        mLesTerminaisons.add(t);
    }

    // ------------------------------------ Recherches -----------------------------------------------

    /**
     * Permet d'obtenir l'indice d'une chemin dans la liste des chemins à partir des coordonnées d'une case
     * @param x abcisse de la case
     * @param y ordonnée de la case
     * @ la position du chemin dans la liste des différents chemins
     */
    public int donneIndiceChemin(int x, int y){
        int index = -1;
        for(int i = 0; i < mLesChemins.size(); i++){
            if(mLesChemins.get(i).isCaseChemin(x,y)){
                index = i;
            }
        }
        return index;
    }

    /**
     * Permet d'obtenir l'indice d'une terminaison à partir de ses coordonnées
     * @param x abcisse de la case
     * @param y ordonnée de la case
     * @return l'indice de la terminaison dans la liste des terminaisons
     */
    public int donneIndiceTerminaison(int x, int y){
        int index = -1;
        for(int i =0; i < mLesTerminaisons.size(); i++){
            if(mLesTerminaisons.get(i).getY() == y && mLesTerminaisons.get(i).getX() == x){
                index = i;
            }
        }
        return index;
    }

    // --------------------------------------- Verifications ----------------------------------------

    /**
     * Permet de savoir si la case est une terminaison ou non
     * @param c la case
     * @return true si c'est une terminaison, false sinon
     */
    public boolean isTerminaison(Case c){
        return c.isTerminaison();
    }


    // --------------------------------------- GETTER & SETTER ---------------------------------------

    public Case getCase(int x, int y){
        return mLesCases[x][y];
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

    public List<Chemin> getLesChemins() {
        return mLesChemins;
    }

    public void setLesChemins(List<Chemin> lesChemins) {
        mLesChemins = lesChemins;
    }

    public List<Terminaison> getLesTerminaisons() {
        return mLesTerminaisons;
    }

    public void setLesTerminaisons(List<Terminaison> lesTerminaisons) {
        mLesTerminaisons = lesTerminaisons;
    }
}
