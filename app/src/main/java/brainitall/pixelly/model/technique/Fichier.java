package brainitall.pixelly.model.technique;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import brainitall.pixelly.controller.Manager;
import brainitall.pixelly.model.metier.Case;
import brainitall.pixelly.model.metier.Chemin;
import brainitall.pixelly.model.metier.Terminaison;
import java.util.List;
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
    public void lireFichier(Context context) {

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
                int numGrille = Integer.parseInt(jsonObject.optString("numGrille").toString());
                int hauteur = Integer.parseInt(jsonObject.optString("hauteur").toString());
                int largeur = Integer.parseInt(jsonObject.optString("largeur").toString());

                //Ajouter les valeurs dans la grille via le Manager
                Manager.getInstance().ajouterGrille(numGrille,largeur,hauteur);
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
                Manager.getInstance().ajouterTerminaison(tailleChemin,x,y,r,g,b);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de creer une sauvegarde contenant des chemins
     */
    public void SaveFile(){

        JSONObject jsonFinal = new JSONObject();

        try {

            jsonFinal.put("NomNiveau", Manager.getInstance().getLaGrille().getNumGrille());

            JSONObject infosChemins=new JSONObject(); //Je créé un objet dans lequel j'insererais toutes les infos
            JSONArray listeChemins = new JSONArray(); //Je créé une liste dans laquel je rentre tous mes objets a chaque fois

            JSONArray listeCases = new JSONArray();
            JSONObject infoCase=new JSONObject();

            List<Chemin> lesChemins = Manager.getInstance().getLaGrille().getLesChemins();//pour chaque chemins

            System.out.println("test1: "+lesChemins);
            System.out.println("test2: "+lesChemins.size());

            for(int compteurChemin=0;compteurChemin<lesChemins.size();compteurChemin++) {

                //J'insere toutes les infos
                infosChemins.put("TailleChemin", lesChemins.get(compteurChemin).getCouleurChemin());
                infosChemins.put("r", 255);
                infosChemins.put("g", 255);
                infosChemins.put("b", 255);

                List<Case> lesCases = lesChemins.get(compteurChemin).getCasesChemin();//pour chaques cases
                System.out.println("test3: "+lesCases);
                System.out.println("test4: "+lesCases.size());

                for (int compteurCase = 0; compteurCase < lesCases.size(); compteurCase++) {

                    infoCase.put("x", lesCases.get(compteurCase).getX());
                    infoCase.put("y", lesCases.get(compteurCase).getY());

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