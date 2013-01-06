package br.ufsm.inf.bolicho.beans;

import br.ufsm.inf.bolicho.dao.DAOException;
import br.ufsm.inf.bolicho.dao.ProductDAO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 28/12/12
 * Time: 04:17
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "productController")
@SessionScoped
public class ProductController implements Serializable {

    private Product currentProduct;
    private Product lastProduct;
    private List<Product> searchResults;
    private ProductDAO productDAO;

    public ProductController() {
        currentProduct = new Product();
        lastProduct = null;
        searchResults = new ArrayList<Product>();
        productDAO = new ProductDAO();
        productDAO.initialize();
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product product) {
        this.currentProduct = product;
    }

    public Product getLastProduct() {
        return lastProduct;
    }

    public void setLastProduct(Product product) {
        this.lastProduct = product;
    }

    public List<Product> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Product> searchResults) {
        this.searchResults = searchResults;
    }

    public void addProduct(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "Produto "
                        + actionEvent.getComponent().getAttributes().get("name")
                        + " cadastrado!")
        );

        try {
            productDAO.insert(currentProduct);
            lastProduct = currentProduct;
            currentProduct = new Product();
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void removeProduct(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public void updateProduct(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public void searchProduct(ActionEvent actionEvent) {
        List<Product> aux = getProductList();
        List<Product> list = new ArrayList<Product>();
        list.clear();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Buscando por " + actionEvent.getComponent().getAttributes().get("name")));
        try {
            for(int i = 0 ; i < aux.size() ; i++)
            {
                if(aux.get(i).getName().startsWith(currentProduct.getName()))
                {
                    list.add(aux.get(i));
                }
            }
        }  catch (Exception e) {
            list =  getProductList();
        }
        searchResults = list;
    }

    public List<Product> getProductList() {
        return productDAO.retrieveAll();
    }

}
