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
        // Chargement du fichier et donc création de la grille
        mLeFichier = new Fichier(str);
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

                JSONObject jsonFinal = new JSONObject();

                try {

                    jsonFinal.put("NomNiveau", Manager.getInstance().getLaGrille().getNumGrille());
                    jsonFinal.put("NombreChemins", Manager.getInstance().getLaGrille().getLesChemins().size());

                    //Je créé un objet dans lequel j'insererais toutes les infos
                    JSONArray listeChemins = new JSONArray(); //Je créé une liste dans laquel je rentre tous mes objets a chaque fois

                    List<Chemin> lesChemins = Manager.getInstance().getLaGrille().getLesChemins();//pour chaque chemins

                    for(int compteurChemin=0;compteurChemin<lesChemins.size();compteurChemin++) {
                        JSONObject infosChemins=new JSONObject();
                        //J'insere toutes les infos
                        infosChemins.put("CheminNumero",compteurChemin);
                        infosChemins.put("TailleMax", lesChemins.get(compteurChemin).getTailleMax());

                        int [] couleurs = lesChemins.get(compteurChemin).getCouleurChemin();

                        infosChemins.put("r", couleurs[0]);
                        infosChemins.put("g", couleurs[1]);
                        infosChemins.put("b", couleurs[2]);

                        JSONArray listeCases = new JSONArray();
                        List<Case> lesCases = lesChemins.get(compteurChemin).getCasesChemin();//pour chaques cases

                        for (int compteurCase = 0; compteurCase < lesChemins.get(compteurChemin).getTailleMax(); compteurCase++) {

                            JSONObject infoCase=new JSONObject();

                            infoCase.put("x", Manager.getInstance().getLaGrille().getLesChemins().get(compteurChemin).getCasesChemin().get(compteurCase).getY());
                            infoCase.put("y", Manager.getInstance().getLaGrille().getLesChemins().get(compteurChemin).getCasesChemin().get(compteurCase).getX());

                            listeCases.put(infoCase);

                        } //fin chaques cases

                        infosChemins.put("Cases", listeCases);

                        listeChemins.put(infosChemins); //j'ajoute dans ma liste de chemin objet chemin avec toutes ses infos
                    }

                    jsonFinal.put("chemins",listeChemins); //J'ajoute a la fin l'objet "chemin" avec sa liste de chemins

                    System.out.println(jsonFinal);
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
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

    // --------------------------- GESTION DE L'ACTIVITE --------------------------

    @Override
    /**
     * Action lorsque l'on retourne sur l'activité précédente (LevelActivity)
     */
    public void onBackPressed() {
        mVue.interruptedThread();
        super.onBackPressed();
        boolean fini = false;
        if(mLaGrille.niveauFini()){
            fini = true;
        }
        Intent data =getIntent();
        data.putExtra("fini",fini);
        setResult(RESULT_OK,data);

        finish();
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
