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

    public Estatistica() {
        this.valores = new ArrayList<>();
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
    
    public void addValor(Integer valor) {
        this.valores.add(valor);
    }
}
