package brainitall.pixelly.model;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe Utilitaire
 */
public class Utilitaire {
    /**
     * Permet de lire un fichier de mémoire interne contenant l'état du jeu
     * @param context context de l'application
     * @return le numéro du dernier niveau débloqué
     */
    public static int lireEtatJeu(Context context){
        int state = 0;
        try {
            File f = new File(context.getFilesDir(),"state.json");
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String lecture = new String(buffer);
            JSONObject jsonRootObject = new JSONObject(lecture);
            state = Integer.parseInt(jsonRootObject.optString("etatJeu").toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return state;
    }


    /**
     * Permet d'écrire sur un fichier de mémoire interne l'état du jeu
     * @param context contexte de l'application
     */
    public static void ecrireEtatJeu(Context context, int numDernierNiveau){
        //LevelActivity levelActivity = (LevelActivity) activity;
        //Activity mActivity = (Activity) activity;
        JSONObject jsonFinal = new JSONObject();
        try {
            jsonFinal.put("etatJeu", numDernierNiveau);
            String mJsonResponse = jsonFinal.toString();
            System.out.println("Json a sauvegarder :"+mJsonResponse);
            FileWriter file = new FileWriter(context.getFilesDir()+"/state.json");
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
