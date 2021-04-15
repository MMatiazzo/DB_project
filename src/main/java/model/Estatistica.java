/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public class Estatistica {
    private ArrayList<Integer> valores;
    private ArrayList<String> labels;

    public Estatistica() {
        this.valores = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    /**
     * @return the valores
     */
    public ArrayList<Integer> getValores() {
        return valores;
    }

    /**
     * @param valores the valores to set
     */
    public void setValores(ArrayList<Integer> valores) {
        this.valores = valores;
    }
    
    /**
     * @return the labels
     */
    public ArrayList<String> getLabels() {
        return labels;
    }

    /**
     * @param labels the labels to set
     */
    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }
}
