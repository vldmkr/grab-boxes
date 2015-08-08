package grabboxes.drawable;

import grabboxes.statics.ResourceLoader;
import processing.core.PApplet;

public class TexturedPlane extends BaseDrawable {

    private float _size = 10.0f;

    public TexturedPlane(PApplet parent) {
        super(parent);
        getParent().textureMode(PApplet.NORMAL);
        getParent().noStroke();
    }

    @Override
    public void draw() {
        getParent().beginShape(PApplet.QUADS);
        getParent().texture(ResourceLoader.getGround());

        // +Z "front" face
        getParent().vertex(-_size, -1.0f, _size, 0, 0);
        getParent().vertex(_size, -1.0f, _size, 1, 0);
        getParent().vertex(_size, 1.0f, _size, 1, 1);
        getParent().vertex(-_size, 1.0f, _size, 0, 1);

        // -Z "back" face
        getParent().vertex(_size, -1.0f, -_size, 0, 0);
        getParent().vertex(-_size, -1.0f, -_size, 1, 0);
        getParent().vertex(-_size, 1.0f, -_size, 1, 1);
        getParent().vertex(_size, 1.0f, -_size, 0, 1);

        // +Y "bottom" face
        getParent().vertex(-_size, 1.0f, _size, 0, 0);
        getParent().vertex(_size, 1.0f, _size, 1.0f, 0);
        getParent().vertex(_size, 1.0f, -_size, 1, 1);
        getParent().vertex(-_size, 1.0f, -_size, 0, 1);

        // -Y "top" face
        getParent().vertex(-_size, -1.0f, -_size, 0, 0);
        getParent().vertex(_size, -1.0f, -_size, 1, 0);
        getParent().vertex(_size, -1.0f, _size, 1, 1);
        getParent().vertex(-_size, -1.0f, _size, 0, 1);

        // +X "right" face
        getParent().vertex(_size, -1.0f, _size, 0, 0);
        getParent().vertex(_size, -1.0f, -_size, 1, 0);
        getParent().vertex(_size, 1.0f, -_size, 1, 1);
        getParent().vertex(_size, 1.0f, _size, 0, 1);

        // -X "left" face
        getParent().vertex(-_size, -1.0f, -_size, 0, 0);
        getParent().vertex(-_size, -1.0f, _size, 1, 0);
        getParent().vertex(-_size, 1.0f, _size, 1, 1);
        getParent().vertex(-_size, 1.0f, -_size, 0, 1);

        getParent().endShape();
    }

    public float getSize() {
        return _size;
    }

    public void setSize(float size) {
        _size = size;
    }

}
