package grabboxes.algo;

public abstract class AlgoWraper {
	protected float _posX;
	protected float _posY;
	protected int _weight;

	public AlgoWraper() {
	}

	public float getPosX() {
		return _posX;
	}

	public float getPosY() {
		return _posY;
	}

	public float getWeight() {
		return _weight;
	}
}
