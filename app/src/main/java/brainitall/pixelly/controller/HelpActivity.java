package brainitall.pixelly.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import brainitall.pixelly.R;

public class HelpActivity extends AppCompatActivity {

    /**
     * textView de l'activitée d'aide
     */
    private TextView  mTexteAide;

    /**
     * première image présentée dans l'activitée aide
     */
    private ImageView mImage1;

    /**
     * seconde image présentée dans l'activitée aide
     */

    private ImageView mImage2;

    private TextView mTexteAide2;

    private Button mBouton;

    private RelativeLayout mRelativeLayout;

    private TextView mTexteAide3;

    private Button mBouton2;


    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // branchement de chaque widget de cette activitée
        mTexteAide = (TextView) findViewById(R.id.activity_help_textview1); // on réference l'élement graphique mTexteAide(TextView)
        mImage1 = (ImageView) findViewById(R.id.activity_help_image1); // on réference l'élément graphique mImage1 (ImageView)
        mImage2= (ImageView) findViewById(R.id.activity_help_image2);
        mTexteAide2 = ( TextView) findViewById(R.id.activity_help_textview2);
        mBouton = (Button) findViewById(R.id.activity_help_Button1);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_help_detailLayout);
        mTexteAide3 = (TextView) findViewById(R.id.activity_help_textview3);
        mBouton2 = (Button) findViewById(R.id.activity_help_Button2);

        mBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelativeLayout.setVisibility(View.VISIBLE); // le clic sur le bouton, rend le relativeLayout visible
                mBouton.setVisibility(View.GONE); // le bouton " plus de détail" est masqué
            }
        });
    }
}
