package brainitall.pixelly.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Level;

import brainitall.pixelly.R;

public class LevelActivity extends AppCompatActivity {

    /**
     * bouton niveau test
     */
    private Button mNiveauTest;

    /**
     * bouton niveau 1 qui sera bloqué au démarage
     */
    private Button mNiveau1;

    /**
     * boutou niveau 2 qui sera bloqué au démarrage
     */
    private Button mNiveau2;

    /**
     * bouton aide
     */
    private Button mHelp;

    /**
     * texte affiché sur la page de niveau
     */
    private TextView mListeNiveaux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // Branchements Vue / Controller
        mNiveauTest = (Button) findViewById(R.id.activity_level_btn_niveauTest); //on réference l'element graphique mNiveauTest
        mNiveau1 = (Button) findViewById(R.id.activity_level_btn_niveau1);
        mNiveau2 = (Button) findViewById(R.id.activity_level_btn_niveau2);
        mHelp = (Button) findViewById(R.id.activity_main_btn_aide);

        mNiveauTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playActivity = new Intent(LevelActivity.this, PlayActivity.class);
                mNiveau1.setEnabled(false); // on désactive le bouton niveau 1
                mNiveau2.setEnabled(false); // on désactive le bouton niveau 2 qui sera debloqué lors d'un succés au niveau 1
                startActivity(playActivity);

                // ----------------------------il faudra activer les boutons lorsqu'un niveau sera finit -------------------------


            /*ouverture de l'activitée aide lorsque l'utilisateur cliquera sur le bouton d'aide */
                mHelp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent helpActivity = new Intent(LevelActivity.this, LevelActivity.class);
                        startActivity(helpActivity);
                    }
                });
            }
        });
    }
}
