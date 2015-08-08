package grabboxes.algo;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;

import java.util.ArrayList;

public class Algo {
    private final ArrayList<BoxesStack> _boxesList;
    private final Grab _grab;

    public Algo(final ArrayList<BoxesStack> boxes, final Grab grab) {
        _boxesList = boxes;
        _grab = grab;
    }

    public long A(ArrayList<Step> way) {
        long a = 0;
        for (Step val : way) {
            a += (long) val.getWeight() * (long) val.getS();
        }
        return a;
    }

    private ArrayList<Step> WorkHard(int firstBoxIdx, int destBoxIdx) {
        ArrayList<Step> result = new ArrayList<Step>();
        ArrayList<BoxWraper> boxWraperList = new ArrayList<BoxWraper>();
        GrabWraper grabWraper = new GrabWraper(_grab);

        for (int i = 0; i < _boxesList.size(); i++) {
            if (i != firstBoxIdx && i != destBoxIdx) {
                boxWraperList.add(new BoxWraper(_boxesList, i));
            }
        }
        BoxWraper firstBox = new BoxWraper(_boxesList, firstBoxIdx);
        BoxWraper destBox = new BoxWraper(_boxesList, destBoxIdx);

        result.add(new Step(grabWraper.getWeight(), grabWraper, firstBox));
        result.add(new Step(grabWraper.getWeight() + firstBox.getWeight(), firstBox, destBox));

        for (int i = 0; i < boxWraperList.size(); i++) {
            result.add(new Step(grabWraper.getWeight(), destBox, boxWraperList.get(i)));
            result.add(new Step(grabWraper.getWeight() + boxWraperList.get(i).getWeight(), boxWraperList.get(i), destBox));
        }
        return result;
    }

    public ArrayList<Step> BruteForce() {
        ArrayList<Step> bestWorkWay = null;
        long bestA = Long.MAX_VALUE;
        for (int f = 0; f < _boxesList.size(); f++) {
            for (int d = 0; d < _boxesList.size(); d++) {
                if (f != d) {
                    ArrayList<Step> tempWorkWay = WorkHard(f, d);
                    long a = A(tempWorkWay);
                    if (a < bestA) {
                        bestWorkWay = tempWorkWay;
                        bestA = a;
                    }
                }
            }
        }
        return bestWorkWay;
    }
}
