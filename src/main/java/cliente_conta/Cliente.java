/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_conta;

import agencia_banco.Agencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lukas
 */
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cpf;
    private String nome;
    private List<Conta> contasList;

    public Cliente() {

    }

    public Cliente(String cpf, String nome, List<Conta> contasList) {
        this.cpf = cpf;
        this.nome = nome;
        this.contasList = contasList;
    }

    private Conta getContaPorNumeroEAgencia(int numero, Agencia agencia) {
        Conta contaRetorno = null;

        for (Conta conta : contasList) {
            if ((conta.getAgencia().equals(agencia)) && (conta.getNumero() == numero)) {
                contaRetorno = conta;
                break;
            }
        }

        return contaRetorno;
    }

    void adicionaConta() {

    }

    public BigDecimal realizaAcaoDeposito(int numero, Agencia agencia, BigDecimal valorDeposito) {

        Conta conta = getContaPorNumeroEAgencia(numero, agencia);

        return conta != null ? conta.realizaDeposito(valorDeposito) : null;
    }

    public BigDecimal realizaAcaoSaque(int numero, Agencia agencia, BigDecimal valorSaque) {

        Conta conta = getContaPorNumeroEAgencia(numero, agencia);

        return conta != null ? conta.realizaSaque(valorSaque) : null;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContasList() {
        return contasList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.cpf, other.cpf);
    }

}
