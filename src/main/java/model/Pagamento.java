/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author mathe
 */
public class Pagamento {
    private Date data_pagamento;
    private String num_placa_carro;
    private String cpf_locador;
    private String cpf_locatario;
    private double valor;
    private Date data_entrega;
    private Date data_devolucao;

    /**
     * @return the data_pagamento
     */
    public Date getData_pagamento() {
        return data_pagamento;
    }

    /**
     * @param data_pagamento the data_pagamento to set
     */
    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
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
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the data_entrega
     */
    public Date getData_entrega() {
        return data_entrega;
    }

    /**
     * @param data_entrega the data_entrega to set
     */
    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    /**
     * @return the data_devolucao
     */
    public Date getData_devolucao() {
        return data_devolucao;
    }

    /**
     * @param data_devolucao the data_devolucao to set
     */
    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
    
}
