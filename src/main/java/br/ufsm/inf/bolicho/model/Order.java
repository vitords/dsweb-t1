package br.ufsm.inf.bolicho.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 02/01/13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Order\"")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @ManyToMany
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @ManyToMany
    @JoinTable(name = "order_selected_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "selected_product_id"))
    private List<Product> selectedProducts;
    @ManyToOne
    @JoinColumn(name = "user_order", referencedColumnName = "id")
    private User user;

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
        this.selectedProducts = Arrays.asList(selectedProducts);
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void removeSelected() {
        for (Product selectedProduct : selectedProducts) {
            this.products.remove(selectedProduct);
        }
    }
}
