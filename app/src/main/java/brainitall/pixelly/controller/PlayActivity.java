package brainitall.pixelly.controller;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import brainitall.pixelly.R;
import brainitall.pixelly.view.PlayView;

public class PlayActivity extends AppCompatActivity {

    private PlayView mVue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Création du widget de la vue de la grille
        mVue = new PlayView(this);
        mVue.setZOrderOnTop(true);
        mVue.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        // Définition de la grille comme contentView
        setContentView(mVue);

    }
}
