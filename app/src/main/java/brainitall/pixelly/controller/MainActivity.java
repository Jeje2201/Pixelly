package brainitall.pixelly.controller;


import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import brainitall.pixelly.R;

/**
 * Classe représentant l'activité principale, lancée au début de l'application
 * @author Jérémy Leriche
 */
public class MainActivity extends AppCompatActivity {

    //---------------------------REFERENCEMENT DES DIFFERENTS OBJETS DE LA VUE ------------------------------
    /**
     * Bouton permettant de jouer
     */
    private Button mJouer;
    /**
     * Bouton permettant d'avoir accès aux différentes options du jeu
     */
    private Button mOption;
    /**
     * Bouton permettant de quitter l'application
     */
    private Button mQuitter;
    /**
     * Bouton permettant d'avoir accès à l'aide du jeu
     */
    private Button mAide;

    /**
     * Lecteur audio
     */
    private static MediaPlayer mp;



    @Override
    /**
     * Permet d'initialiser l'activité
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Branchements entre Vue et Controlleur
        mJouer = (Button) findViewById(R.id.activity_main_btn_jouer);
        mOption = (Button) findViewById(R.id.activity_main_btn_option);
        mQuitter = (Button) findViewById(R.id.activity_main_btn_quitter);
        mAide = (Button) findViewById(R.id.activity_main_btn_aide);

        //Si le média pour la musique n'existe pas (donc au premier lancement), le créer
        if( mp == null) {
            mp = MediaPlayer.create(this, R.raw.background_music);
        }
        //Si la musique n'est pas lancer, alors la lancer
        if(!mp.isPlaying()) {
            mp.setLooping(true);
            mp.start();
        }

        /*
            Action lorsque le joueur clique sur le bouton 'Jouer'
         */

        mJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent levelActivity = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(levelActivity);
            }
        });
        /*
            Action lorsque le joueur clique sur le bouton 'Option'
         */
        mOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionActivity = new Intent(MainActivity.this, OptionActivity.class);
                startActivity(optionActivity);
            }
        });

        /*
            Action lorsque le joueur clique sur le bouton 'Quitter'
         */

         mQuitter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
                 System.exit(0);
             }
         });
        /*
            Action lorsque le joueur clique sur le bouton 'Aide'
         */
        mAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });
    }

    @Override
    /**
     * Permet de gérer les activités lorsque l'on va en arrière
     */
    public void onBackPressed() {
        finishAffinity();
    }

    /**
     * Permet d'obtenir le lecteur audio
     * @return le lecteur audio
     */
    public static MediaPlayer getMp() {
        return mp;
    }


}
