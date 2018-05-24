package brainitall.pixelly.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import brainitall.pixelly.R;

/**
 * Classe représentant l'activité correspondant à l'Aide du jeu
 * @author Loïc Ezrati
 */
public class HelpActivity extends AppCompatActivity {

    /**
     * élement graphique qui va permettre de faire défiler l'activitée aide
     */
    private ScrollView mDefilement;

    /**
     * mise en page de l'activitée d'aide
     */
    private LinearLayout mMiseEnPage;


    /**
     * textView de l'activitée d'aide
     */
    private TextView  mTexteAide;

    /**
     * première image présentée dans l'activitée aide
     */
    private ImageView mImage1;

    /**
     *  texte de description de la première image présentant le fonctionnement du jeu
     */
    private TextView mTexteAide2;

    /**
     * seconde image présentée dans l'activitée aide
     */
    private ImageView mImage2;

    /**
     * texte decrivant la seconde image présentant le fonctionnement du jeu
     */
    private TextView mTexteAide3;

    @Override
    /**
     * Permet d'intialiser l'activité
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // branchement de chaque widget de cette activitée
        mDefilement = (ScrollView) findViewById(R.id.activity_help_defilement); // on réference l'élement graphique qui va servir à faire défiler l'activitée(ScrollView)
        mMiseEnPage = (LinearLayout) findViewById(R.id.activity_help_mise_en_page);
        mTexteAide = (TextView) findViewById(R.id.activity_help_textview1); // on réference l'élement graphique mTexteAide(TextView)
        mImage1 = (ImageView) findViewById(R.id.activity_help_image1); // on réference l'élément graphique mImage1 (ImageView)
        mImage2= (ImageView) findViewById(R.id.activity_help_image2);
        mTexteAide2 = ( TextView) findViewById(R.id.activity_help_textview2);
        mTexteAide3 = (TextView) findViewById(R.id.activity_help_textview3);

    }
}
