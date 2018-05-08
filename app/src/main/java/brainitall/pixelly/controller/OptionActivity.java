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
    private ToggleButton son;
    private Button reset;
    private Button aide;

    private MediaPlayer mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.id.activity_option_btn_son);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        // Branchements entre Vue et Controlleur
        son = (ToggleButton) findViewById(R.id.activity_option_btn_son);
        reset = (Button) findViewById(R.id.activity_option_btn_reset);
        aide = (Button) findViewById(R.id.activity_main_btn_aide);



        /*
            Action lorsque le joueur clique sur le bouton 'on/off' du son
         */
        son.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton son, boolean isChecked) {
                if (isChecked) {
                    mMediaPlayer.start();
                } else {
                    mMediaPlayer.stop();
                }
            }
        });

        /*
            Action lorsque le joueur clique sur le bouton 'Reset'
         */

        

        /*
            Action lorsque le joueur clique sur le bouton 'Aide'
         */
        aide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent(OptionActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });
    }
}
