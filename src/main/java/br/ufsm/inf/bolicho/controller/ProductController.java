package br.ufsm.inf.bolicho.controller;

import br.ufsm.inf.bolicho.dao.DAOException;
import br.ufsm.inf.bolicho.dao.NewProductDAO;
import br.ufsm.inf.bolicho.dao.ProductDAO;
import br.ufsm.inf.bolicho.model.Product;

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
    private Product[] selectedProducts;

    public ProductController() {
        currentProduct = new Product();
        lastProduct = null;
        searchResults = new ArrayList<Product>();
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

        new NewProductDAO().salvar(currentProduct);
        lastProduct = currentProduct;
        currentProduct = new Product();

    }

    public void updateProduct(ActionEvent actionEvent) {
        new NewProductDAO().alterar(currentProduct);
    }

    public void searchProduct(ActionEvent actionEvent) throws Exception {
        List<Product> aux = getProductList();
        List<Product> list = new ArrayList<Product>();
        list.clear();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Buscando por " + actionEvent.getComponent().getAttributes().get("name")));
        try {
            for (Product p : aux) {
                if (p.getName().toLowerCase().contains(currentProduct.getName().toLowerCase())) {
                    list.add(p);
                }
            }
        }  catch (Exception e) {
            list =  getProductList();
        }
        searchResults = list;
    }

    public List<Product> getProductList() throws Exception {
        // Causa: javax.resource.ResourceException: IJ000453: Unable to get managed connection for java:/dswebDatasource
        //return new NewProductDAO().findAll();
        return null;
    }



    public void setSelectedProducts(Product[] selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public Product[] getSelectedProducts() {
        return selectedProducts;
    }

    public void removeSelectedProducts(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produtos removidos."));
        for (Product selectedProduct : selectedProducts) {
            new NewProductDAO().excluir(selectedProduct.getId());
        }

    }
}
