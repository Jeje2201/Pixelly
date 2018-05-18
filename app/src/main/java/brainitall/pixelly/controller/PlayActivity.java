package brainitall.pixelly.controller;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import brainitall.pixelly.R;
import brainitall.pixelly.view.PlayView;

/**
 * Classe représentant l'activité permettant de gérer le jeu
 * @author Mélodie Meissel
 */
public class PlayActivity extends AppCompatActivity {

    /**
     * La vue personnalisée
     */
    private PlayView mVue;

    @Override
    /**
     * Permet d'intialiser l'activité
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Création du widget de la vue de la grille
        mVue = new PlayView(this);
        mVue.setZOrderOnTop(true);
        mVue.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        // Définition de la grille comme contentView
        setContentView(mVue);

    }

    @Override
    /**
     * Permet d'effectuer des actions spécifiques lorsque l'on retourne en arrière
     */
    public void onBackPressed() {
        mVue.interruptedThread();
        finish();
        super.onBackPressed();
    }
}
