package controller;

import model.Perspective;
import model.PerspectiveState;

public class TranslateCommand implements Command {
    private Perspective perspective;
    private int newX;
    private int newY;
    private PerspectiveState previousState;

    public TranslateCommand(Perspective perspective, int newX, int newY) {
        this.perspective = perspective;
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public void execute() {
        previousState = perspective.createMemento();
        perspective.setTranslate(newX, newY);
        System.out.println("controller.TranslateCommand : translation modifiée à (" + newX + ", " + newY + ")");
    }

    @Override
    public void undo() {
        if (previousState != null) {
            perspective.restoreMemento(previousState);
            System.out.println("controller.TranslateCommand annulé.");
        }
    }
}
