package brainitall.pixelly.controller;

import brainitall.pixelly.model.metier.Grille;

public class Manager {
    private static Manager instance = new Manager();

    private Grille mLaGrille;

    private Manager(){
        mLaGrille = null;
    }

    public void ajouterGrille(int numGrille, int hauteur, int largeur ){
        mLaGrille = new Grille(numGrille,hauteur,largeur);
    }

    public void dissocierGrille(){
        mLaGrille = null;
    }

    public void ajouterTerminaison(int tailleChemin, int x, int y, int r, int g, int b){
        if(mLaGrille != null){
            mLaGrille.ajouterTerminaison(tailleChemin,x,y,r,g,b);
        }
    }
    // ----------------------------- GETTER & SETTER --------------------------------------

    public static Manager getInstance() {
        return instance;
    }

    public Grille getLaGrille() {
        return mLaGrille;
    }
}
