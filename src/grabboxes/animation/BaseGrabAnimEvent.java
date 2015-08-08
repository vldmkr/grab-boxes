package grabboxes.animation;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;

public abstract class BaseGrabAnimEvent implements IGrabAnimEvent {
    protected Grab _grab = null;
    protected BoxesStack _box = null;

    @Override
    public final void setObjects(Grab grab, BoxesStack box) {
        _grab = grab;
        _box = box;
    }

    @Override
    public void afterTask() {
    }

    @Override
    public void beforeTask() {
    }
}