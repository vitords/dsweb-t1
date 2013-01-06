package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.PojoMapper;
import br.ufsm.inf.bolicho.beans.User;

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

public class UserDAO implements GenericDAO<User> {

    private File jsonData;
    private List<User> users;
    private boolean initialized;

    public UserDAO() {
        jsonData = new File("C:\\users.json"); //TODO: Salvar onde?
        users = new ArrayList<User>();
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

    public void initialize() {
        try {
            FileReader fileReader = new FileReader(jsonData);
            UserDAO tmp = (UserDAO) PojoMapper.fromJson(fileReader, UserDAO.class);
            users.clear();
            users.addAll(tmp.getUsers());
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int generateId() {
        return users.size();
    }

    public void insert(User user) throws DAOException {
        if(!initialized) {
            initialize();
        }
        user.setId(generateId());
        users.add(user);
        try {
            FileWriter fw = new FileWriter(jsonData);
            PojoMapper.toJson(this, fw, true);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public User retrieve(User user) throws DAOException {
        if(!initialized) {
            initialize();
        }

        for (User u : users) {
            if (u.getId() == user.getId()) {
                return u;
            }
        }
        return null;
    }

    public List<User> retrieveAll() throws DAOException {
        if(!initialized) {
            initialize();
        }

        return users;
    }

    public void update(User user) throws DAOException {
        if(!initialized) {
            initialize();
        }

        for (User u : users) {
            if (u.getId() == user.getId()) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setEmail(user.getEmail());
                u.setPassword(user.getPassword());
                u.setBillingAddress(user.getBillingAddress());
                u.setDeliveryAddress(user.getDeliveryAddress());
                u.setCpf(user.getCpf());
            }
        }
    }

    public void delete(User user) throws DAOException {
        Iterator iterator = users.iterator();
        while(iterator.hasNext()) {
            User u = (User) iterator.next();
            if (u.getId() == user.getId()) {
                iterator.remove();
            }
        }
    }
}
