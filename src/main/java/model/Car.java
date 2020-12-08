/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Guilherme
 */
public class Car {
    private String placa;
    private boolean abss;
    private String modelo;
    private String tipo;
    private boolean ar_condicionado;
    private boolean airbags;
    private int num_lugares;
    private String descricao;
    private boolean disponibilidade;
    private String cpf_locador;
    private String avatar;
    private double preco;

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the abss
     */
    public boolean isAbss() {
        return abss;
    }

    /**
     * @param abss the abss to set
     */
    public void setAbss(boolean abss) {
        this.abss = abss;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the ar_condicionado
     */
    public boolean isAr_condicionado() {
        return ar_condicionado;
    }

    /**
     * @param ar_condicionado the ar_condicionado to set
     */
    public void setAr_condicionado(boolean ar_condicionado) {
        this.ar_condicionado = ar_condicionado;
    }

    /**
     * @return the airbags
     */
    public boolean isAirbags() {
        return airbags;
    }

    /**
     * @param airbags the airbags to set
     */
    public void setAirbags(boolean airbags) {
        this.airbags = airbags;
    }

    /**
     * @return the num_lugares
     */
    public int getNum_lugares() {
        return num_lugares;
    }

    /**
     * @param num_lugares the num_lugares to set
     */
    public void setNum_lugares(int num_lugares) {
        this.num_lugares = num_lugares;
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
     * @return the disponibilidade
     */
    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    /**
     * @param disponibilidade the disponibilidade to set
     */
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

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
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
}
