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

/**
 *
 * @author Lukas
 */
public class ContaPoupanca extends Conta {

    private BigDecimal rendimento = BigDecimal.ZERO;

    public ContaPoupanca(int numero, String digito, BigDecimal saldo, Agencia agencia, Status status) {
        super(numero, digito, saldo, agencia, status);
    }

    @Override
    public BigDecimal realizaDeposito(BigDecimal valor) {
        Calendar diaAtual = Calendar.getInstance();

        //Efetua o agendamento para o próximo dia útil caso o dia atual seja Sábado ou Domingo
        if ((diaAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) || (diaAtual.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
            realizaAgendamento(valor);
        } else {
            this.rendimento = this.rendimento.add(valor.multiply(BigDecimal.valueOf(0.02)));
            this.saldo = this.saldo.add(valor.multiply(BigDecimal.valueOf(1.02)));
        }
        return this.saldo;
    }

    @Override
    public BigDecimal realizaSaque(BigDecimal valor) {
        int comparaSaldoEValor = this.saldo.compareTo(valor);

        if (comparaSaldoEValor == -1) {
            System.out.println("Não foi possível efetuar o saque! Saldo abaixo do valor de saque desejado. Saldo atual: " + this.retornaSaldoTotal());

            return this.saldo;
        } else {
            this.saldo = this.saldo.subtract(valor);
        }
        return this.retornaSaldoTotal();
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

}
