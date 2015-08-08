package grabboxes.animation;

import grabboxes.drawable.Grab;
import grabboxes.statics.Animation;

public class EventGrabWakeUp extends BaseGrabAnimEvent {

    public EventGrabWakeUp() {
    }

    public EventGrabWakeUp(Grab grab) {
        setObjects(grab, null);
    }

    @Override
    public void doTask() {
        Animation.setInitial(_grab.getPosX(), _grab.getPosY(), _grab.getPosZ());
        Animation.setGoal(_grab.getPosX(), _grab.getHomeY(), _grab.getPosZ());
    }

}
