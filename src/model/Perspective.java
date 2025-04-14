package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Perspective implements Subject, Serializable {
    private static final long serialVersionUID = 1L;
    private double zoomFactor;
    private int translateX;
    private int translateY;
    private List<Observer> observers = new ArrayList<>();

    public Perspective(double zoomFactor, int translateX, int translateY) {
        this.zoomFactor = zoomFactor;
        this.translateX = translateX;
        this.translateY = translateY;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        notifyObservers();
    }

    public void setTranslate(int x, int y) {
        this.translateX = x;
        this.translateY = y;
        notifyObservers();
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

    // Méthodes pour le patron Memento : sauvegarder et restaurer l'état
    public PerspectiveState createMemento() {
        return new PerspectiveState(zoomFactor, translateX, translateY);
    }

    public void restoreMemento(PerspectiveState state) {
        this.zoomFactor = state.getZoomFactor();
        this.translateX = state.getTranslateX();
        this.translateY = state.getTranslateY();
        notifyObservers();
    }
}
