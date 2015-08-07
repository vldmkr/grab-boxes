package grabboxes.algo;

import java.util.ArrayList;

import grabboxes.drawable.BoxesStack;

public class BoxWraper extends AlgoWraper {
	private int _idx;

	BoxWraper(final ArrayList<BoxesStack> boxes, int boxIdx) {
		BoxesStack box = boxes.get(boxIdx);
		_idx = boxIdx;
		_posX = box.getPosX();
		_posY = box.getPosZ();
		_weight = box.getBoxWeight(0);
	}

	public int getIdx() {
		return _idx;
	}
}
