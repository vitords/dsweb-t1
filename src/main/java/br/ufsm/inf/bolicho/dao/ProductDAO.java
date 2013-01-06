package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.PojoMapper;
import br.ufsm.inf.bolicho.beans.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 28/12/12
 * Time: 02:37
 * To change this template use File | Settings | File Templates.
 */

public class ProductDAO implements GenericDAO<Product> {

    private File jsonData;
    private List<Product> products;
    private boolean initialized;

    public ProductDAO() {
        jsonData = new File("C:\\json\\products.json"); //TODO: Salvar onde?
        products = new ArrayList<Product>();
        initialized = false;
        //System.out.println("Entrou no construtor. Initialized: " + initialized + " Object: " + this);
        if(!jsonData.exists()) {
            //System.out.println("jsonData.exists = false. Initialized: " + initialized + " Object: " + this);
            try {
                jsonData.createNewFile();
                initialized = true;
                //System.out.println("Criou novo arquivo. Initialized: " + initialized + " Object: " + this);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public void initialize() {
        //System.out.println("Entrou na initialize. Initialized: " + initialized + " Object: " + this);
        try {
            FileReader fileReader = new FileReader(jsonData);
            ProductDAO tmp = (ProductDAO) PojoMapper.fromJson(fileReader, ProductDAO.class);
            products.clear();
            products.addAll(tmp.getProducts());
            initialized = true;
            //System.out.println("Terminou de inicializar. Initialized: " + initialized + " Object: " + this);
        } catch (Exception e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            //System.out.println("Erro no Initialize: " + e.getMessage());
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int generateId() {
        return products.size();
    }

    public void insert(Product product) throws DAOException {
        //System.out.println("Entrou na insert. Initialized: " + initialized + " Object: " + this);
        if(!initialized) {
            //System.out.println("Testou initialized e deu false. Initialized: " + initialized + " Object: " + this);
            initialize();
        }
        //System.out.println("Passou do teste. Initialized: " + initialized + " Object: " + this);
        product.setId(generateId());
        products.add(product);
        try {
            FileWriter fw = new FileWriter(jsonData);
            PojoMapper.toJson(this, fw, true);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Product retrieve(Product product) {
        if(!initialized) {
            initialize();
        }

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                return p;
            }
        }
        return null;
    }

    public List<Product> retrieveAll() {
        if(!initialized) {
            initialize();
        }

        return products;
    }

    public void update(Product product) {
        if(!initialized) {
            initialize();
        }

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p.setName(product.getName());
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                p.setWeight(product.getWeight());
                p.setQuantityInStock(product.getQuantityInStock());
            }
        }
    }

    public void delete(Product product) {
        Iterator iterator = products.iterator();
        while(iterator.hasNext()) {
            Product p = (Product) iterator.next();
            if (p.getId() == product.getId()) {
                iterator.remove();
            }
        }
    }
}
