package controller;

import model.ImageModel;
import model.Perspective;

public class MainController {
    private ImageModel imageModel;
    private Perspective perspective;
    private CommandManager commandManager;

    public MainController(ImageModel imageModel, Perspective perspective) {
        this.imageModel = imageModel;
        this.perspective = perspective;
        this.commandManager = CommandManager.getInstance();
    }

    public void zoom(double newZoom) {
        Command cmd = new ZoomCommand(perspective, newZoom);
        commandManager.executeCommand(cmd);
    }

    public void translate(int x, int y) {
        Command cmd = new TranslateCommand(perspective, x, y);
        commandManager.executeCommand(cmd);
    }

    public void save(String fileName) {
        Command cmd = new SaveCommand(imageModel, perspective, fileName);
        commandManager.executeCommand(cmd);
    }

    public void undo() {
        commandManager.undo();
    }
}
