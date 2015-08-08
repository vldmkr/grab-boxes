package grabboxes.drawable;

import processing.core.PApplet;

public abstract class BaseDrawable implements IDrawable {
    private PApplet _parent;

    public BaseDrawable(PApplet parent) {
        _parent = parent;
    }

    public PApplet getParent() {
        return _parent;
    }
}
