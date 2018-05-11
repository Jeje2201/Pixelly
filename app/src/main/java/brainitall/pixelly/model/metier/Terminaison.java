package brainitall.pixelly.model.metier;

public class Terminaison extends Case {
    private int mTailleChemin;

    public Terminaison(int tailleChemin, int x, int y, int r, int g, int b) {
        super(x,y,r,g,b);
        mTailleChemin = tailleChemin;
    }

    public int getTailleChemin() {
        return mTailleChemin;
    }

    public void setTailleChemin(int tailleChemin) {
        mTailleChemin = tailleChemin;
    }

}

