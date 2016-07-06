/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumerator;

/**
 *
 * @author Lukas
 */
public enum Status {

    ATIVO {
        public char getCodigo() {
            return 'A';
        }
    },
    INATIVO {
        public char getCodigo() {
            return 'I';
        }
    }

}
