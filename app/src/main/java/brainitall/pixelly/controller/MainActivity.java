package brainitall.pixelly.controller;


import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import brainitall.pixelly.R;

/**
 * Classe représentant l'activité principale, lancée au début de l'application
 * @author Jérémy Leriche
 */
public class MainActivity extends AppCompatActivity {

    //---------------------------REFERENCEMENT DES DIFFERENTS OBJETS DE LA VUE ------------------------------
    /**
     * Image qui sert de bouton permettant de jouer
     */
    private ImageView mJouer;

    /**
     * Image qui sert de bouton permettant d'avoir accès aux différentes options du jeu
     */
    private ImageView mOption;
    /**
     * Image qui sert de bouton permettant de quitter l'application
     */
    private ImageView mQuitter;
    /**
     * Image qui sert de bouton permettant d'avoir accès à l'aide du jeu
     */
    private ImageView mAide;

    /**
     * Image qui sert de bouton permettant d'avoir accès au crédit du jeu
     */
    private ImageView mCredit;

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
        mJouer = (ImageView) findViewById(R.id.imageView_play);
        mOption = (ImageView) findViewById(R.id.imageView_option);
        mQuitter = (ImageView) findViewById(R.id.imageView_quitter);
        mCredit = (ImageView) findViewById(R.id.imageView_credit);
        mAide = (ImageView) findViewById(R.id.imageView_aide);

        //Si le média pour la musique n'existe pas (donc au premier lancement), le créer
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.background_music);
        }
        //Si la musique n'est pas lancer, alors la lancer
        if (!mp.isPlaying()) {
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

        mCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditActivity = new Intent(MainActivity.this, CreditActivity.class);
                startActivity(creditActivity);
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
