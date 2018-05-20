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

    // --------------------------------------- Gestion du jeu ----------------------------------------

    /**
     * Permet de modifier un chemin : soit ajouter, soit supprimer des cases
     * @param xStart l'abcisse de la case de départ
     * @param yStart l'ordonnée de la case de départ
     * @param x l'abcisse de la case d'arrivée
     * @param y l'abcisse de la case d'arrivée
     */
    public void modifierChemins(int xStart, int yStart, int x, int y){
        // Verification de coordonnées compatibles
        if(verifierCoordonnees(xStart, yStart, x, y)){
            // Cas où la case de départ est déjà dans un chemin
            if(isChemin(xStart,yStart)){
                // Cas où la case d'arrivée est déjà dans un chemin ==> Fusion des chemins ou Suppression de la case d'arrivée du chemin
                if(isChemin(x,y)){
                    // Cas où les deux cases sont toutes deux dans un même chemin => Suppression
                    if(isDansMemeChemin(xStart, yStart, x, y)) {
                        supprimerCaseChemin(donneIndiceChemin(xStart, yStart));
                    }
                    else{
                        // Cas où les deux cases permettent la fusion ==> Fusion des deux cases
                        if(verifierFusion(xStart,yStart,x,y)){
                            fusionnerChemins(mLesChemins.get(donneIndiceChemin(xStart,yStart)), mLesChemins.get(donneIndiceChemin(x, y)));
                        }
                    }
                }
                // Cas où la case d'arrivée n'est pas déjà dans un chemin ==> Ajout de case
                else{
                    // Ajout de la case => si la case n'est pas une terminaison et qu'elle ne se trouve pas en plein milieu d'un chemin
                    if(!isTerminaison(mLesCases[xStart][yStart]))
                        ajouterCaseChemin(xStart, yStart, x, y);
                }
            }
            // Cas où la case de départ n'est pas déjà dans un chemin => Creation du chemin + ajout que si la case de départ est une terminaison
            else{
                if(isTerminaison(mLesCases[xStart][yStart]) && !isChemin(x,y)){
                    if(mLesCases[x][y].isTerminaison()){
                        if(mLesTerminaisons.get(donneIndiceTerminaison(x,y)).getTailleChemin() == 2){
                            creerChemin(xStart, yStart, x, y);
                        }
                    }
                    else {
                        creerChemin(xStart, yStart, x, y);
                    }
                }
            }
        }
    }



    /**
     * Permet de créer un nouveau chemin contenant deux cases
     * @param xStart l'abcisse de la case terminaison créant le chemin
     * @param yStart l'ordonnée de la case terminaison créant le chemin
     * @param x l'abcisse de la deuxième case
     * @param y l'ordonnée de la deuxième case
     */
    public void creerChemin(int xStart, int yStart, int x, int y){
        // Création du chemin
        Terminaison t = (Terminaison) mLesCases[xStart][yStart];
        Chemin c = new Chemin(t);
        // Ajout du chemin à la liste des chemins connus par la grille
        mLesChemins.add(c);
        // Ajout de la deuxième case contenue dans le chemin
        mLesChemins.get(donneIndiceChemin(xStart,yStart)).ajouterCase(mLesCases[x][y]);

    }

    /**
     * Permet d'ajouter une case à un chemin déjà existant
     * @param xStart abcisse de la case départ
     * @param yStart ordonnée de la case de départ
     * @param x abcisse de la case d'arrivée
     * @param y ordonnée de la case d'arrivée
     */
    public void ajouterCaseChemin(int xStart, int yStart, int x, int y){
        if(mLesChemins.get(donneIndiceChemin(xStart,yStart)).verifierDerniereCase(xStart,yStart)){
            // Dans le cas où l'on souhaite rallier une terminaison ==> verification de sa compatibilité avant ajout
            if(isTerminaison(mLesCases[x][y])){
                // on verifie que la terminaison soit compatible
                if(verifierTerminaison(donneIndiceChemin(xStart,yStart),x,y)){
                    mLesChemins.get(donneIndiceChemin(xStart,yStart)).ajouterCase(mLesCases[x][y]);
                }
            }
            else{
                mLesChemins.get(donneIndiceChemin(xStart,yStart)).ajouterCase(mLesCases[x][y]);
            }
        }

    }

    /**
     * Permet de supprimer une case d'un chemin
     * @param index l'indice du chemin où la case doit être supprimée
     */
    public void supprimerCaseChemin(int index){
        mLesChemins.get(index).supprimerCase();

        if(mLesChemins.get(index).getCasesChemin().size() < 2){
            mLesChemins.get(index).getCasesChemin().get(0).setDansChemin(false);
            mLesChemins.remove(index);
        }
    }

    /**
     * Permet de supprimer un chemin dans sa totalité
     * @param x abcisse de l'une des terminaison du chemin
     * @param y ordonnée de l'une des terminaison du chemin
     */
    public void supprimerChemin(int x, int y){
        // On vérifie que la case est dans un chemin
        if(isChemin(x,y)){
            int index = donneIndiceChemin(x,y);
            // Cas où le chemin est entièrement terminé : appuie sur n'importe case => suppression totale du chemin
            if(mLesChemins.get(index).isComplet()){
                mLesChemins.get(index).supprimerTout();
                mLesChemins.remove(index);
            }
            // Cas où le chemin n'est pas terminé :
            else{
                // nécéssité d'appuyer sur la terminaison initiale ==> suppression totale du chemin
                if(isTerminaison(mLesChemins.get(index).getCaseChemin(x,y))){
                    mLesChemins.get(index).supprimerTout();
                    mLesChemins.remove(index);
                }
            }
        }
    }

    /**
     * Permet de fusionner deux chemins
     * @param c1 le premier chemin
     * @param c2 le deuxième chemin
     */
    public void fusionnerChemins(Chemin c1, Chemin c2){
        // Ajout de toutes les cases du deuxième chemin dans le premier dans l'ordre inverse
        for(int i = c2.getCasesChemin().size()-1; i >= 0; i--){
            c1.ajouterCase(c2.getCasesChemin().get(i));
        }
//        for(Case c : c2.getCasesChemin()){
//            c1.ajouterCase(c);
//        }
        // Suppression du deuxième chemin
        c2.supprimerTout();
        mLesChemins.remove(c2);



    }

    //---------------------------------------- Ajouts / Suppressions ---------------------------------

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
        return mLesCases[x][y].isDansChemin();
    }

    /**
     * Permet de savoir si deux cases se trouvent dans le même chemin
     * @param x1 l'abcisse de la case 1
     * @param y1 l'ordonnée de la case 1
     * @param x2 l'abcisse de la case 2
     * @param y2 l'ordonnée de la case 2
     * @return true s'ils se trouvent dans le même chemin, false sinon
     */
    public boolean isDansMemeChemin(int x1, int y1, int x2, int y2){
        return(donneIndiceChemin(x1,y1) == donneIndiceChemin(x2,y2));
    }

    /**
     * Permet de savoir si deux terminaisons ont le même type
     * @param c1 chemin contenant la première terminaison
     * @param c2 chemin contenant la deuxième terminaison
     * @return true si les deux terminaisons sont de même type
     */
    public boolean isMemeTypeTerminaison(Chemin c1, Chemin c2){
        return(c1.getPremiereTerminaison().getTailleChemin() == c2.getPremiereTerminaison().getTailleChemin() && verifierCouleur(c1.getCouleurChemin(),c2.getCouleurChemin()));

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
        if(x == xStart+1 && y == yStart+1){
            return false;
        }
        else if (x == xStart+1 && y == yStart-1){
            return false;
        }
        else if(x == xStart-1 && y == yStart-1){
            return false;
        }
        else if(x == xStart-1 && y == yStart+1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Permet de vérifier si la case de départ et la terminaison d'arrivée sont compatibles pour un ajout de case dans un chemin
     * ie : même couleur, même taille de chemin initiale et qu'il ne reste plus qu'une place pour la terminaison dans le chemin
     * @param index l'indice du chemin concerné
     * @param x l'abcisse de la terminaison d'arrivée
     * @param y l'ordonnée de la terminaison d'arrivée
     * @return
     */
    public boolean verifierTerminaison(int index, int x, int y){
        Terminaison t = (Terminaison) mLesCases[x][y];
        Chemin chemin = mLesChemins.get(index);
        // Verification de la taille adéquate && Vérification de la couleur adéquate
        return (verifierTailleChemin(chemin.getTailleMax(), t.getTailleChemin()) && verifierCouleur(chemin.getCouleurChemin(),t.getR(),t.getG(),t.getB()) && chemin.getCasesChemin().size() == chemin.getTailleMax()-1);
    }

    /**
     * Permet de vérifier la couleur de deux cases
     * @param couleurChemin la couleur du chemin
     * @param r le code rouge de la terminaison
     * @param g le code vert de la terminaison
     * @param b le code bleu de la terminaison
     * @return true si la couleur est la meme, false sinon
     */
    public boolean verifierCouleur(int[] couleurChemin, int r, int g, int b){
        return(couleurChemin[0] == r && couleurChemin[1] == g && couleurChemin[2] == b);
    }

    /**
     * Permet de vérifier la couleur de deux chemins;
     * @param couleurChemin1 tableau des codes r,g,b du chemin 1
     * @param couleurChemin2 tableau des codes r,g,b du chemin 2
     * @return true si ce sont les mêmes couleurs, false sinon
     */
    public boolean verifierCouleur(int[] couleurChemin1, int[] couleurChemin2){
        int cpt = 0;
        for(int i = 0; i < couleurChemin1.length; i++){
            if(couleurChemin1[i] == couleurChemin2[i]){
                cpt++;
            }
        }
        return cpt > 2;
    }

    /**
     * Permet de verifier que la taille maximale d'un chemin et la taille chemin indiqué par la terminaison soit la meme
     * @param tailleChemin la taille maximale supportée par le chemin
     * @param tailleTerminaison la taille du chemin portée par la terminaison
     * @return true si les tailles sont identiques, false sinon
     */
    public boolean verifierTailleChemin(int tailleChemin, int tailleTerminaison){
        return tailleChemin == tailleTerminaison;
    }

    /**
     * Permet de verifier que la taille des chemins permet une fusion de deux chemins de même type
     * @param c1 le premier chemin
     * @param c2 le deuxième chemin
     * @return true si la fusion est possible, false sinon
     */
    public boolean verifierTailleFusion(Chemin c1, Chemin c2){
        return (c1.getCasesChemin().size()+c2.getCasesChemin().size() == c1.getTailleMax());
    }



    /**
     * Permet de vérifier si la fusion est possible
     * @param xStart l'abcisse de la case de départ
     * @param yStart l'ordonnée de la case de départ
     * @param x l'abcisse de la case d'arrivée
     * @param y l'ordonnée de la case d'arrivée
     * @return true si la fusion est possible, false sinon
     */
    public boolean verifierFusion(int xStart, int yStart, int x, int y) {
        Chemin c1 = mLesChemins.get(donneIndiceChemin(xStart,yStart));
        Chemin c2 = mLesChemins.get(donneIndiceChemin(x,y));
        return isMemeTypeTerminaison(c1,c2) && verifierTailleFusion(c1,c2) ;
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
