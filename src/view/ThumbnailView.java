package view;

import model.ImageModel;
import model.Observer;

public class ThumbnailView implements Observer {
    private ImageModel imageModel;

    public ThumbnailView(ImageModel imageModel) {
        this.imageModel = imageModel;
        imageModel.attach(this);
    }

    @Override
    public void update() {
        System.out.println("view.ThumbnailView : l'image a changé => " + imageModel.getFilePath());

    }
}
