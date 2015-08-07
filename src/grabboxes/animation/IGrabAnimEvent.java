package grabboxes.animation;

import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.Grab;

public interface IGrabAnimEvent {
	void setObjects(Grab grab, BoxesStack box);

	void beforeTask();
	
	void doTask();
	
	void afterTask();
}
