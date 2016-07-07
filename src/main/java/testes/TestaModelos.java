/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import agencia_banco.Agencia;
import agencia_banco.Banco;
import cliente_conta.Cliente;

/**
 *
 * @author Lukas
 */
public class TestaModelos {

    public static void main(String[] args) {

        Banco banco = new Banco(1234, "Bradesco");

        System.out.println("Código: " + String.valueOf(banco.getCodigo()));
        System.out.println("Nome: " + banco.getNome());

        Agencia agencia = new Agencia(12345, "123", "Agência do Centro", banco);

        System.out.println("Número: " + String.valueOf(agencia.getNumero()));
        System.out.println("Dígito: " + agencia.getDigito());
        System.out.println("Nome: " + agencia.getNome());
        System.out.println("Banco: " + agencia.getBanco().getNome());
    }

}
