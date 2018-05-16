package brainitall.pixelly.model.technique;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import brainitall.pixelly.controller.Manager;

public class Fichier {

    private String mNomFichier;

    public Fichier(String nomFichier){
        mNomFichier = nomFichier;

    }

    // methode a revoir !
    /**
     * Permet de lire un fichier Json
     */
    public void lireFichier(Context context) {

        String json = null;
        try {
            InputStream is = context.getAssets().open(mNomFichier);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        try {
            JSONObject obj = new JSONObject(json);
            JSONObject tailleGrille = obj.getJSONObject("infoGrille");

            for (int i = 0; i < tailleGrille.length(); i++) {
                //JSONObject jo_inside = tailleGrille.getJSONObject(i);
                int numGrille = tailleGrille.getInt("numGrille");
                int hauteur = tailleGrille.getInt("hauteur");
                int largeur = tailleGrille.getInt("largeur");
                // terminaison.setHauteur(jo_inside.getInt("hauteur"));
                //terminaison.setLargeur(jo_inside.getInt("largeur"));

                //Ajouter les valeurs dans la ArrayList
                //locList.add(tailleGrille);
                Manager.getInstance().ajouterGrille(numGrille, largeur, hauteur);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject  terminaison = obj.getJSONObject("terminaison");

            for (int i = 0; i < terminaison.length(); i++) {
                int tailleChemin = terminaison.getInt("tailleChemin");
                int x = terminaison.getInt("x");
                int y = terminaison.getInt("y");
                int r = terminaison.getInt("r");
                int g = terminaison.getInt("g");
                int b = terminaison.getInt("b");

                //Ajouter les valeurs dans la ArrayList
                //locList.add(terminaison);
                Manager.getInstance().ajouterTerminaison(tailleChemin,x,y,r,g,b);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }

    }
}
