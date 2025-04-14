package controller;

import model.ImageModel;
import model.Perspective;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class LoadCommand implements Command {
    private String fileName;

    private ImageModel imageModel;
    private Perspective perspective;

    public LoadCommand(String fileName, ImageModel imageModel, Perspective perspective) {
        this.fileName = fileName;
        this.imageModel = imageModel;
        this.perspective = perspective;
    }

    @Override
    public void execute() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {

            ImageModel loadedImageModel = (ImageModel) in.readObject();
            Perspective loadedPerspective = (Perspective) in.readObject();


            imageModel.setFilePath(loadedImageModel.getFilePath());
            perspective.setZoomFactor(loadedPerspective.getZoomFactor());
            perspective.setTranslate(loadedPerspective.getTranslateX(), loadedPerspective.getTranslateY());

            System.out.println("controller.LoadCommand : état chargé depuis " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        // Optionnellement, vous pouvez implémenter une annulation pour le chargement,
        // mais ce n'est généralement pas nécessaire pour une opération de lecture.
        System.out.println("controller.LoadCommand annulé : aucune opération à annuler.");
    }
}
