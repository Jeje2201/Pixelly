package brainitall.pixelly.model.technique;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import brainitall.pixelly.controller.Manager;

/**
 * Classe représentant un fichier
 */
public class Fichier {

    /**
     * Nom du fichier
     */
    private String mNomFichier;

    /**
     * Constructeur
     * @param nomFichier le nom du fichier donné en paramètre
     */
    public Fichier(String nomFichier){
        mNomFichier = nomFichier;

    }

    /**
     * Permet de lire un fichier Json et stocke les valeurs dans la grille
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

<<<<<<< HEAD

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
=======
        try {

            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray jsonArray = jsonRootObject.optJSONArray("infoGrille");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
>>>>>>> 637ee8caf29c991af1d771796ad78b01fb880e9a
                int numGrille = Integer.parseInt(jsonObject.optString("numGrille").toString());
                int hauteur = Integer.parseInt(jsonObject.optString("hauteur").toString());
                int largeur = Integer.parseInt(jsonObject.optString("largeur").toString());

<<<<<<< HEAD
                Manager.getInstance().ajouterGrille(numGrille,largeur,hauteur);
            }

            //Racine de l'objet terminaison
            //JSONObject  terminaison = jsonRootObject.getJSONObject("terminaison");
            JSONArray terminaison = jsonRootObject.optJSONArray("terminaison");

            for (int i = 0; i < terminaison.length(); i++) {
                JSONObject jsonObject = terminaison.getJSONObject(i);
=======
                // Association de la grille
                Manager.getInstance().ajouterGrille(numGrille,largeur,hauteur);
            }

            JSONObject  terminaison = jsonRootObject.getJSONObject("terminaison");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
>>>>>>> 637ee8caf29c991af1d771796ad78b01fb880e9a
                int tailleChemin = Integer.parseInt(jsonObject.optString("tailleChemin").toString());
                int x = Integer.parseInt(jsonObject.optString("x").toString());
                int y = Integer.parseInt(jsonObject.optString("y").toString());
                int r = Integer.parseInt(jsonObject.optString("r").toString());
                int g = Integer.parseInt(jsonObject.optString("g").toString());
                int b = Integer.parseInt(jsonObject.optString("b").toString());

<<<<<<< HEAD
                //Ajouter les valeurs dans la methode ajouterTerminaison() de la classe Manager
=======

                //Ajouter les valeurs dans la grille via le Manager
>>>>>>> 637ee8caf29c991af1d771796ad78b01fb880e9a
                Manager.getInstance().ajouterTerminaison(tailleChemin,x,y,r,g,b);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
