package model;//(Memento)
import java.io.Serializable;

public class PerspectiveState implements Serializable {
    private static final long serialVersionUID = 1L;
    private double zoomFactor;
    private int translateX;
    private int translateY;

    public PerspectiveState(double zoomFactor, int translateX, int translateY) {
        this.zoomFactor = zoomFactor;
        this.translateX = translateX;
        this.translateY = translateY;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }
}
