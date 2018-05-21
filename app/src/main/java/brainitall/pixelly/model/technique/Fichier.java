package brainitall.pixelly.model.technique;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import brainitall.pixelly.controller.Manager;
import brainitall.pixelly.controller.PlayActivity;
import brainitall.pixelly.model.metier.Case;
import brainitall.pixelly.model.metier.CaseSimple;
import brainitall.pixelly.model.metier.Chemin;
import brainitall.pixelly.model.metier.Terminaison;

/**
 * Classe représentant un fichier
 * @author Manon Brun, Jérémy Leriche
 */
public class Fichier {

    /**
     * Nom du fichier
     */
    private String mNomFichier;

    private PlayActivity mPlayActivity;

    /**
     * Constructeur
     * @param nomFichier le nom du fichier donné en paramètre
     */
    public Fichier(String nomFichier, Context context){

        mPlayActivity= (PlayActivity)context;
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
            lireSave(context,"save"+mPlayActivity.getLaGrille().getNumGrille()+".json");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void lireSave(Context context,String nomSauvegarde){
        try {
            File f = new File(context.getFilesDir(),nomSauvegarde);
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer);
            System.out.println("Chaine dans la sauvegarde :"+json);

            // Récupération des données
            JSONObject jsonRootObject = new JSONObject(json);
            //Racine de l'objet chemins
            JSONArray chemins = jsonRootObject.optJSONArray("chemins");
            Chemin c = null;
            for (int i = 0; i < chemins.length(); i++) {
                JSONObject jsonObject = chemins.getJSONObject(i);
                int cheminNumero = Integer.parseInt(jsonObject.optString("CheminNumero").toString());
                int tailleMax = Integer.parseInt(jsonObject.optString("TailleMax").toString());
                int r = Integer.parseInt(jsonObject.optString("r").toString());
                int g = Integer.parseInt(jsonObject.optString("g").toString());
                int b = Integer.parseInt(jsonObject.optString("b").toString());




                //Racine de l'objet cases
                JSONArray cases = jsonObject.optJSONArray("Cases");

                for (int j = 0; j < cases.length(); j++) {
                    JSONObject jsonObj = cases.getJSONObject(j);
                    int x = Integer.parseInt(jsonObj.optString("x").toString());
                    int y = Integer.parseInt(jsonObj.optString("y").toString());
                    if(j==0){
                        Terminaison t = new Terminaison(tailleMax,y,x,r,g,b);
                        c = new Chemin(t);
                        mPlayActivity.getLaGrille().getLesChemins().add(c);
                    }
                    else{
                        c.ajouterCase(mPlayActivity.getLaGrille().getCase(y,x));
                    }



                }}



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void ecrireSave(Context context, String nomSauvegarde){


        JSONObject jsonFinal = new JSONObject();

        try {

            jsonFinal.put("NomNiveau", mPlayActivity.getLaGrille().getNumGrille());
            jsonFinal.put("NombreChemins", mPlayActivity.getLaGrille().getLesChemins().size());

            //Je créé un objet dans lequel j'insererais toutes les infos
            JSONArray listeChemins = new JSONArray(); //Je créé une liste dans laquel je rentre tous mes objets a chaque fois

            List<Chemin> lesChemins = mPlayActivity.getLaGrille().getLesChemins();//pour chaque chemins

            for(int compteurChemin=0;compteurChemin<lesChemins.size();compteurChemin++) {
                JSONObject infosChemins=new JSONObject();
                //J'insere toutes les infos
                infosChemins.put("CheminNumero",compteurChemin);
                infosChemins.put("TailleMax", lesChemins.get(compteurChemin).getTailleMax());

                int [] couleurs = lesChemins.get(compteurChemin).getCouleurChemin();

                infosChemins.put("r", couleurs[0]);
                infosChemins.put("g", couleurs[1]);
                infosChemins.put("b", couleurs[2]);

                JSONArray listeCases = new JSONArray();
                List<Case> lesCases = lesChemins.get(compteurChemin).getCasesChemin();//pour chaques cases

                for (int compteurCase = 0; compteurCase < lesChemins.get(compteurChemin).getTailleMax(); compteurCase++) {

                    JSONObject infoCase=new JSONObject();

                    infoCase.put("x",mPlayActivity.getLaGrille().getLesChemins().get(compteurChemin).getCasesChemin().get(compteurCase).getY());
                    infoCase.put("y", mPlayActivity.getLaGrille().getLesChemins().get(compteurChemin).getCasesChemin().get(compteurCase).getX());

                    listeCases.put(infoCase);

                } //fin chaques cases

                infosChemins.put("Cases", listeCases);

                listeChemins.put(infosChemins); //j'ajoute dans ma liste de chemin objet chemin avec toutes ses infos
            }

            jsonFinal.put("chemins",listeChemins); //J'ajoute a la fin l'objet "chemin" avec sa liste de chemins

            System.out.println(jsonFinal.toString());

            FileWriter file = new FileWriter(context.getFilesDir()+"/"+nomSauvegarde);
            file.write(jsonFinal.toString());
            file.flush();
            file.close();
        }
        catch(JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
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