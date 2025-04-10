

// PerspectiveView.java (peut être intégré dans une partie de l'interface)
public class PerspectiveView implements Observer {
    private Perspective perspective;

    public PerspectiveView(Perspective perspective) {
        this.perspective = perspective;
        perspective.attach(this);
    }

    @Override
    public void update() {
        System.out.println("PerspectiveView : zoom = " + perspective.getZoomFactor() +
                ", translation = (" + perspective.getTranslateX() + ", " + perspective.getTranslateY() + ")");
        // Dans une interface graphique, cette vue pourrait être associée à des labels affichant les paramètres
    }
}
