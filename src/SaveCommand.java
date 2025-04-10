// SaveCommand.java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SaveCommand implements Command {
    private ImageModel imageModel;
    private Perspective perspective;
    private String fileName;

    public SaveCommand(ImageModel imageModel, Perspective perspective, String fileName) {
        this.imageModel = imageModel;
        this.perspective = perspective;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(imageModel);
            out.writeObject(perspective);
            System.out.println("SaveCommand : état sauvegardé dans " + fileName);
        } catch (IOException e) {
            System.err.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        System.out.println("SaveCommand : aucune annulation pour la sauvegarde.");
    }
}
