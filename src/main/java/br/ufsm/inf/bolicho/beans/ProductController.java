package br.ufsm.inf.bolicho.beans;

import br.ufsm.inf.bolicho.beans.Product;
import br.ufsm.inf.bolicho.dao.DAOException;
import br.ufsm.inf.bolicho.dao.ProductDAO;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 28/12/12
 * Time: 04:17
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class ProductController implements Serializable {

    private Product currentProduct;
    private Product lastProduct;
    private ProductDAO productDAO;

    public ProductController() {
        currentProduct = new Product();
        lastProduct = null;
        productDAO = new ProductDAO();
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

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void save(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto " + currentProduct.getName() + " cadastrado!"));
        try {
            productDAO.insert(currentProduct);
            lastProduct = currentProduct;
            currentProduct = new Product();
        } catch (DAOException e) {

        }
    }
}
