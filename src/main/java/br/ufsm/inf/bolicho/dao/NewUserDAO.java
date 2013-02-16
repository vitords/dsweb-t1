package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.PojoMapper;
import br.ufsm.inf.bolicho.beans.User;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 02/01/13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */

public class NewUserDAO extends NewGenericDAO<User> {

    public void salvar(User user) {
        save(user);
    }

    public void alterar(User user) {
        update(user);
    }

    public void excluir(long id) {
        User u = findById(id);
        delete(u);
    }

}