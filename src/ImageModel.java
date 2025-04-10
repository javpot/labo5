// ImageModel.java
import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageModel implements Subject, Serializable {
    private static final long serialVersionUID = 1L;
    private String filePath;
    private List<Observer> observers = new ArrayList<>();

    public ImageModel(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        notifyObservers();
    }

    // Méthode pour charger l'image à partir du chemin
    public BufferedImage getBufferedImage() {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
            return null;
        }
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
