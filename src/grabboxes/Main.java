package grabboxes;

import grabboxes.adapters.DrawAdapter;
import grabboxes.managers.BackgroundManager;
import grabboxes.managers.PositionManager;
import grabboxes.statics.ResourceLoader;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class Main extends PApplet {
	private static final long serialVersionUID = 9192803387216155828L;

	private BackgroundManager _backgroundManager;
	private PositionManager _positionManager;
	private DrawAdapter _drawAdapter;

	private int operBox = 0;
	private int operBoxWeight = 0;

	public static void main(String args[]) {
		PApplet.main(new String[] { "grabboxes.Main" });
	}

	@Override
	public void mouseWheel(MouseEvent event) {
		super.mouseWheel(event);
		if (_positionManager.isSetupMode()) {
			if (operBoxWeight >= 0 && operBoxWeight <= 99) {
				operBoxWeight -= event.getCount();
			} else {
				if (operBoxWeight < 0) {
					operBoxWeight = 0;
				}
				if (operBoxWeight > 99) {
					operBoxWeight = 99;
				}
			}
			_drawAdapter.changeBoxLabel(operBox, 0, operBoxWeight);
		} else {
			final float k = 0.2f;
			_positionManager.changeScale(-event.getCount() * k);
		}
	}

	@Override
	public void mouseClicked() {
        if (mouseButton == LEFT && _positionManager.isSetupMode()) {
            float x = (mouseX - getParent().getWidth() * 0.5f) / _positionManager.getScale();
            float z = (mouseY - getParent().getHeight() * 0.5f) / _positionManager.getScale();

            float size = _drawAdapter.getPlaneSize() - ResourceLoader.getBox().getHeight() * 0.5f;

            if (PApplet.abs(x) < size && PApplet.abs(z) < size) {
                operBoxWeight = 0;
                int b = _drawAdapter.addBox(x, z, operBoxWeight);
                _drawAdapter.changeBoxLabel(b, 0, operBoxWeight);
                // _drawAdapter._eventsAdapter.addGoToBox(b);
                // _drawAdapter._eventsAdapter.addPickUpBox(b);
                // _drawAdapter._eventsAdapter.addGoToBox(0);
                // _drawAdapter._eventsAdapter.addPutDownBox(0);
                operBox = b;
            }
        } else if (mouseButton == RIGHT) {
            if (_positionManager.isSetupMode() && !_drawAdapter.isEnoughBoxes()) {
                _backgroundManager.deny();
                return;
            }
            _positionManager.setSetupMode(!_positionManager.isSetupMode());
            if (_positionManager.isSetupMode()) {
                _drawAdapter.cleanBoxes();
                _drawAdapter.pauseWork();
                _drawAdapter.setChangingLabelShown(true);
            } else {
                _drawAdapter.setChangingLabelShown(false);
                _drawAdapter.makePath(_drawAdapter._algo.BruteForce());
                _drawAdapter.doWork();
            }
        } else {
            _backgroundManager.deny();
        }
    }

	@Override
	public void mouseDragged() {
		_positionManager.setPositionByMouse();
	}

	@Override
	public void setup() {
		size(800, 600, P3D);
		textSize(150);
		ResourceLoader.Parent = this;

		_backgroundManager = new BackgroundManager(this);
		_positionManager = new PositionManager(this);
		_positionManager.setOffsetY(0.6f);
		_positionManager.setScale(3.0f);

		_drawAdapter = new DrawAdapter(this);
		_drawAdapter.setPlaneSize(100.0f);
		_drawAdapter.setGrabPos(100.0f, -100.0f);
	}

	@Override
	public void draw() {
		_backgroundManager.draw();
		pushMatrix();
		_positionManager.draw();
		_drawAdapter.draw();
		popMatrix();
	}
}
