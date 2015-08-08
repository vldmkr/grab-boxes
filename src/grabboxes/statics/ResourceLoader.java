package grabboxes.statics;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class ResourceLoader {

    public static final float OFFSET_GRAB = 20.0f;
    public static final float OFFSET_BOX = 1.8f;

    public static PApplet Parent;

    private static PShape _box = null;
    private static PShape _grab = null;
    private static PImage _ground = null;

    private static PShape loadShape(String path) {
        return Parent != null ? Parent.loadShape(path) : null;
    }

    private static PImage loadImage(String path) {
        return Parent != null ? Parent.loadImage(path) : null;
    }

    public static PShape loadBox() {
        return loadShape(PathConstants.MODEL_BOX);
    }

    public static PShape loadGrab() {
        return loadShape(PathConstants.MODEL_GRAB);
    }

    public static PImage loadGround() {
        return loadImage(PathConstants.TEXTURE_GROUND);
    }

    public static PShape getBox() {
        if (_box == null) {
            _box = loadBox();
        }
        return _box;
    }

    public static PShape getGrab() {
        if (_grab == null) {
            _grab = loadGrab();
        }
        return _grab;
    }

    public static PImage getGround() {
        if (_ground == null) {
            _ground = loadGround();
        }
        return _ground;
    }
}
