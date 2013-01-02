package br.ufsm.inf.bolicho.beans;

import br.ufsm.inf.bolicho.dao.DAOException;
import br.ufsm.inf.bolicho.dao.ProductDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 28/12/12
 * Time: 04:17
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "productController")
@ViewScoped
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

    public List<Product> getProductList() {
        return productDAO.retrieveAll();
    }

    public void save(ActionEvent actionEvent) {
        // Pra usar os atributos enviados pelo commandButton: actionEvent.getComponent().getAttributes().get("nomedoatributo");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto " + actionEvent.getComponent().getAttributes().get("name") + " cadastrado!"));
        try {
            productDAO.insert(currentProduct);
            lastProduct = currentProduct;
            currentProduct = new Product();
        } catch (DAOException e) {

        }
    }
}
