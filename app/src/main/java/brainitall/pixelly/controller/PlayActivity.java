package brainitall.pixelly.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import brainitall.pixelly.R;
import brainitall.pixelly.model.Utilitaire;
import brainitall.pixelly.model.metier.Case;
import brainitall.pixelly.model.metier.Chemin;
import brainitall.pixelly.model.metier.Grille;
import brainitall.pixelly.model.technique.Fichier;
import brainitall.pixelly.view.PlayView;

public class PlayActivity extends AppCompatActivity {

    // --------------------- MODELE ----------------------------

    /**
     * Grille du jeu
     */
    private Grille mLaGrille;
    /**
     * Fichier du jeu
     */
    private Fichier mLeFichier;

    private int mNumDernierNiveau;



    // -------------------- VUE ---------------------------------

    private PlayView mVue;

    //Options
    private TextView mNomNiv;
    private Button mAide;
    private Button mReinit;
    private Button mSave;
    //Grille
    private SurfaceView mGrilleNiv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialisation de la grille
        mLaGrille = null;
        // On récupère le nom du fichier à charger
        Intent intent = getIntent();
        String str = intent.getStringExtra("nomFichier");
        mNumDernierNiveau = intent.getIntExtra("numDernierNiveau",0);
        // Chargement du fichier et donc création de la grille
        mLeFichier = new Fichier(str,this);
        mLeFichier.lireFichier(getApplicationContext(),this);
        // Création du widget de la vue de la grille
        mVue = new PlayView(this);
//        mVue.setZOrderOnTop(true);
//        mVue.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        // Définition de la grille comme contentView
        //setContentView(mVue);
        setContentView(R.layout.activity_play);


        //Rattachement au layout
        mNomNiv = (TextView) findViewById(R.id.activity_play_nom_niveau);
        mNomNiv.setText("Niveau "+mLaGrille.getNumGrille());
        mAide = (Button) findViewById(R.id.activity_play_aide);
        mReinit = (Button) findViewById(R.id.activity_play_rez);
        mSave = (Button) findViewById(R.id.activity_play_save);
        mGrilleNiv = (SurfaceView) findViewById(R.id.activity_play_grille);


        mAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent(PlayActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLeFichier.ecrireSave(getApplicationContext(),"save"+mLaGrille.getNumGrille()+".json");
            }
        });

        mReinit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reinitialiserGrille();
            }
        });
    }

    // ---------------------------------- GESTION DU JEU ----------------------

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
     * Permet d'associer une grille
     * @param numGrille numéro de la grille
     * @param hauteur hauteur de la grille
     * @param largeur largeur de la grille
     * @param nomGrille nom de la grille
     */
    public void ajouterGrille(int numGrille, int hauteur, int largeur, String nomGrille){
        mLaGrille = new Grille(numGrille,hauteur,largeur,nomGrille);
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

    /**
     * Permet de modifier les chemins de la grille (ajout et suppression de case, fusion de chemin)
     * @param xStart l'abcisse de la case de départ
     * @param yStart l'ordonnée de la case de départ
     * @param x l'abcisse de la case d'arrivée
     * @param y l'ordonnée de la case d'arrivée
     */
    public void modifierChemins(int xStart, int yStart, int x, int y){
        if(mLaGrille != null){
            mLaGrille.modifierChemins(xStart, yStart, x, y);
        }
    }

    /**
     * Permet de supprimer un chemin à partir d'une case
     * @param x l'abcisse de la case
     * @param y l'ordonnée de la case
     */
    public void supprimerChemin(int x, int y){
        if(mLaGrille != null){
            mLaGrille.supprimerChemin(x, y);
        }
    }

    public void reinitialiserGrille(){
        if(mLaGrille != null){
            for(Chemin c : mLaGrille.getLesChemins()){
                c.supprimerTout();
            }
            mLaGrille.getLesChemins().removeAll(mLaGrille.getLesChemins());
        }
    }

    public boolean isTerminaison(int x, int y){
        if(mLaGrille != null){
            Case c = mLaGrille.getCase(x,y);
            if(c.isTerminaison()){
                return true;
            }
        }
        return false;
    }

    // --------------------------- GESTION DE L'ACTIVITE --------------------------

    @Override
    /**
     * Action lorsque l'on retourne sur l'activité précédente (LevelActivity)
     */
    public void onBackPressed() {
        mVue.interruptedThread();
        super.onBackPressed();
        if(mLaGrille.niveauFini()){
            if(mNumDernierNiveau < LevelActivity.NBLEVEL-1 && mLaGrille.getNumGrille()-1 >= mNumDernierNiveau){
                mNumDernierNiveau += 1;
                Utilitaire.ecrireEtatJeu(getApplicationContext(),mNumDernierNiveau);
                System.out.println(Utilitaire.lireEtatJeu(getApplicationContext()));
            }
        }
        mLeFichier.ecrireSave(getApplicationContext(),"save"+mLaGrille.getNumGrille()+".json");
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLeFichier.ecrireSave(getApplicationContext(),"save"+mLaGrille.getNumGrille()+".json");
    }

    // ----------------------------- GETTER & SETTER --------------------------------------


    /**
     * Permet d'obtenir la grille
     * @return la grille
     */
    public Grille getLaGrille() {
        return mLaGrille;
    }
}
