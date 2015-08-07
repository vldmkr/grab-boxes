package grabboxes.adapters;

import grabboxes.animation.EventGoToBox;
import grabboxes.animation.EventGrabWakeUp;
import grabboxes.animation.EventPickDownToBox;
import grabboxes.animation.EventPickUpFromBox;
import grabboxes.animation.EventPutDownToBox;
import grabboxes.animation.EventPutUpFromBox;
import grabboxes.animation.IGrabAnimEvent;
import grabboxes.drawable.BoxesStack;
import grabboxes.drawable.IDrawable;
import grabboxes.statics.Animation;

import java.util.ArrayDeque;

public class GrabAnimEventsAdapter implements IDrawable {

	private final DrawAdapter _adapter;
	private final ArrayDeque<IGrabAnimEvent> _events = new ArrayDeque<IGrabAnimEvent>();
	private IGrabAnimEvent _prevEvent = null;

	private boolean _isActiveDeque = false;

	public GrabAnimEventsAdapter(DrawAdapter adapter) {
		_adapter = adapter;
	}

	private BoxesStack getBoxIdx(int boxIdx) {
		return boxIdx < _adapter._boxesList.size() ? _adapter._boxesList.get(boxIdx) : null;
	}

	public void addGoToBox(int boxIdx) {
		BoxesStack box = getBoxIdx(boxIdx);
		if (box != null) {
			_events.addLast(new EventGoToBox(_adapter._grab, box));
		}
	}

	public void addPickUpBox(int boxIdx) {
		BoxesStack box = getBoxIdx(boxIdx);
		if (box != null) {
			_events.addLast(new EventPickDownToBox(_adapter._grab, box));
			_events.addLast(new EventPickUpFromBox(_adapter._grab, box));
		}
	}

	public void addPutDownBox(int boxIdx) {
		BoxesStack box = getBoxIdx(boxIdx);
		if (box != null) {
			_events.addLast(new EventPutDownToBox(_adapter._grab, box));
			_events.addLast(new EventPutUpFromBox(_adapter._grab, box));
		}
	}

	public void addGrabWakeUp() {
		_events.addLast(new EventGrabWakeUp(_adapter._grab));
	}

	public boolean isActiveDeque() {
		return _isActiveDeque;
	}

	public void isActiveDeque(boolean flag) {
		_isActiveDeque = flag;
	}

	public void clear() {
		_events.clear();
	}

	public boolean isEmpty() {
		return _events.isEmpty();
	}

	private void animate() {
		Animation.step();
		_adapter._grab.setPos(Animation.getCurrentX(), Animation.getCurrentY(), Animation.getCurrentZ());
	}

	@Override
	public void draw() {
		if (_isActiveDeque) {
			if (Animation.isDone()) {

				IGrabAnimEvent curEvent = null;

				if (!_events.isEmpty()) {
					curEvent = _events.pollFirst();
				} else {
					_prevEvent = null;
				}

				if (curEvent != null) {
					curEvent.beforeTask();
				}
				if (_prevEvent != null) {
					_prevEvent.afterTask();
				}
				if (curEvent != null) {
					curEvent.doTask();
				}

				_prevEvent = curEvent;
				animate();
			} else {
				animate();
			}
		}
	}

}
