package grabboxes.animation;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;
import grabboxes.statics.Animation;

public class EventGoToBox extends BaseGrabAnimEvent {

    public EventGoToBox() {
    }

    public EventGoToBox(Grab grab, BoxesStack box) {
        setObjects(grab, box);
    }

    @Override
    public void doTask() {
        Animation
                .setInitial(_grab.getPosX(), _grab.getHomeY(), _grab.getPosZ());
        Animation.setGoal(_box.getPosX(), _grab.getHomeY(), _box.getPosZ());
    }
}
