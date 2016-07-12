/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_conta;

import contas_e_operacoes.ContaPoupanca;
import contas_e_operacoes.ContaCorrente;
import agencia_banco.Agencia;
import enumerator.Status;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Lukas
 */
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cpf;
    private String nome;
    private Set<Conta> contas = new LinkedHashSet<>();

    public Cliente() {
    }

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    private boolean verificaExistenciaConta(Agencia agencia, int numero) {
        for (Conta conta : contas) {
            if ((conta.getAgencia().equals(agencia)) && (conta.getNumero() == numero)) {
                return true;
            }
        }
        return false;
    }

    //Adiciona uma conta na lista de contas
    protected void adicionaConta(Agencia agencia, Conta conta) {
        if (verificaExistenciaConta(conta.getAgencia(), conta.getNumero())) {
            System.out.println(" A conta de número " + String.valueOf(conta.getNumero())
                    + " , dígito " + conta.getDigito() + " da agência " + conta.getAgencia().getNome() + " já está associada ao cliente!");
        } else {
            contas.add(conta);
            System.out.println("A conta foi adicionada com sucesso!");
        }
    }

    public Conta getConta(Agencia agencia, int numero) {
        Conta contaRetorno = null;

        for (Conta conta : contas) {
            if ((conta.getAgencia().equals(conta.getAgencia())) && (conta.getNumero() == numero)) {
                contaRetorno = conta;
                break;
            }
        }

        return contaRetorno;
    }

    /**
     * Adiciona uma conta baseado nos parâmetros
     *
     * @param criaContaCorrente
     * @param numero
     * @param digito
     * @param agencia
     */
//    public void criaConta(boolean criaContaCorrente, int numero, String digito, Agencia agencia) {
//        Conta novaConta = criaContaCorrente ? new ContaCorrente(numero, digito, BigDecimal.ZERO, agencia, Status.ATIVO)
//                : new ContaPoupanca(numero, digito, BigDecimal.ZERO, agencia, Status.ATIVO);
//
//        boolean contaExistente = verificaExistenciaConta(numero);
//
//        if (contaExistente) {
//            System.out.println(" A conta de número " + String.valueOf(numero)
//                    + " , dígito " + digito + " da agência " + agencia.getNome() + " já existe!");
//        } else {
//            adicionaConta(novaConta);
//            System.out.println("A nova conta foi criada com sucesso!");
//        }
//    }
    public BigDecimal realizaAcaoDeposito(int numero, Agencia agencia, BigDecimal valorDeposito) {
        Conta conta = getConta(agencia, numero);

        return conta != null ? conta.realizaDeposito(valorDeposito) : null;
    }

    public BigDecimal realizaAcaoSaque(int numero, Agencia agencia, BigDecimal valorSaque) {
        //Conta conta = getContaPorNumeroEAgencia(numero, agencia);        
        Conta conta = getConta(agencia, numero);

        return conta != null ? conta.realizaSaque(valorSaque) : null;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContasList() {
        return Collections.unmodifiableList(new LinkedList<>(contas));
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
