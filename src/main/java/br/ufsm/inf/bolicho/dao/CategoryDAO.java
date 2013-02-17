package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.model.Category;
/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 04/01/13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDAO extends GenericDAO<Category> {

    public void salvar(Category category) {
        save(category);
    }

    public void alterar(Category category) {
        update(category);
    }

    public void excluir(int id) {
        Category o = findById(id);
        delete(o);
    }
}
