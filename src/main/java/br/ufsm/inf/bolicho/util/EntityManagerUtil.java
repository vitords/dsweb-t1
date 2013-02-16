package br.ufsm.inf.bolicho.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 16/02/13
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */

public class EntityManagerUtil {
    private static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("dsweb");
        }
        return emf.createEntityManager();
    }
}