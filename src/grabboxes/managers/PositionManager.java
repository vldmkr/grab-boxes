package grabboxes.managers;

import grabboxes.drawable.BaseDrawable;
import grabboxes.statics.MainConstants;
import processing.core.PApplet;

public class PositionManager extends BaseDrawable {

    private float _curRotateX = 0;
    private float _curRotateY = -PApplet.QUARTER_PI;
    private float _goalRotateX = PApplet.QUARTER_PI / 2f;
    private float _goalRotateY = 0;

    private float _oldGoalRotateX = _goalRotateX;
    private float _oldGoalRotateY = _goalRotateY;

    private float _offsetY = 0;

    private float _goalY = 0;
    private float _curY = 0;

    private float _goalScale = 0;
    private float _curScale = 0;

    private boolean _setupMode = false;

    public PositionManager(PApplet parrent) {
        super(parrent);
    }

    @Override
    public void draw() {
        float diff = _curY - _goalY;
        if (PApplet.abs(diff) > MainConstants.EPS_POS) {
            _curY -= diff / MainConstants.SPEED_POS;
        }

        diff = _curRotateX - _goalRotateX;
        if (PApplet.abs(diff) > MainConstants.EPS_POS) {
            _curRotateX -= diff / MainConstants.SPEED_POS;
        }

        diff = _curRotateY - _goalRotateY;
        if (PApplet.abs(diff) > MainConstants.EPS_POS) {
            _curRotateY -= diff / MainConstants.SPEED_POS;
        }

        diff = _curScale - _goalScale;
        if (PApplet.abs(diff) > MainConstants.EPS_POS) {
            _curScale -= diff / MainConstants.SPEED_POS;
        }

        getParent().translate(getParent().getWidth() * 0.5f, _curY, 0.0f);
        getParent().rotateX(_curRotateY);
        getParent().rotateY(_curRotateX);

        getParent().scale(_curScale);
    }

    public void setPositionByMouse() {
        if (!_setupMode) {
            _goalRotateX += (getParent().mouseX - getParent().pmouseX) / (float) (getParent().width) * PApplet.PI;
            _goalRotateY += (getParent().pmouseY - getParent().mouseY) / (float) (getParent().height) * PApplet.PI;

            checkPosition();
        }
    }

    private void checkPosition() {
        if (_goalRotateY > PApplet.QUARTER_PI * 0.5f)
            _goalRotateY = PApplet.QUARTER_PI * 0.5f;
        else if (_goalRotateY < -PApplet.QUARTER_PI)
            _goalRotateY = -PApplet.QUARTER_PI;

        if (_goalRotateX > PApplet.QUARTER_PI)
            _goalRotateX = PApplet.QUARTER_PI;
        else if (_goalRotateX < -PApplet.QUARTER_PI)
            _goalRotateX = -PApplet.QUARTER_PI;
    }

    public void setOffsetY(float k) {
        _offsetY = k >= 0.0f && k <= 1.0f ? k : 0.0f;
        _curY = getParent().getHeight() * _offsetY;
        _goalY = getParent().getHeight() * _offsetY;
    }

    public float getOffsetY() {
        return _offsetY;
    }

    public void setScale(float scale) {
        if (scale < 1.0f) {
            _goalScale = 1.0f;
        } else if (scale > 15.0f) {
            _goalScale = 15.0f;
        } else {
            _goalScale = scale;
        }
    }

    public void changeScale(float k) {
        setScale(_goalScale + k);
    }

    public float getScale() {
        return _goalScale;
    }

    public boolean isSetupMode() {
        return _setupMode;
    }

    public void setSetupMode(boolean flag) {
        _setupMode = flag;
        if (_setupMode) {
            _oldGoalRotateX = _goalRotateX;
            _oldGoalRotateY = _goalRotateY;
            _goalRotateX = 0;
            _goalRotateY = -PApplet.HALF_PI;

            _goalY = getParent().getHeight() * 0.5f;

        } else {
            _goalRotateX = _oldGoalRotateX;
            _goalRotateY = _oldGoalRotateY;

            _goalY = getParent().getHeight() * _offsetY;
        }
    }
}
