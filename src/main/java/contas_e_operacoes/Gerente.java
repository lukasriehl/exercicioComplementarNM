/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contas_e_operacoes;

import funcionario.Funcionario;
import java.math.BigDecimal;

/**
 *
 * @author Lukas
 */
public class Gerente extends Funcionario {

    public Gerente(String cpf, String nome) {
        super(cpf, nome);
    }

    public void alteraLimiteChequeEspecial(ContaCorrente contaCorrente, BigDecimal novoValor) {

    }

}
