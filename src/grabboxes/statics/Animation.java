package grabboxes.statics;

import processing.core.PApplet;

public class Animation {

    private static float[] _initial = new float[3];
    private static float[] _goal = new float[3];
    private static float[] _current = new float[3];

    private static float _step = 0.0f;

    private static boolean _isNotDone = false;

    public static boolean isDone() {
        return !_isNotDone;
    }

    public static float getCurrentX() {
        return _current[0];
    }

    public static float getCurrentY() {
        return _current[1];
    }

    public static float getCurrentZ() {
        return _current[2];
    }

    public static void setInitial(float x, float y, float z) {
        _current[0] = _initial[0] = x;
        _current[1] = _initial[1] = y;
        _current[2] = _initial[2] = z;

        _step = 0.0f;
    }

    public static void setGoal(float x, float y, float z) {
        _goal[0] = x;
        _goal[1] = y;
        _goal[2] = z;
    }

    public static void restart() {
        _current[0] = _initial[0];
        _current[1] = _initial[1];
        _current[2] = _initial[2];

        _step = 0.0f;
    }

    public static void reverse() {
        _goal[0] = _initial[0];
        _goal[1] = _initial[1];
        _goal[2] = _initial[2];

        _initial[0] = _current[0];
        _initial[1] = _current[1];
        _initial[2] = _current[2];

        _step = 0.0f;
    }

    public static void step() {
        final float end = 1.0f;
        float diff = end - _step;

        if (_isNotDone = (PApplet.abs(diff) > MainConstants.EPS_GRAB)) {
            _current[0] = _initial[0] + (_goal[0] - _initial[0]) * _step;
            _current[1] = _initial[1] + (_goal[1] - _initial[1]) * _step;
            _current[2] = _initial[2] + (_goal[2] - _initial[2]) * _step;
            _step += diff / MainConstants.SPEED_GRAB;
        }
    }
}
