/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contas_e_operacoes;

import cliente_conta.Conta;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author Lukas
 */
abstract class OperacoesConta extends Conta {

    private HashMap<String, BigDecimal> agendamento = new HashMap<>();

    //Não deve ficar na classe Conta, e  sim numa classe específica de operações que devem ser acessíveis ao gerente
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

        this.agendamento.put(novaDataAgendamento, valor);

        System.out.println("Agendamento realizado no valor de " + valor.toString() + " p/ o dia " + novaDataAgendamento);
    }
}
