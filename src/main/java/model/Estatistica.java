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
    private ArrayList<ArrayList> colunas;

    public Estatistica() {
          this.colunas = new ArrayList<>();
    }

    public ArrayList<ArrayList> getColunas() {
        return colunas;
    }
    
    public void setColunas(ArrayList<ArrayList> colunas) {
        this.colunas = colunas;
    }
}
