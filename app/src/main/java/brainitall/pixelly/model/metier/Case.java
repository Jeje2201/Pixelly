package brainitall.pixelly.model.metier;

public abstract class Case {

    private int mR;
    private int mG;
    private int mB;
    private int mX;
    private int mY;
    private boolean mDansChemin;

    public Case(){
        mR = 0;
        mG = 0;
        mB = 0;
        mX = 0;
        mY = 0;
        mDansChemin = false;
    }

    public Case(int x, int y, int r, int g, int b) {
        mR = r;
        mG = g;
        mB = b;
        mX = x;
        mY = y;
        mDansChemin = false;
    }

    public Case(int x, int y) {
        mR = 0;
        mG = 0;
        mB = 0;
        mX = x;
        mY = y;
        mDansChemin = false;
    }

    public int getR() {
        return mR;
    }

    public void setR(int r) {
        mR = r;
    }

    public int getG() {
        return mG;
    }

    public void setG(int g) {
        mG = g;
    }

    public int getB() {
        return mB;
    }

    public void setB(int b) {
        mB = b;
    }

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        mX = x;
    }

    public int getY() {
        return mY;
    }

    public void setY(int y) {
        mY = y;
    }

    public boolean isDansChemin() {
        return mDansChemin;
    }

    public void setDansChemin(boolean dansChemin) {
        mDansChemin = dansChemin;
    }
}
