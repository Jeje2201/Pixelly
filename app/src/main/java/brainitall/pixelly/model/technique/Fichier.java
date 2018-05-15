package brainitall.pixelly.model.technique;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fichier {

    public Fichier(){

    }

    // methode a revoir !
    /**
     * Permet de lire un fichier Json
     *//*
    public void lireFichier(Context context) {
        ArrayList<MaTerminaison> locList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = context.getAssets().open("1.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject  tailleGrille = obj.getJSONObject("tailleGrille");

            for (int i = 0; i < tailleGrille.length(); i++) {
                //JSONObject jo_inside = tailleGrille.getJSONObject(i);
                int hauteur = tailleGrille.getInt("hauteur");
                int largeur = tailleGrille.getInt("largeur");
                // terminaison.setHauteur(jo_inside.getInt("hauteur"));
                //terminaison.setLargeur(jo_inside.getInt("largeur"));

                //Ajouter les valeurs dans la ArrayList
                locList.add(tailleGrille);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject  terminaison = obj.getJSONObject("terminaison");

            for (int i = 0; i < terminaison.length(); i++) {
                int x = terminaison.getInt("x");
                int y = terminaison.getInt("y");
                int r = terminaison.getInt("r");
                int g = terminaison.getInt("g");
                int b = terminaison.getInt("b");

                //Ajouter les valeurs dans la ArrayList
                locList.add(terminaison);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return locList;

    }*/
}
