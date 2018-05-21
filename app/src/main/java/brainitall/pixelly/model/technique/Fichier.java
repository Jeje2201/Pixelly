package brainitall.pixelly.model.technique;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import brainitall.pixelly.controller.Manager;
import brainitall.pixelly.controller.PlayActivity;

/**
 * Classe représentant un fichier
 * @author Manon Brun, Jérémy Leriche
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
     * Permet de lire un fichier Json contenant un niveau et stocke les valeurs dans la grille
     */
    public void lireFichier(Context context, Context activity) {
        PlayActivity playActivity = (PlayActivity) activity;
        System.out.println("On tente le chargement du fichier");

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


            for(int i = 0; i < infoGrille.length(); i++){
                JSONObject jsonObject = infoGrille.getJSONObject(i);
                String nomGrille = jsonObject.optString("nomGrille").toString();
                int numGrille = Integer.parseInt(jsonObject.optString("numGrille").toString());
                int hauteur = Integer.parseInt(jsonObject.optString("hauteur").toString());
                int largeur = Integer.parseInt(jsonObject.optString("largeur").toString());

                //Ajouter les valeurs dans la grille via le Manager
                //Manager.getInstance().ajouterGrille(numGrille,largeur,hauteur);
                //Manager.getInstance().ajouterGrille(numGrille,hauteur,largeur,nomGrille);
                playActivity.ajouterGrille(numGrille,hauteur,largeur,nomGrille);
            }

            //Racine de l'objet terminaison
            JSONArray terminaison = jsonRootObject.optJSONArray("terminaison");

            for (int i = 0; i < terminaison.length(); i++) {
                JSONObject jsonObject = terminaison.getJSONObject(i);
                int tailleChemin = Integer.parseInt(jsonObject.optString("tailleChemin").toString());
                int x = Integer.parseInt(jsonObject.optString("x").toString());
                int y = Integer.parseInt(jsonObject.optString("y").toString());
                int r = Integer.parseInt(jsonObject.optString("r").toString());
                int g = Integer.parseInt(jsonObject.optString("g").toString());
                int b = Integer.parseInt(jsonObject.optString("b").toString());

                //Ajouter les valeurs dans la grille via le Manager
                //Manager.getInstance().ajouterTerminaison(tailleChemin,x,y,r,g,b);
                playActivity.ajouterTerminaison(tailleChemin,x,y,r,g,b);


            }
            System.out.println("Le num de la grille : "+ playActivity.getLaGrille().getNumGrille());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void lireSave(Context context, Context activity, String nomSauvegarde){
        PlayActivity playActivity = (PlayActivity) activity;
        try {
            File f = new File(context.getFilesDir(),nomSauvegarde);
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String lecture = new String(buffer);
            JSONObject jsonRootObject = new JSONObject(lecture);

            // Récupération des données
            



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * Permet de lire un fichier Json de sauvegarde et stocke les valeurs
     */
    /*
    public void lireFichierSave(Context context) {

        String json = null;
        try {
            InputStream is = context.getAssets().open(mNomFichier);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonRootObject = new JSONObject(json);
            //Racine de l'objet chemins
            JSONArray chemins = jsonRootObject.optJSONArray("chemins");

            for (int i = 0; i < chemins.length(); i++) {
                JSONObject jsonObject = chemins.getJSONObject(i);
                int tailleChemin = Integer.parseInt(jsonObject.optString("tailleChemin").toString());
                int r = Integer.parseInt(jsonObject.optString("r").toString());
                int g = Integer.parseInt(jsonObject.optString("g").toString());
                int b = Integer.parseInt(jsonObject.optString("b").toString());

                //Ajouter les valeurs dans le Chemin via le Manager



                //Racine de l'objet cases
                JSONArray cases = jsonRootObject.optJSONArray("cases");

                for (int j = 0; j < cases.length(); j++) {
                    JSONObject jsonObj = cases.getJSONObject(j);
                    int x = Integer.parseInt(jsonObj.optString("x").toString());
                    int y = Integer.parseInt(jsonObj.optString("y").toString());

                    //Ajouter les valeurs dans le Chemin via le Manager ajouter chemin
                   Terminaison t = new Terminaison(tailleChemin,x,y,r,g,b);

                }
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}