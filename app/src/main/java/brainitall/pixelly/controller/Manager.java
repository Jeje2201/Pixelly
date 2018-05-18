package brainitall.pixelly.controller;

import brainitall.pixelly.model.metier.Chemin;
import brainitall.pixelly.model.metier.Grille;
import brainitall.pixelly.view.PlayView;

/**
 * Classe représentant le Manager du jeu
 * @author Mélodie Meissel
 */
public class Manager {
    /**
     * Singleton
     */
    private static Manager instance = new Manager();

    /**
     * La grille de jeu
     */
    private Grille mLaGrille;

    /**
     * Constructeur
     */
    private Manager(){
        mLaGrille = null;
    }

    /**
     * Permet d'associer une grille
     * @param numGrille numéro de la grille
     * @param hauteur hauteur de la grille
     * @param largeur largeur de la grille
     */
    public void ajouterGrille(int numGrille, int hauteur, int largeur ){
        mLaGrille = new Grille(numGrille,hauteur,largeur);
    }

    /**
     * Permet de dissocier la grille
     */
    public void dissocierGrille(){
        mLaGrille = null;
    }

    /**
     * Permet d'ajouter une terminaison dans la grille
     * @param tailleChemin taille du chemin portée par la terminaison
     * @param x l'abcisse de la terminaison
     * @param y l'ordonnée de la terminaison
     * @param r le code du rouge
     * @param g le code du vert
     * @param b le code du bleu
     */
    public void ajouterTerminaison(int tailleChemin, int x, int y, int r, int g, int b){
        if(mLaGrille != null){
            mLaGrille.ajouterTerminaison(tailleChemin,x,y,r,g,b);
        }
    }

    /**
     * Permet d'ajouter un chemin dans la grille
     * @param c le chemin à ajouter
     */
    public void ajouterChemin(Chemin c){
        if(mLaGrille != null){
            mLaGrille.ajouterChemin(c);
        }
    }
    // ----------------------------- GETTER & SETTER --------------------------------------

    /**
     * Permet d'obtenir le Singleton
     * @return le singleton
     */
    public static Manager getInstance() {
        return instance;
    }

    /**
     * Permet d'obtenir la grille
     * @return la grille
     */
    public Grille getLaGrille() {
        return mLaGrille;
    }
}
