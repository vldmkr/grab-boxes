package grabboxes.animation;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;
import grabboxes.statics.Animation;
import grabboxes.statics.ResourceLoader;

public class EventPutDownToBox extends BaseGrabAnimEvent {

    public EventPutDownToBox() {
    }

    public EventPutDownToBox(Grab grab, BoxesStack box) {
        setObjects(grab, box);
    }

    @Override
    public void doTask() {
        Animation.setInitial(_grab.getPosX(), _grab.getHomeY(), _grab.getPosZ());
        Animation.setGoal(_box.getPosX(), _box.getTopPlaceY(), _box.getPosZ());
    }

    @Override
    public void afterTask() {
        _grab.setHomeY(Math.min(_grab.getHomeY(), _box.getTopPlaceY() - ResourceLoader.getBox().getHeight()));
    }
}