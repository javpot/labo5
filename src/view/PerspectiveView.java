package view;

import model.Observer;
import model.Perspective;

public class PerspectiveView implements Observer {
    private Perspective perspective;

    public PerspectiveView(Perspective perspective) {
        this.perspective = perspective;
        perspective.attach(this);
    }

    @Override
    public void update() {
        System.out.println("view.PerspectiveView : zoom = " + perspective.getZoomFactor() +
                ", translation = (" + perspective.getTranslateX() + ", " + perspective.getTranslateY() + ")");

    }
}
