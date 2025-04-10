// ZoomCommand.java
public class ZoomCommand implements Command {
    private Perspective perspective;
    private double newZoom;
    private PerspectiveState previousState;

    public ZoomCommand(Perspective perspective, double newZoom) {
        this.perspective = perspective;
        this.newZoom = newZoom;
    }

    @Override
    public void execute() {
        previousState = perspective.createMemento();
        perspective.setZoomFactor(newZoom);
        System.out.println("ZoomCommand : zoom modifié à " + newZoom);
    }

    @Override
    public void undo() {
        if (previousState != null) {
            perspective.restoreMemento(previousState);
            System.out.println("ZoomCommand annulé.");
        }
    }
}
