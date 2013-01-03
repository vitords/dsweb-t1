package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.PojoMapper;
import br.ufsm.inf.bolicho.beans.Order;

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
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class OrderDAO implements GenericDAO<Order> {

    private File jsonData;
    private List<Order> orders;
    private boolean initialized;

    public OrderDAO() {
        jsonData = new File("C:\\orders.json"); //TODO: Salvar onde?
        orders = new ArrayList<Order>();
        initialized = false;

        if(!jsonData.exists()) {
            try {
                jsonData.createNewFile();
                initialized = true;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void initialize() {
        try {
            FileReader fileReader = new FileReader(jsonData);
            OrderDAO tmp = (OrderDAO) PojoMapper.fromJson(fileReader, OrderDAO.class);
            orders.addAll(tmp.getOrders());
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int generateId() {
        return -1; //TODO: Implementar generateId()
    }

    public void insert(Order order) throws DAOException {
        if(!initialized) {
            initialize();
        }
        order.setId(generateId());
        orders.add(order);
        try {
            FileWriter fw = new FileWriter(jsonData);
            PojoMapper.toJson(this, fw, true);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Order retrieve(Order order) throws DAOException {
        if(!initialized) {
            initialize();
        }

        for (Order o : orders) {
            if (o.getId() == order.getId()) {
                return o;
            }
        }
        return null;
    }

    public List<Order> retrieveAll() throws DAOException {
        if(!initialized) {
            initialize();
        }

        return orders;
    }

    public void update(Order order) throws DAOException {
        if(!initialized) {
            initialize();
        }

        for (Order o : orders) {
            if (o.getId() == order.getId()) {
                o.setUser(order.getUser()); // Inútil? Order nunca vai mudar de usuário...
                o.setProducts(order.getProducts());
            }
        }
    }

    public void delete(Order order) throws DAOException {
        Iterator iterator = orders.iterator();
        while(iterator.hasNext()) {
            Order o = (Order) iterator.next();
            if (o.getId() == order.getId()) {
                iterator.remove();
            }
        }
    }
}
