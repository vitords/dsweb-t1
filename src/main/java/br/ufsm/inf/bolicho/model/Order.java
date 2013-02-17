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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "order_id_gen")
    @SequenceGenerator(name = "order_id_gen", sequenceName = "order_id_seq", allocationSize = 1)
    private int id;
    @OneToOne
    private User user;
    @OneToMany
    @JoinColumn(name = "products_id")
    @JoinTable(name = "\"order_products\"")
    @OrderColumn
    private List<Product> products;
    @OneToMany
    @JoinColumn(name = "selected_id")
    @JoinTable(name = "\"order_selected_product\"")
    @OrderColumn
    private List<Product> selectedProducts;

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
