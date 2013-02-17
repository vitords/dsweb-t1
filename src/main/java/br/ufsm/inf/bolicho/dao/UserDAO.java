package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 02/01/13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */

public class UserDAO extends GenericDAO<User> {

    public void salvar(User user) {
        save(user);
    }

    public void alterar(User user) {
        update(user);
    }

    public void excluir(int id) {
        User u = findById(id);
        delete(u);
    }

    public User findByEmail(String email) {
        Session session = (Session) getEntityManager().getDelegate();
        return (User) session.createCriteria(User.class).add(Restrictions.eq("email", email).ignoreCase()).uniqueResult();
    }

}