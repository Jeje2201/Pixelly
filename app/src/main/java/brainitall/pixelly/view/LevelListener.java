package brainitall.pixelly.view;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import brainitall.pixelly.controller.LevelActivity;
import brainitall.pixelly.controller.PlayActivity;

public class LevelListener implements OnClickListener {
    /**
     * Nom du fichier json a charger
     */
    private String mNomFichier;

    /**
     * Activité de départ
     */
    private LevelActivity mLevelActivity;

    /**
     * Numéro du niveau lancé
     */
    private int mNumLance;

    /**
     * Constructeur
     * @param numLance numéro du niveau lancé
     * @param nomFichier nom du fichier à charger
     * @param levelActivity Activité de départ
     */
    public LevelListener(int numLance, String nomFichier, LevelActivity levelActivity) {
        mNumLance = numLance;
        mNomFichier = nomFichier;
        mLevelActivity = levelActivity;
    }

    @Override
    /**
     * Action quand on clique sur le bouton niveau
     */
    public void onClick(View v) {
        Intent playActivity = new Intent(mLevelActivity, PlayActivity.class);
        playActivity.putExtra("nomFichier",mNomFichier);
        playActivity.putExtra("numDernierNiveau",mLevelActivity.getNumDernierNiveau());
        mLevelActivity.setNumNiveauCourant(mNumLance);
        mLevelActivity.startActivity(playActivity);
    }
}
