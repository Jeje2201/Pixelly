package brainitall.pixelly.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.Intent;
import android.view.View;

import brainitall.pixelly.R;

/**
 * Classe représentant l'activité correspondant au menu des options
 * @author Manon Brun
 */
public class OptionActivity extends AppCompatActivity {

    //------------------------ REFERENCEMENT DES DIFFERENTS OBJETS DE LA VUE
    /**
     * Bouton permettant de gérer le son
     */
    private ToggleButton mSon;
    /**
     * Bouton permettant de RESET les paramètres du jeu
     */
    private Button mReset;
    /**
     * Bouton permettant d'avoir accès à l'aide du jeu
     */
    private Button mAide;

    @Override
    /**
     * Permet d'initialiser l'activité
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        // Branchements entre Vue et Controlleur
        mSon = (ToggleButton) findViewById(R.id.activity_option_btn_son);
        mReset = (Button) findViewById(R.id.activity_option_btn_reset);
        mAide = (Button) findViewById(R.id.activity_main_btn_aide);

        //Si la musique n'est pas jouée lorsque l'on arrive sur la view, alors toggle le bouton, sinon ne rien toggle
        //L'on obtiens un état de toggle toujours opposé à l'état de notre musique lorsque l'on arrive sur la view
        if(!MainActivity.getMp().isPlaying()){
            mSon.toggle();
        }

        /*
            Action lorsque le joueur clique sur le bouton 'on/off' du son
         */
        mSon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton son, boolean isChecked) {
                //Si le toggle est sur true et qu'aucun sons est en cours, alors lancer la musique
                if (isChecked && !MainActivity.getMp().isPlaying()) {
                    Toast.makeText(OptionActivity.this, "Musique play", Toast.LENGTH_SHORT).show();
                    MainActivity.getMp().start();
                }
                //Sinon si le toggle est sur false que la musique est en cours, alors la mettre sur pause
                else {
                    Toast.makeText(OptionActivity.this, "Musique Stop", Toast.LENGTH_SHORT).show();
                    if(MainActivity.getMp().isPlaying()) {
                        MainActivity.getMp().pause();
                    }
                }
            }
        });

        /*
            Action lorsque le joueur clique sur le bouton 'Reset'
         */



        /*
            Action lorsque le joueur clique sur le bouton 'Aide'
         */
        mAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent(OptionActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });
    }
}
