package brainitall.pixelly.controller;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import brainitall.pixelly.R;
import brainitall.pixelly.model.metier.Case;
import brainitall.pixelly.model.metier.Chemin;
import brainitall.pixelly.view.PlayView;

public class PlayActivity extends AppCompatActivity {

    private PlayView mVue;

    //Options
    private TextView mNomNiv;
    private Button mAide;
    private Button mReinit;
    private Button mSave;
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
        mSave = (Button) findViewById(R.id.activity_play_save);
        mGrilleNiv = (SurfaceView) findViewById(R.id.activity_play_grille);


        mAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpActivity = new Intent(PlayActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonFinal = new JSONObject();

                try {

                    jsonFinal.put("NomNiveau", Manager.getInstance().getLaGrille().getNumGrille());
                    jsonFinal.put("NombreChemins", Manager.getInstance().getLaGrille().getLesChemins().size());

                    //Je créé un objet dans lequel j'insererais toutes les infos
                    JSONArray listeChemins = new JSONArray(); //Je créé une liste dans laquel je rentre tous mes objets a chaque fois

                    List<Chemin> lesChemins = Manager.getInstance().getLaGrille().getLesChemins();//pour chaque chemins

                    for(int compteurChemin=0;compteurChemin<lesChemins.size();compteurChemin++) {
                        JSONObject infosChemins=new JSONObject();
                        //J'insere toutes les infos
                        infosChemins.put("CheminNumero",compteurChemin);
                        infosChemins.put("TailleMax", lesChemins.get(compteurChemin).getTailleMax());

                        int [] couleurs = lesChemins.get(compteurChemin).getCouleurChemin();

                        infosChemins.put("r", couleurs[0]);
                        infosChemins.put("g", couleurs[1]);
                        infosChemins.put("b", couleurs[2]);

                        JSONArray listeCases = new JSONArray();
                        List<Case> lesCases = lesChemins.get(compteurChemin).getCasesChemin();//pour chaques cases

                        for (int compteurCase = 0; compteurCase < lesChemins.get(compteurChemin).getTailleMax(); compteurCase++) {

                            JSONObject infoCase=new JSONObject();

                            infoCase.put("x", Manager.getInstance().getLaGrille().getLesChemins().get(compteurChemin).getCasesChemin().get(compteurCase).getY());
                            infoCase.put("y", Manager.getInstance().getLaGrille().getLesChemins().get(compteurChemin).getCasesChemin().get(compteurCase).getX());

                            listeCases.put(infoCase);

                        } //fin chaques cases

                        infosChemins.put("Cases", listeCases);

                        listeChemins.put(infosChemins); //j'ajoute dans ma liste de chemin objet chemin avec toutes ses infos
                    }

                    jsonFinal.put("chemins",listeChemins); //J'ajoute a la fin l'objet "chemin" avec sa liste de chemins

                    System.out.println(jsonFinal);
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
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
