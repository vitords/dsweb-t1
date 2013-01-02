package br.ufsm.inf.bolicho.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 28/12/12
 * Time: 02:47
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDAO<T> extends Serializable {

    public void insert(T object) throws DAOException;
    public T retrieve(T object) throws DAOException;
    public List<?> retrieveAll() throws DAOException;
    public void update(T object) throws DAOException;
    public void delete(T object) throws DAOException;
}
