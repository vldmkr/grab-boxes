package grabboxes.managers;
import grabboxes.drawable.BaseDrawable;
import processing.core.PApplet;

public class BackgroundManager extends BaseDrawable {
	private float _stepDeny, _stepAccept;

	private float _defGray;
	private float _changeSpeed;

	public BackgroundManager(PApplet parrent) {
		super(parrent);

		setDefGray(210.0f);
		setChangeSpeed(2.0f);

		_stepDeny = _stepAccept = getDefGray();
	}

	@Override
	public void draw() {
		getParent().background(
				getParent().color(_stepAccept, _stepDeny,
						PApplet.min(_stepAccept, _stepDeny)));
		if (_stepDeny < getDefGray()) {
			_stepDeny += getChangeSpeed();
		}
		if (_stepAccept < getDefGray()) {
			_stepAccept += getChangeSpeed();
		}
	}

	public void accept() {
		_stepAccept = getDefGray() / 1.5f;
	}

	public void deny() {
		_stepDeny = getDefGray() / 1.5f;
	}

	public float getDefGray() {
		return _defGray;
	}

	public void setDefGray(float defGray) {
		_defGray = defGray;
	}

	public float getChangeSpeed() {
		return _changeSpeed;
	}

	public void setChangeSpeed(float changingSpeed) {
		_changeSpeed = changingSpeed;
	}
}
