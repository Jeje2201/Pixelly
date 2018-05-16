package brainitall.pixelly.model.technique;

import android.content.Context;

import org.json.JSONArray;
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

        System.out.println("LALA");
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
<<<<<<< HEAD
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray jsonArray = jsonRootObject.optJSONArray("infoGrille");
=======
            JSONObject obj = new JSONObject(json);
            JSONObject tailleGrille = obj.getJSONObject("infoGrille");
>>>>>>> 1da9d2169f0c399dec60b4c991539baceaa091fc

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int numGrille = Integer.parseInt(jsonObject.optString("numGrille").toString());
                int hauteur = Integer.parseInt(jsonObject.optString("hauteur").toString());
                int largeur = Integer.parseInt(jsonObject.optString("largeur").toString());


                Manager.getInstance().ajouterGrille(numGrille,largeur,hauteur);
            }
            JSONObject  terminaison = jsonRootObject.getJSONObject("terminaison");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int tailleChemin = Integer.parseInt(jsonObject.optString("tailleChemin").toString());
                int x = Integer.parseInt(jsonObject.optString("x").toString());
                int y = Integer.parseInt(jsonObject.optString("y").toString());
                int r = Integer.parseInt(jsonObject.optString("r").toString());
                int g = Integer.parseInt(jsonObject.optString("g").toString());
                int b = Integer.parseInt(jsonObject.optString("b").toString());


                //Ajouter les valeurs dans la ArrayList
                Manager.getInstance().ajouterTerminaison(tailleChemin,x,y,r,g,b);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }

    }
}
