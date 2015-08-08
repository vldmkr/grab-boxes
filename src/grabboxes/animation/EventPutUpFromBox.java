package grabboxes.animation;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;
import grabboxes.statics.Animation;

public class EventPutUpFromBox extends BaseGrabAnimEvent {

    private boolean _wasWithBox = true;

    public EventPutUpFromBox() {
    }

    public EventPutUpFromBox(Grab grab, BoxesStack box) {
        setObjects(grab, box);
    }

    @Override
    public void doTask() {
        float y = _wasWithBox ? _box.getTopY() : _box.getTopPlaceY();
        Animation.setInitial(_box.getPosX(), y, _box.getPosZ());
        Animation.setGoal(_grab.getPosX(), _grab.getHomeY(), _grab.getPosZ());
    }

    @Override
    public void beforeTask() {
        if (_wasWithBox = _grab.withBox()) {
            _box.pushBox(_grab.getBoxWeight());
            _grab.withBox(false);
        }
    }
}