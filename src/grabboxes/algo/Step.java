package grabboxes.algo;

public class Step {
    private float _weight;
    private float _s;

    public final AlgoWraper begin;
    public final AlgoWraper end;

    public static float S(float bX, float bY, float eX, float eY) {
        return (float) Math.sqrt((eY - bY) * (eY - bY) + (eX - bX) * (eX - bX));
    }

    public Step(float w, AlgoWraper b, AlgoWraper e) {
        _weight = w;
        begin = b;
        end = e;
        _s = S(begin.getPosX(), begin.getPosY(), end.getPosX(), end.getPosY());
    }

    public float getWeight() {
        return _weight;
    }

    public float getS() {
        return _s;
    }
}
