package br.ufsm.inf.bolicho.dao;
import br.ufsm.inf.bolicho.model.Order;

/**
 * Created with IntelliJ IDEA.
 * User: hstefan
 * Date: 16/02/13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class NewOrderDAO extends NewGenericDAO<Order> {
    public void salvar(Order order) {
        save(order);
    }

    public void alterar(Order order) {
        update(order);
    }

    public void excluir(int id) {
        Order o = findById(id);
        delete(o);
    }
}
