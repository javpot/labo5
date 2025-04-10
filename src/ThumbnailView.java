

// ThumbnailView.java (optionnel selon l'affichage souhaité)
public class ThumbnailView implements Observer {
    private ImageModel imageModel;

    public ThumbnailView(ImageModel imageModel) {
        this.imageModel = imageModel;
        imageModel.attach(this);
    }

    @Override
    public void update() {
        System.out.println("ThumbnailView : l'image a changé => " + imageModel.getFilePath());
        // Ici, dans une UI graphique, on pourrait mettre à jour une vignette dans un JPanel
    }
}
