package brainitall.pixelly.model.metier;

import java.util.List;
import java.util.Vector;

/**
 * Classe représentant une Grille de jeu
 * @author Mélodie Meissel
 */
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

    /**
     * Constructeur par défaut
     */
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
     * @param xStart abcisse de la case départ
     * @param yStart ordonnée de la case de départ
     * @param x abcisse de la case d'arrivée
     * @param y ordonnée de la case d'arrivée
     */
    public void ajouterCaseChemin(int xStart, int yStart, int x, int y){
        // Si la case de départ ne se trouve pas déjà dans un chemin
        if(!isChemin(xStart,yStart)){
            // Si la case de départ est une terminaison
            if(mLesCases[xStart][yStart].isTerminaison()){
                // Création du chemin
                Terminaison t = (Terminaison) mLesCases[xStart][yStart];
                Chemin c = new Chemin(t);
                // Ajout du chemin à la liste des chemins connus par la grille
                mLesChemins.add(c);
                // Verifcation des coordonées de la case avant ajout + Ajout de la case au chemin
                if(verifierCoordonnees(xStart,yStart,x,y)){
                    mLesChemins.get(donneIndiceChemin(xStart,yStart)).getCasesChemin().add(mLesCases[x][y]);
                }
            }
        }
        else{
            // Verifcation des coordonées de la case avant ajout + Ajout de la case au chemin
            if(verifierCoordonnees(xStart,yStart,x,y)){
                mLesChemins.get(donneIndiceChemin(xStart,yStart)).getCasesChemin().add(mLesCases[x][y]);
            }
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

    /**
     * Permet d'ajouter un chemin dans la grille
     * @param c le chemin à ajouter
     */
    public void ajouterChemin(Chemin c){
        mLesChemins.add(c);
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

    /**
     * Permet de savoir si la case aux coordonnées (x,y) est dans un chemin ou non
     * @param x l'abcisse de la case
     * @param y l'ordonnée de la case
     * @return true si la case est dans un chemin, false sinon
     */
    public boolean isChemin(int x,int y){
        return donneIndiceChemin(x,y) != -1;
    }

    /**
     * Permet de vérifier si les coordonnées de case que l'on souhaite ajouter sont possibles ou non
     * @param xStart abcisse de la case de départ
     * @param yStart ordonnée de la case de départ
     * @param x abcisse de la case d'arrivée
     * @param y ordonnée de la case d'arrivée
     * @return true si les coordonnées sont possibles, false sinon
     */
    public boolean verifierCoordonnees(int xStart, int yStart, int x, int y){
        if(x == xStart+1 && y == yStart+1)
            return false;
        if(x == xStart+1 && y == yStart-1)
            return false;
        if(x == xStart-1 && y == yStart-1)
            return false;
        if(x == xStart-1 && y == yStart+1)
            return false;
        return true;
    }
    // --------------------------------------- GETTER & SETTER ---------------------------------------

    /**
     * Permet d'obtenir une case dans le tableau de case
     * @param x abcisse de la case que l'on souhaite obtenir
     * @param y ordonnée de la case que l'on souhaite obtenir
     * @return la case aux coordonnées (x,y)
     */
    public Case getCase(int x, int y){
        return mLesCases[x][y];
    }

    /**
     * Permet d'obtenir le numéro de la grille
     * @return le numéro de la grille
     */
    public int getNumGrille() {
        return mNumGrille;
    }

    /**
     * Permet d'obtenir la largeur de la grille
     * @return la largeur de la grille
     */
    public int getLargeurGrille() {
        return mLargeurGrille;
    }

    /**
     * Permet d'obtenir la hauteur de la grille
     * @return la hauteur de la grille
     */
    public int getHauteurGrille() {
        return mHauteurGrille;
    }

    /**
     * Permet d'obtenir le tableau de cases composant la grille
     * @return les cases
     */
    public Case[][] getLesCases() {
        return mLesCases;
    }

    /**
     * Permet d'obtenir la liste des chemins de la grille
     * @return la liste des chemins
     */
    public List<Chemin> getLesChemins() {
        return mLesChemins;
    }

    /**
     * Permet d'obtenir la liste des terminaisons
     * @return la liste des terminaisons
     */
    public List<Terminaison> getLesTerminaisons() {
        return mLesTerminaisons;
    }
}
