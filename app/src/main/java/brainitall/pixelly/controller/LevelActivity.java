package brainitall.pixelly.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

import brainitall.pixelly.R;
import brainitall.pixelly.model.Utilitaire;
import brainitall.pixelly.model.technique.Fichier;
import brainitall.pixelly.view.LevelListener;

/**
 * Classe représentant l'activité correspondant à la liste des niveaux
 * @author Loïc Ezrati
 */
public class LevelActivity extends AppCompatActivity {

    // -------------------- MODELE -----------------------------


    /**
     * Nombre total de Niveau
     */
    public static int NBLEVEL = 3;

    /**
     * Le fichier permetttant de charger et sauvegarder le jeu
     */
    private Fichier mLeFichier;

    /**
     * Numéro du dernier niveau débloqué
     */
    private int mNumDernierNiveau;

    /**
     * Numéro du niveau sur lequel le joueur est en train de jouer
     */
    private int mNumNiveauCourant;

    // ------------------------ VUE -----------------------------

    /**
     * Liste des boutons relatifs aux différents niveaux
     */
    private List<Button> mLesBoutonsNiveau;

    /**
     * bouton aide
     */
    private Button mHelp;

    /**
     * texte affiché sur la page de niveau
     */
    private TextView mListeNiveaux;


    /**
     * barre de défilement de l'activitée
     */
    private ScrollView mDefilement;

    /**
     * mise en page de l'activitée
     */
    private LinearLayout mMiseEnPage;






    @Override
    /**
     * Permet d'initialiser l'activité
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // Initialisation des boutons
        System.out.println("ON RENTRE DANS LE LEVEL ACTIVITY");
        mNumDernierNiveau = Utilitaire.lireEtatJeu(getApplicationContext());
        mNumNiveauCourant = 0;
        mLesBoutonsNiveau = new Vector<>();
        initBoutonsNiveau();
        mHelp = (Button) findViewById(R.id.activity_main_btn_aide);
        mDefilement = (ScrollView) findViewById(R.id.activity_level_defilement); // réferencement de la barre de défilement
        mMiseEnPage = (LinearLayout) findViewById(R.id.activity_level_MiseEnPage);
        initListenerLevel();
        /*ouverture de l'activitée aide lorsque l'utilisateur cliquera sur le bouton d'aide */
        mHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent(LevelActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });
    }

    /**
     * Permet d'initialiser les boutons Niveau
     */
    private void initBoutonsNiveau() {
        // Branchements Vue / Controller
        Button nTest = (Button) findViewById(R.id.activity_level_btn_niveauTest); //on réference l'element graphique mNiveauTest
        Button n1 = (Button) findViewById(R.id.activity_level_btn_niveau1);
        Button n2 = (Button) findViewById(R.id.activity_level_btn_niveau2);
        Button n3 =(Button) findViewById(R.id.activity_level_btn_niveau3);
        Button n4 =(Button) findViewById(R.id.activity_level_btn_niveau4);
        Button n5 =(Button) findViewById(R.id.activity_level_btn_niveau5);

        // Ajout à la liste de boutons
        mLesBoutonsNiveau.add(nTest);
        mLesBoutonsNiveau.add(n1);
        mLesBoutonsNiveau.add(n2);
        mLesBoutonsNiveau.add(n3);
        mLesBoutonsNiveau.add(n4);
        mLesBoutonsNiveau.add(n5);
        // On bloque les niveaux non débloqué encore par l'utilisateur
        if(mNumDernierNiveau < NBLEVEL){
            for(int i = mNumDernierNiveau+1; i < mLesBoutonsNiveau.size(); i++){
                mLesBoutonsNiveau.get(i).setEnabled(false);
            }
        }
    }

    /**
     * Permet d'initialiser les Listeners sur les boutons Niveau
     */
    private void initListenerLevel() {
        String nomFichier = "";
        for(int i = 0; i < mLesBoutonsNiveau.size(); i++){
            nomFichier = "n"+(i+1)+".json";
            mLesBoutonsNiveau.get(i).setOnClickListener(new LevelListener(i, nomFichier,this));
        }
    }

    public void debloquerNiveau(){
        System.out.println(mNumDernierNiveau);
        for(int i = 0 ; i < mNumDernierNiveau+1; i++){
            mLesBoutonsNiveau.get(i).setEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNumDernierNiveau = Utilitaire.lireEtatJeu(getApplicationContext());
        System.out.println(mNumDernierNiveau);
        debloquerNiveau();
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilitaire.ecrireEtatJeu(getApplicationContext(), mNumDernierNiveau);
        System.out.println("On quitte l'activité et on écrit l'état du jeu");
        finish();
    }

    // ----------------------- GETTER & SETTER ---------------------------------


    public int getNumNiveauCourant() {
        return mNumNiveauCourant;
    }

    public void setNumNiveauCourant(int numNiveauCourant) {
        mNumNiveauCourant = numNiveauCourant;
    }

    public int getNumDernierNiveau() {
        return mNumDernierNiveau;
    }


}
