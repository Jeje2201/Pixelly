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


            JSONObject jsonRootObject = new JSONObject(json);
            //Racine de l'objet infoGrille
            JSONArray infoGrille = jsonRootObject.optJSONArray("infoGrille");

            //test qui fonctionne pas
//            JSONObject infoGrille = (new JSONObject(json)).getJSONObject("infoGrille");
//            int numGrille = infoGrille.getInt("numGrille");
//            int hauteur = infoGrille.getInt("hauteur");
//            int largeur = infoGrille.getInt("largeur");

            for(int i = 0; i < infoGrille.length(); i++){
                JSONObject jsonObject = infoGrille.getJSONObject(i);
                int numGrille = Integer.parseInt(jsonObject.optString("numGrille").toString());
                int hauteur = Integer.parseInt(jsonObject.optString("hauteur").toString());
                int largeur = Integer.parseInt(jsonObject.optString("largeur").toString());

                Manager.getInstance().ajouterGrille(numGrille,largeur,hauteur);
            }

            //Racine de l'objet terminaison
            //JSONObject  terminaison = jsonRootObject.getJSONObject("terminaison");
            JSONArray terminaison = jsonRootObject.optJSONArray("terminaison");

            for (int i = 0; i < terminaison.length(); i++) {
                JSONObject jsonObject = terminaison.getJSONObject(i);
                int tailleChemin = Integer.parseInt(jsonObject.optString("tailleChemin").toString());
                int x = Integer.parseInt(jsonObject.optString("x").toString());
                int y = Integer.parseInt(jsonObject.optString("y").toString());
                int r = Integer.parseInt(jsonObject.optString("r").toString());
                int g = Integer.parseInt(jsonObject.optString("g").toString());
                int b = Integer.parseInt(jsonObject.optString("b").toString());

                //Ajouter les valeurs dans la methode ajouterTerminaison() de la classe Manager
                Manager.getInstance().ajouterTerminaison(tailleChemin,x,y,r,g,b);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
