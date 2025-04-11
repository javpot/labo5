import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class LoadCommand implements Command {
    private String fileName;
    // Références aux instances actuelles de l'application
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
            // Lire dans le même ordre que pour la sauvegarde
            ImageModel loadedImageModel = (ImageModel) in.readObject();
            Perspective loadedPerspective = (Perspective) in.readObject();

            // Mise à jour de l'image (par exemple, le chemin) et de la perspective actuelle
            imageModel.setFilePath(loadedImageModel.getFilePath());
            perspective.setZoomFactor(loadedPerspective.getZoomFactor());
            perspective.setTranslate(loadedPerspective.getTranslateX(), loadedPerspective.getTranslateY());

            System.out.println("LoadCommand : état chargé depuis " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        // Optionnellement, vous pouvez implémenter une annulation pour le chargement,
        // mais ce n'est généralement pas nécessaire pour une opération de lecture.
        System.out.println("LoadCommand annulé : aucune opération à annuler.");
    }
}
