package grabboxes.animation;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;
import grabboxes.statics.Animation;

public class EventPickUpFromBox extends BaseGrabAnimEvent {

	public EventPickUpFromBox() {
	}

	public EventPickUpFromBox(Grab grab, BoxesStack box) {
		setObjects(grab, box);
	}

	@Override
	public void doTask() {
		Animation.setInitial(_box.getPosX(), _box.getTopPlaceY(), _box.getPosZ());
		Animation.setGoal(_grab.getPosX(), _grab.getHomeY(), _grab.getPosZ());
	}

}