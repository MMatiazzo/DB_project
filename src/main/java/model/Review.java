/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Guilherme
 */
public class Review {
    private String num_placa_carro;
    private String cpf_locador;
    private String cpf_locatario;
    private String descricao;
    private int nota;
    private Date data_review;



    /**
     * @return the cpf_locador
     */
    public String getCpf_locador() {
        return cpf_locador;
    }

    /**
     * @param cpf_locador the cpf_locador to set
     */
    public void setCpf_locador(String cpf_locador) {
        this.cpf_locador = cpf_locador;
    }

    /**
     * @return the cpf_locatario
     */
    public String getCpf_locatario() {
        return cpf_locatario;
    }

    /**
     * @param cpf_locatario the cpf_locatario to set
     */
    public void setCpf_locatario(String cpf_locatario) {
        this.cpf_locatario = cpf_locatario;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the nota
     */
    public int getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

    /**
     * @return the num_placa_carro
     */
    public String getNum_placa_carro() {
        return num_placa_carro;
    }

    /**
     * @param num_placa_carro the num_placa_carro to set
     */
    public void setNum_placa_carro(String num_placa_carro) {
        this.num_placa_carro = num_placa_carro;
    }

    /**
     * @return the data_review
     */
    public Date getData_review() {
        return data_review;
    }

    /**
     * @param data_review the data_review to set
     */
    public void setData_review(Date data_review) {
        this.data_review = data_review;
    }
    
    
}
