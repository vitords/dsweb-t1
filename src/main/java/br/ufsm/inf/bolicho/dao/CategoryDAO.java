package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.PojoMapper;
import br.ufsm.inf.bolicho.beans.Category;

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
 * Date: 04/01/13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDAO implements GenericDAO<Category> {

    private File jsonData;
    private List<Category> categories;
    private boolean initialized;

    public CategoryDAO() {
        jsonData = new File("C:\\categories.json"); //TODO: Salvar onde?
        categories = new ArrayList<Category>();
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
            CategoryDAO tmp = (CategoryDAO) PojoMapper.fromJson(fileReader, CategoryDAO.class);
            categories.clear();
            categories.addAll(tmp.getCategories());
            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int generateId() {
        return categories.size();
    }

    public void insert(Category category) throws DAOException {
        if(!initialized) {
            initialize();
        }
        category.setId(generateId());
        categories.add(category);
        try {
            FileWriter fw = new FileWriter(jsonData);
            PojoMapper.toJson(this, fw, true);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Category retrieve(Category category) throws DAOException {
        if(!initialized) {
            initialize();
        }

        for (Category c : categories) {
            if (c.getId() == category.getId()) {
                return c;
            }
        }
        return null;
    }

    public List<Category> retrieveAll() throws DAOException {
        if(!initialized) {
            initialize();
        }

        return categories;
    }

    public void update(Category category) throws DAOException {
        if(!initialized) {
            initialize();
        }

        for (Category c : categories) {
            if (c.getId() == category.getId()) {
                c.setName(category.getName());
                c.setDescription(category.getDescription());
                c.setProductList(category.getProductList());
            }
        }
    }

    public void delete(Category category) throws DAOException {
        Iterator iterator = categories.iterator();
        while(iterator.hasNext()) {
            Category c = (Category) iterator.next();
            if (c.getId() == category.getId()) {
                iterator.remove();
            }
        }
    }
}
