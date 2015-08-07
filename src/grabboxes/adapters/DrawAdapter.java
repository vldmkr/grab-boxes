package grabboxes.adapters;

import grabboxes.algo.Algo;
import grabboxes.algo.BoxWraper;
import grabboxes.algo.Step;
import grabboxes.drawable.BaseDrawable;
import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;
import grabboxes.drawable.TexturedPlane;
import grabboxes.statics.ResourceLoader;

import java.util.ArrayList;

import processing.core.PApplet;

public class DrawAdapter extends BaseDrawable {

    public final GrabAnimEventsAdapter _eventsAdapter;

    final ArrayList<BoxesStack> _boxesList = new ArrayList<BoxesStack>();
    final Grab _grab;
    final TexturedPlane _plane;
    private BoxesStack operBoxStack = null;
    private int operBoxPos = 0;
    private boolean isChangingLabelShown = false;
    public Algo _algo;

    public DrawAdapter(PApplet parent) {
        super(parent);
        _eventsAdapter = new GrabAnimEventsAdapter(this);
        _grab = new Grab(getParent());
        _plane = new TexturedPlane(getParent());
        _algo = new Algo(_boxesList, _grab);

        hideGrab();
    }

    public void makePath(ArrayList<Step> way) {
        if (way != null) {
            _grab.setHomeY(-ResourceLoader.getBox().getHeight() * 2.0f);
            _eventsAdapter.clear();
            _eventsAdapter.addGrabWakeUp();

            int destIdx = -1;
            for (Step val : way) {
                if (val.begin instanceof BoxWraper) {
                    int idxEnd = ((BoxWraper) val.end).getIdx();
                    if (destIdx < 0) {
                        _eventsAdapter.addGoToBox(idxEnd);
                        _eventsAdapter.addPutDownBox(idxEnd);
                        destIdx = idxEnd;
                    } else {
                        if (idxEnd == destIdx) {
                            _eventsAdapter.addGoToBox(idxEnd);
                            _eventsAdapter.addPutDownBox(idxEnd);
                        } else {
                            _eventsAdapter.addGoToBox(idxEnd);
                            _eventsAdapter.addPickUpBox(idxEnd);
                        }
                    }
                } else {
                    int idxEnd = ((BoxWraper) val.end).getIdx();
                    _eventsAdapter.addGoToBox(idxEnd);
                    _eventsAdapter.addPickUpBox(idxEnd);
                }
            }
        }
    }

    public void setPlaneSize(float size) {
        _plane.setSize(size);
    }

    public float getPlaneSize() {
        return _plane.getSize();
    }

    public void doWork() {
        _eventsAdapter.isActiveDeque(true);
    }

    public void pauseWork() {
        _eventsAdapter.isActiveDeque(false);
    }

    public void setGrabPos(float x, float z) {
        _grab.setPosX(x);
        _grab.setPosZ(z);
    }

    public void hideGrab() {
        _grab.setPosZ(1000.f);
    }

    public void draw() {
        _plane.draw();
        _eventsAdapter.draw();
        for (BoxesStack val : _boxesList) {
            val.draw();
        }
        if (isChangingLabelShown && operBoxStack != null) {
            BoxesStack.drawSetupBoxWeight(getParent(), operBoxStack.getPosX(), operBoxStack.getPosY(), operBoxStack.getPosZ(), operBoxStack.getBoxWeight(operBoxPos),
                    operBoxStack.getBoxCount() - 1);
        }
        _grab.draw();
    }

    public int changeBoxLabel(int stackPos, int boxPos, int weight) {
        if (stackPos < _boxesList.size()) {
            operBoxStack = _boxesList.get(stackPos);
            operBoxPos = boxPos;
            return operBoxStack.setBoxWeight(boxPos, weight);
        }
        return BoxesStack.EMPTY_STACK;
    }

    public boolean isChangingLabelShown() {
        return isChangingLabelShown;
    }

    public void setChangingLabelShown(boolean isShown) {
        isChangingLabelShown = isShown;
    }

    public int addBox(float x, float z, int weight) {
        BoxesStack box = new BoxesStack(getParent(), weight);
        box.setPos(x, 0.0f, z);
        _boxesList.add(box);

        return _boxesList.size() - 1;
    }

    public void cleanBoxes() {
        _boxesList.clear();
        operBoxStack = null;
        _grab.withBox(false);
    }

    public boolean isEmpty() {
        return _boxesList.isEmpty();
    }

    public boolean isEnoughBoxes() {
        return +_boxesList.size() > 1;
    }
}
