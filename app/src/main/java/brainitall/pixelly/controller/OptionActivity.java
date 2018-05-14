package brainitall.pixelly.controller;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.content.Intent;
import android.view.View;

import brainitall.pixelly.R;

public class OptionActivity extends AppCompatActivity {

    // Référencement des différents éléments de la vue
    private ToggleButton mSon;
    private Button mReset;
    private Button mAide;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        // Branchements entre Vue et Controlleur
        mSon = (ToggleButton) findViewById(R.id.activity_option_btn_son);
        mReset = (Button) findViewById(R.id.activity_option_btn_reset);
        mAide = (Button) findViewById(R.id.activity_main_btn_aide);

        mp=MediaPlayer.create(this, R.raw.musique_test);
        mp.start();
        
        /*
            Action lorsque le joueur clique sur le bouton 'on/off' du son
         */
        mSon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton son, boolean isChecked) {
                if (isChecked) {
                    System.out.println("- - - - - - - - - - - - -");
                    System.out.println("Play musique");
                    mp.start();
                } else {
                    System.out.println("- - - - - - - - - - - - -");
                    System.out.println("Stop musique");
                    mp.stop();
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
