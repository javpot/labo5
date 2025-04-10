// MainFrame.java : Fenêtre principale avec barre d'outils et ImagePanel
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ImageModel imageModel;
    private Perspective perspective;
    private MainController controller;
    private ImagePanel imagePanel;

    public MainFrame() {
        super("Application d'affichage d'images");
        // Remplacer "chemin/vers/image.jpg" par le chemin d'une image existante sur votre système
        imageModel = new ImageModel("C:\\Users\\Jaja\\Documents\\frog.jpg");
        perspective = new Perspective(1.0, 0, 0);
        controller = new MainController(imageModel, perspective);
        imagePanel = new ImagePanel(imageModel, perspective);
        initUI();
    }

    private void initUI() {
        JToolBar toolBar = new JToolBar();
        JButton btnZoomIn = new JButton("Zoom In");
        JButton btnZoomOut = new JButton("Zoom Out");
        JButton btnLeft = new JButton("←");
        JButton btnRight = new JButton("→");
        JButton btnUp = new JButton("↑");
        JButton btnDown = new JButton("↓");
        JButton btnUndo = new JButton("Undo");
        JButton btnSave = new JButton("Save");

        // Écouteurs pour les boutons
        btnZoomIn.addActionListener(e -> {
            double currentZoom = perspective.getZoomFactor();
            controller.zoom(currentZoom * 1.2);
        });

        btnZoomOut.addActionListener(e -> {
            double currentZoom = perspective.getZoomFactor();
            controller.zoom(currentZoom * 0.8);
        });

        btnLeft.addActionListener(e -> {
            int currentX = perspective.getTranslateX();
            controller.translate(currentX - 20, perspective.getTranslateY());
        });

        btnRight.addActionListener(e -> {
            int currentX = perspective.getTranslateX();
            controller.translate(currentX + 20, perspective.getTranslateY());
        });

        btnUp.addActionListener(e -> {
            int currentY = perspective.getTranslateY();
            controller.translate(perspective.getTranslateX(), currentY - 20);
        });

        btnDown.addActionListener(e -> {
            int currentY = perspective.getTranslateY();
            controller.translate(perspective.getTranslateX(), currentY + 20);
        });

        btnUndo.addActionListener(e -> controller.undo());

        btnSave.addActionListener(e -> {
            // Dans une application réelle, utiliser un JFileChooser pour sélectionner l'emplacement
            controller.save("etatSauvegarde.dat");
        });

        toolBar.add(btnZoomIn);
        toolBar.add(btnZoomOut);
        toolBar.addSeparator();
        toolBar.add(btnLeft);
        toolBar.add(btnRight);
        toolBar.add(btnUp);
        toolBar.add(btnDown);
        toolBar.addSeparator();
        toolBar.add(btnUndo);
        toolBar.add(btnSave);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
