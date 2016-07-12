/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contas_e_operacoes;

import agencia_banco.Agencia;
import cliente_conta.Conta;
import enumerator.Status;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 *
 * @author Lukas
 */
abstract class OperacoesConta extends Conta {

    private int numero;
    private String digito;
    private Agencia agencia;
    private Status status;
    private BigDecimal saldo;
    private HashMap<String, BigDecimal> agendamento = new HashMap<>();

    protected OperacoesConta(int numero, String digito, Agencia agencia) {
        this.numero = String.valueOf(numero).length() > 7
                ? Integer.parseInt(String.valueOf(numero).substring(0, 7)) : numero;
        this.digito = digito.length() > 2
                ? digito.substring(0, 2) : digito;
        this.agencia = agencia;
        this.saldo = BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal realizaDeposito(BigDecimal valor) {
        Calendar diaAtual = Calendar.getInstance();

        //Efetua o agendamento para o próximo dia útil caso o dia atual seja Sábado ou Domingo
        if ((diaAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) || (diaAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
            realizaAgendamento(valor);
        } else {
            saldo = saldo.add(valor);
        }
        return null;
    }

    @Override
    protected BigDecimal realizaSaque(BigDecimal valor) {
        if (verificaSaldo(valor)) {
            System.out.println("Não é possível realizar o saque, saldo insuficiente!");
        } else {
            saldo = saldo.subtract(valor);
        }
        return saldo;
    }

    protected boolean verificaSaldo(BigDecimal valor) {
        return saldo.compareTo(valor) < 0;
    }

    protected void realizaAgendamento(BigDecimal valor) {
        Calendar diaAtual = Calendar.getInstance();

        String novaDataAgendamento;
        int qtdeDias = diaAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 1 : 2;

        diaAtual.add(Calendar.DAY_OF_MONTH, qtdeDias);

        novaDataAgendamento = String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH)).length() < 2
                ? "0".concat(String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH)))
                : String.valueOf(diaAtual.get(Calendar.DAY_OF_MONTH));
        novaDataAgendamento = novaDataAgendamento.concat("/").concat(String.valueOf(diaAtual.get(Calendar.MONTH)))
                .concat(String.valueOf(diaAtual.get(Calendar.YEAR)));

        if (this.agendamento.containsKey(novaDataAgendamento)) {
            this.agendamento.put(novaDataAgendamento, agendamento.get(novaDataAgendamento).add(valor));
        } else {
            this.agendamento.put(novaDataAgendamento, valor);
        }

        System.out.println("Agendamento realizado no valor de " + valor.toString() + " p/ o dia " + novaDataAgendamento);
    }

    //TODO
    protected void conferirDepositoAgendados() {

    }

    protected void inativarConta() {
        status = Status.ATIVO;
    }

    protected void ativarConta() {
        status = Status.INATIVO;
    }

    @Override
    public OperacoesConta clone() {
        OperacoesConta operacoesConta = (OperacoesConta) super.clone();
        operacoesConta.agencia = this.agencia.clone();
        operacoesConta.agendamento = this.agendamento == null ? null
                : new LinkedHashMap<>(this.agendamento);
        return operacoesConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperacoesConta)) {
            return false;
        }

        OperacoesConta that = (OperacoesConta) o;

        if (agencia != null ? !agencia.equals(that.agencia) : that.agencia != null) {
            return false;
        }
        if (!digito.equals(digito)) {
            return false;
        }
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.numero;
        hash = 83 * hash + Objects.hashCode(this.digito);
        hash = 83 * hash + Objects.hashCode(this.agencia);
        return hash;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public String getDigito() {
        return digito;
    }

    @Override
    public Agencia getAgencia() {
        return agencia;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public BigDecimal getSaldo() {
        return saldo;
    }
}
