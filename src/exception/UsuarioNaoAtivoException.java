/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author 1113778771
 */
public class UsuarioNaoAtivoException extends RuntimeException{
    public UsuarioNaoAtivoException(){
        super("O USUÁRIO NÃO ESTÁ ATIVO NO MOMENTO. ENTRE EM CONTATO COM O ADMINISTRADOR.");
    }
}
