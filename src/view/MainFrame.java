package view;// Fenêtre principale avec barre d'outils et view.ImagePanel
import controller.Command;
import controller.LoadCommand;
import controller.MainController;
import model.ImageModel;
import model.Perspective;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {
    private ImageModel imageModel;
    private Perspective perspective;
    private MainController controller;
    private ImagePanel imagePanel;

    public MainFrame() {
        super("Application d'affichage d'images");

        imageModel = new ImageModel("src/images/images-2.jpeg");
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
        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> {

            String fileName = "/Users/babyjv_/Documents/IMPORTANT STUFF/Java stuffs/labo6/etatSauvegarde.dat";
            Command loadCmd = new LoadCommand(fileName, imageModel, perspective);
            loadCmd.execute();
        });
        toolBar.add(btnLoad);

        JButton btnOpenImage = new JButton("Ouvrir Image");
        btnOpenImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(MainFrame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                imageModel.setFilePath(selectedFile.getAbsolutePath());
            }
        });
        toolBar.add(btnOpenImage);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
