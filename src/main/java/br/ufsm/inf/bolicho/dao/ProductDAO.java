package br.ufsm.inf.bolicho.dao;

import br.ufsm.inf.bolicho.PojoMapper;
import br.ufsm.inf.bolicho.beans.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
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
        jsonData = new File("C:\\data.json"); //TODO: Salvar onde?
        products = new ArrayList<Product>();
        initialized = false;
        System.out.println("Entrou no construtor. Initialized: " + initialized + " Object: " + this);
        if(!jsonData.exists()) {
            System.out.println("jsonData.exists = false. Initialized: " + initialized + " Object: " + this);
            try {
                jsonData.createNewFile();
                initialized = true;
                System.out.println("Criou novo arquivo. Initialized: " + initialized + " Object: " + this);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void initialize() {
        System.out.println("Entrou na initialize. Initialized: " + initialized + " Object: " + this);
        try {
            FileReader fileReader = new FileReader(jsonData);
            ProductDAO tmp = (ProductDAO) PojoMapper.fromJson(fileReader,ProductDAO.class);
            products.addAll(tmp.getProducts());
            initialized = true;
            System.out.println("Terminou de inicializar. Initialized: " + initialized + " Object: " + this);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public File getJsonData() {
        return jsonData;
    }

    public void setJsonData(File jsonData) {
        this.jsonData = jsonData;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public int generateId() {
        return -1; //TODO: Implementar generateId()
    }

    public void insert(Product product) throws DAOException {
        System.out.println("Entrou na insert. Initialized: " + initialized + " Object: " + this);
        if(!initialized) {
            System.out.println("Testou initialized e deu false. Initialized: " + initialized + " Object: " + this);
            initialize();
        }
        System.out.println("Passou do teste. Initialized: " + initialized + " Object: " + this);
        product.setId(generateId());
        products.add(product);
        FileWriter fw = null;
        try {
            fw = new FileWriter(jsonData);
            PojoMapper.toJson(this, fw, true);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Product retrieve(Product object) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Product> retrieveAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void update(Product object) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete(Product object) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
