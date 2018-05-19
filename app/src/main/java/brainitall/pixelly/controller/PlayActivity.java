package brainitall.pixelly.controller;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import brainitall.pixelly.R;
import brainitall.pixelly.view.PlayView;

public class PlayActivity extends AppCompatActivity {

    private PlayView mVue;

    //Options
    private TextView mNomNiv;
    private Button mAide;
    private Button mReinit;
    //Grille
    private SurfaceView mGrilleNiv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Création du widget de la vue de la grille
        mVue = new PlayView(this);
        mVue.setZOrderOnTop(true);
        mVue.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        // Définition de la grille comme contentView
        //setContentView(mVue);
        setContentView(R.layout.activity_play);


        //Rattachement au layout
        mNomNiv = (TextView) findViewById(R.id.activity_play_nom_niveau);
        System.out.println("Niveau "+Manager.getInstance().getLaGrille().getNumGrille());
        mNomNiv.setText("Niveau "+Manager.getInstance().getLaGrille().getNumGrille());
        mAide = (Button) findViewById(R.id.activity_play_aide);
        mReinit = (Button) findViewById(R.id.activity_play_rez);
        mGrilleNiv = (SurfaceView) findViewById(R.id.activity_play_grille);


        mAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent(PlayActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });

        mReinit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Manager.getInstance().reinitialiserGrille();
            }
        });
    }

    @Override
    public void onBackPressed() {
        mVue.interruptedThread();
        finish();
        super.onBackPressed();
    }
}
