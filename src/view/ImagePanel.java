package view;// view.ImagePanel.java : Composant Swing affichant l'image avec les transformations
import model.ImageModel;
import model.Observer;
import model.Perspective;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel implements Observer {
    private ImageModel imageModel;
    private Perspective perspective;

    public ImagePanel(ImageModel imageModel, Perspective perspective) {
        this.imageModel = imageModel;
        this.perspective = perspective;
        // S'abonner aux changements des modèles
        imageModel.attach(this);
        perspective.attach(this);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = imageModel.getBufferedImage();
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            double zoom = perspective.getZoomFactor();
            int tx = perspective.getTranslateX();
            int ty = perspective.getTranslateY();
            g2d.translate(tx, ty);
            g2d.scale(zoom, zoom);
            g2d.drawImage(image, 0, 0, this);
        } else {
            g.setColor(Color.RED);
            g.drawString("Erreur de chargement de l'image", 20, 20);
        }
    }
}
