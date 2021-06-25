/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.lists;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import ui.administrador.UsuarioBEAN;

/**
 *
 * @author 1113778771
 */
public class OperadoresModel extends AbstractListModel implements ComboBoxModel {

    private UsuarioBEAN usuarioSelecionado;
    private List<UsuarioBEAN> usuarios = new ArrayList<>();
    
    public void addItem(UsuarioBEAN usuario){
        this.usuarios.add(usuario);
    }
    
    @Override
    public UsuarioBEAN getSelectedItem(){
        return usuarioSelecionado;
    }
    
    @Override
    public int getSize(){
        return usuarios.size();
    }
    
    @Override
    public UsuarioBEAN getElementAt(int i){
        return usuarios.get(i);
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        UsuarioBEAN usuarioSelecionado = (UsuarioBEAN) anItem;
        for(UsuarioBEAN usuario : usuarios){
            if(usuarioSelecionado.getCodigo().equals(usuario.getCodigo())){
                this.usuarioSelecionado = usuarioSelecionado;
            }
        }
    }
    
    public void removeAllItems(){
        usuarios.clear();
    }

    
}
