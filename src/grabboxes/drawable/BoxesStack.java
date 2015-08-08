package grabboxes.drawable;

import grabboxes.statics.ResourceLoader;
import processing.core.PApplet;
import processing.core.PShape;

import java.util.ArrayList;

public class BoxesStack extends BaseDrawable {
    public static final int EMPTY_BOX = -1;
    public static final int EMPTY_STACK = -2;

    private final ArrayList<Integer> _weights = new ArrayList<Integer>();
    private final PShape _box;

    private float _posX = 0.0f;
    private float _posY = 0.0f;
    private float _posZ = 0.0f;

    public BoxesStack(PApplet parent, int weight) {
        super(parent);
        _box = ResourceLoader.getBox();
        pushBox(weight);
    }

    public BoxesStack(PApplet parent) {
        this(parent, EMPTY_BOX);
    }

    public boolean pushBox(int weight) {
        if (isValidWeight(weight) || isEmptyBox(weight)) {
            _weights.add(weight);
            return true;
        }
        return false;
    }

    public void pushBox() {
        _weights.add(EMPTY_BOX);
    }

    public int popBox() {
        if (!_weights.isEmpty()) {
            return _weights.remove(_weights.size() - 1);
        }
        return EMPTY_STACK;
    }

    public int setBoxWeight(int pos, int weight) {
        if (pos < _weights.size()) {
            if (isValidWeight(weight) || isEmptyBox(weight)) {
                return _weights.set(pos, weight);
            }
        }
        return EMPTY_STACK;
    }

    public int getBoxWeight(int pos) {
        if (pos < _weights.size()) {
            return _weights.get(pos);
        }
        return EMPTY_STACK;
    }

    public void setPos(float x, float y, float z) {
        _posX = x;
        _posY = y;
        _posZ = z;
    }

    public float getPosX() {
        return _posX;
    }

    public void setPosX(float x) {
        _posX = x;
    }

    public float getPosY() {
        return _posY;
    }

    public void setPosY(float y) {
        _posY = y;
    }

    public float getPosZ() {
        return _posZ;
    }

    public void setPosZ(float z) {
        _posZ = z;
    }

    public float getTopY() {
        return getTopPlaceY() + _box.getHeight();
    }

    public float getTopPlaceY() {
        return -_weights.size() * _box.getHeight();
    }

    public int getBoxCount() {
        return _weights.size();
    }

    @Override
    public void draw() {
        getParent().pushMatrix();
        getParent().translate(_posX, _posY, _posZ);
        for (int i = 0; i < _weights.size(); i++) {
            getParent().shape(_box, 0.0f, (-i * _box.getHeight()) + ResourceLoader.OFFSET_BOX);
            drawBoxWeight(getParent(), _weights.get(i), i);
        }
        getParent().popMatrix();
    }

    public static void drawBoxWeight(PApplet parent, int weight, int stackPos) {
        parent.pushMatrix();
        parent.translate(0.0f, (-ResourceLoader.getBox().getHeight() * (stackPos + 0.5f)) + ResourceLoader.OFFSET_BOX, ResourceLoader.getBox().getWidth() * 0.51f);
        parent.scale(0.05f);
        parent.textAlign(PApplet.CENTER);
        parent.textSize(150);
        parent.pushStyle();
        parent.fill(89, 47, 16);
        if (isValidWeight(weight)) {
            parent.text(weight, 0.0f, 0.0f, 0.0f);
        } else if (weight != EMPTY_BOX) {
            parent.text("?", 0.0f, 0.0f, 0.0f);
        }
        parent.popStyle();
        parent.popMatrix();
    }

    public static void drawSetupBoxWeight(PApplet parent, float x, float y, float z, int weight, int stackPos) {
        parent.pushMatrix();

        parent.translate(x, y - ResourceLoader.OFFSET_BOX, z);
        parent.rotateX(PApplet.HALF_PI);
        parent.translate(0.0f, 3f, (ResourceLoader.getBox().getHeight() * (stackPos + 0.95f)));
        parent.scale(0.05f);
        parent.textAlign(PApplet.CENTER);
        parent.textSize(150);
        parent.pushStyle();
        parent.fill(89, 47, 16);
        if (isValidWeight(weight)) {
            parent.text(weight, 0.0f, 0.0f, 0.0f);
        } else if (weight != EMPTY_BOX) {
            parent.text("?", 0.0f, 0.0f, 0.0f);
        }
        parent.popStyle();
        parent.popMatrix();
    }

    public static boolean isValidWeight(int weight) {
        return (weight >= 0 && weight <= 99);
    }

    public static boolean isEmptyBox(int weight) {
        return weight == EMPTY_BOX;
    }
}
