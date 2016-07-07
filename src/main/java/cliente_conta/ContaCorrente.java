/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_conta;

import agencia_banco.Agencia;
import enumerator.Status;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author Lukas
 */
public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, String digito, BigDecimal saldo, Agencia agencia, Status status) {
        super(numero, digito, saldo, agencia, status);
        this.saldoChequeEspecial = new BigDecimal(5000);
    }

    @Override
    public BigDecimal realizaDeposito(BigDecimal valor) {
        Calendar diaAtual = Calendar.getInstance();

        //Efetua o agendamento para o próximo dia útil caso o dia atual seja Sábado ou Domingo
        if ((diaAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) || (diaAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
            realizaAgendamento(valor);
        } else if (this.saldoChequeEspecial.compareTo(BigDecimal.valueOf(5000)) == -1) {
            BigDecimal difChequeEspecial;
            boolean ultrapassouChequeEspecial;

            this.saldoChequeEspecial = this.saldoChequeEspecial.add(valor);

            difChequeEspecial = BigDecimal.valueOf(5000).subtract(this.saldoChequeEspecial);
            ultrapassouChequeEspecial = difChequeEspecial.compareTo(BigDecimal.ZERO) == -1;

            this.saldoChequeEspecial = ultrapassouChequeEspecial ? BigDecimal.valueOf(5000) : this.saldoChequeEspecial;

            this.saldo = ultrapassouChequeEspecial ? this.saldo.add(difChequeEspecial.abs())
                    : this.saldo;
        } else {
            this.saldo = this.saldo.add(valor);
        }

        System.out.println("Depósito realizado! Limite atual: " + this.saldoChequeEspecial.toString());
        System.out.println("Saldo total(saldo conta-corrente + limite): " + this.retornaSaldoTotal().toString());

        return this.retornaSaldoTotal();
    }

    @Override
    public BigDecimal realizaSaque(BigDecimal valor) {

        BigDecimal saldoAtual = this.retornaSaldoTotal();

        if (saldoAtual.compareTo(valor) == -1) {
            System.out.println("Não foi possível efetuar o saque! Saldo abaixo do valor de saque desejado. Saldo atual: " + saldoAtual);

            return saldoAtual;
        } else {
            BigDecimal difContaCorrente = this.saldo.subtract(valor);
            BigDecimal difChequeEspecial;
            boolean ultrapassouChequeEspecial;

            this.saldoChequeEspecial = difContaCorrente.compareTo(BigDecimal.ZERO) == -1 ? this.saldoChequeEspecial.subtract(difContaCorrente.abs()) : saldoChequeEspecial;
            ultrapassouChequeEspecial = this.saldoChequeEspecial.compareTo(BigDecimal.ZERO) == -1;

            difChequeEspecial = ultrapassouChequeEspecial ? this.saldoChequeEspecial.subtract(valor) : BigDecimal.ZERO;

            this.saldoChequeEspecial = ultrapassouChequeEspecial ? BigDecimal.ZERO : this.saldoChequeEspecial;

            this.saldo = this.saldo.subtract(difChequeEspecial);

            System.out.println("Saque efetuado com sucesso! Saldo atualizado: " + this.retornaSaldoTotal().toString());

            return this.retornaSaldoTotal();
        }
    }

}
