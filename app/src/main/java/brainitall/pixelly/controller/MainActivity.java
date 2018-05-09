package brainitall.pixelly.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import brainitall.pixelly.R;

public class MainActivity extends AppCompatActivity {

    // Référencement des différents éléments de la vue
    private Button mJouer;
    private Button mOption;
    private Button mQuitter;
    private Button mAide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Branchements entre Vue et Controlleur
        mJouer = (Button) findViewById(R.id.activity_main_btn_jouer);
        mOption = (Button) findViewById(R.id.activity_main_btn_option);
        mQuitter = (Button) findViewById(R.id.activity_main_btn_quitter);
        mAide = (Button) findViewById(R.id.activity_main_btn_aide);

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
}
