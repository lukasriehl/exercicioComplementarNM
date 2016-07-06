/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_conta;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author Lukas
 */
public class ContaPoupanca extends Conta {

    private BigDecimal rendimento = BigDecimal.valueOf(0.0);

    @Override
    public BigDecimal realizaDeposito(BigDecimal valor) {

        BigDecimal saldoAtualizado;

        Calendar diaAtual = Calendar.getInstance();

        //Efetua o agendamento para o próximo dia útil caso o dia atual seja Sábado ou Domingo
        if ((diaAtual.get(Calendar.DAY_OF_WEEK) == 1) || (diaAtual.get(Calendar.DAY_OF_WEEK) == 7)) {

            saldoAtualizado = this.getSaldo();

            realizaAgendamento(valor);

        } else {
            this.rendimento = valor.multiply(BigDecimal.valueOf(1.02));
            saldoAtualizado = this.getSaldo().add(valor.multiply(BigDecimal.valueOf(1.02)));
        }

        return saldoAtualizado;
    }

    @Override
    public BigDecimal realizaSaque(BigDecimal valor) {
        int comparaSaldoEValor = this.getSaldo().compareTo(valor);

        return comparaSaldoEValor != -1 ? this.getSaldo().subtract(valor) : this.getSaldo();
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

}
