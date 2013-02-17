package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.model.Product;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 16/02/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class NewProductDAO extends NewGenericDAO<Product> {

    public void salvar(Product product) {
        save(product);
    }

    public void alterar(Product product) {
        update(product);
    }

    public void excluir(int id) {
        Product p = findById(id);
        delete(p);
    }

}