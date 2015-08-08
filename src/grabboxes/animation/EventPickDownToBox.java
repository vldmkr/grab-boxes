package grabboxes.animation;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;
import grabboxes.statics.Animation;

public class EventPickDownToBox extends BaseGrabAnimEvent {

    public EventPickDownToBox() {
    }

    public EventPickDownToBox(Grab grab, BoxesStack box) {
        setObjects(grab, box);
    }

    @Override
    public void doTask() {
        Animation.setInitial(_grab.getPosX(), _grab.getHomeY(), _grab.getPosZ());
        float y = _box.getBoxCount() > 0 ? _box.getTopY() : _box.getTopPlaceY();
        Animation.setGoal(_box.getPosX(), y, _box.getPosZ());
    }

    @Override
    public void afterTask() {
        _grab.withBox(_box.popBox());
    }
}
