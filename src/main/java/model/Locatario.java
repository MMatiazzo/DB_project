/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mathe
 */
public class Locatario {
    private String cpf_pessoa;
    private String comp_renda;
    private String endereco;
    private String habilitacao;

    /**
     * @return the comp_renda
     */
    public String getComp_renda() {
        return comp_renda;
    }

    /**
     * @param comp_renda the comp_renda to set
     */
    public void setComp_renda(String comp_renda) {
        this.comp_renda = comp_renda;
    }

    /**
     * @return the habilitacao
     */
    public String getHabilitacao() {
        return habilitacao;
    }

    /**
     * @param habilitacao the habilitacao to set
     */
    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }

    /**
     * @return the cpf_pessoa
     */
    public String getCpf_pessoa() {
        return cpf_pessoa;
    }

    /**
     * @param cpf_pessoa the cpf_pessoa to set
     */
    public void setCpf_pessoa(String cpf_pessoa) {
        this.cpf_pessoa = cpf_pessoa;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
