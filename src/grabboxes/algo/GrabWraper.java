package grabboxes.algo;

import grabboxes.drawable.Grab;

public class GrabWraper extends AlgoWraper {
    GrabWraper(final Grab grab) {
        _posX = grab.getPosX();
        _posY = grab.getPosZ();
        _weight = 1;
    }
}
