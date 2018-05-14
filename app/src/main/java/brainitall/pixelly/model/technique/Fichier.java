package brainitall.pixelly.model.technique;

public class Fichier {

    /*
    // methode a revoir !

    public ArrayList<MaTerminaison> loadJSONFromAsset() {
        ArrayList<MaTerminaison> locList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getAssets().open("1.json");
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
            JSONArray m_jArry = obj.getJSONArray("terminaison");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                MaTerminaison terminaison = new MaTerminaison();
                terminaison.setTailleChemin(jo_inside.getInt("tailleChemin"));
                terminaison.setX(jo_inside.getInt("x"));
                terminaison.setY(jo_inside.getInt("y"));
                terminaison.setR(jo_inside.getInt("r"));
                terminaison.setG(jo_inside.getInt("g"));
                terminaison.setB(jo_inside.getInt("b"));

                //Ajouter les valeurs dans la ArrayList
                locList.add(terminaison);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locList;
    }
    */
}
