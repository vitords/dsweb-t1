package br.ufsm.inf.bolicho.beans;

import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 02/01/13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public class Order implements Serializable {

    private int id;
    private User user;
    private List<Product> products;
    private Product[] selectedProducts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setSelectedProducts(Product[] selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public Product[] getSelectedProducts() {
        return selectedProducts;
    }

    public void removeSelected() {
        for (int i = 0; i < selectedProducts.length; i++) {
            this.products.remove(selectedProducts[i]);
        }
    }
}
