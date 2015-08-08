package grabboxes.drawable;

import grabboxes.statics.ResourceLoader;
import processing.core.PApplet;
import processing.core.PShape;

public class Grab extends BaseDrawable {

    private final PShape _grab;
    private final PShape _box;

    private boolean _withBox = false;
    private int _boxWeight = BoxesStack.EMPTY_BOX;

    private float _posX = 0.0f;
    private float _posY = 0.0f;
    private float _posZ = 0.0f;

    private float _homeY = 0.0f;

    public Grab(PApplet parent) {
        super(parent);
        _grab = ResourceLoader.getGrab();
        _box = ResourceLoader.getBox();
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

    public float getHomeY() {
        return _homeY;
    }

    public void setHomeY(float y) {
        _homeY = y;
    }

    public boolean withBox() {
        return _withBox;
    }

    public void withBox(boolean withBox) {
        _withBox = withBox;
        _boxWeight = BoxesStack.EMPTY_BOX;
    }

    public void withBox(int weight) {
        boolean isValidWeight = BoxesStack.isValidWeight(weight);
        withBox(isValidWeight || BoxesStack.isEmptyBox(weight));
        if (isValidWeight) {
            _boxWeight = weight;
        }
    }

    @Override
    public void draw() {
        getParent().pushMatrix();
        getParent().translate(_posX, _posY, _posZ);

        if (_withBox) {
            getParent().shape(_box, 0.0f, ResourceLoader.OFFSET_BOX);
            BoxesStack.drawBoxWeight(getParent(), _boxWeight, 0);
        }

        getParent().rotateX(PApplet.PI);
        getParent().shape(_grab, 0.0f, ResourceLoader.OFFSET_GRAB);
        getParent().popMatrix();
    }

    public int getBoxWeight() {
        return _boxWeight;
    }

    public void setBoxWeight(int weight) {
        _boxWeight = weight;
    }
}
