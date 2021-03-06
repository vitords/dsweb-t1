package br.ufsm.inf.bolicho.controller;

import br.ufsm.inf.bolicho.dao.OrderDAO;
import br.ufsm.inf.bolicho.model.Order;
import br.ufsm.inf.bolicho.model.Product;
import br.ufsm.inf.bolicho.model.User;

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
 * Date: 02/01/13
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "orderController")
@SessionScoped
public class OrderController implements Serializable {

    private Order currentOrder;

    public OrderController() {
        currentOrder = new Order();
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void addOrder(ActionEvent actionEvent) {
        User user = (User) actionEvent.getComponent().getAttributes().get("user");
        currentOrder.setUser(user);
        new OrderDAO().salvar(currentOrder);
        currentOrder = new Order();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido realizado."));
    }

    public void removeOrder(ActionEvent actionEvent) {
        // TODO: É necessário implementar?
    }

    public void updateOrder(ActionEvent actionEvent) {
        // TODO: É necessário implementar?
    }

    public List<Product> searchOrder(ActionEvent actionEvent) {
        return null; // TODO: Implementar
    }

    public List<Order> getOrderList() throws Exception {
        return new OrderDAO().findAll();
    }

    public void addProduct(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto adicionado ao carrinho."));
        ArrayList<Product> products = (ArrayList<Product>) currentOrder.getProducts();
        if (products == null) {
            products = new ArrayList<Product>();
        }
        Product product = (Product) actionEvent.getComponent().getAttributes().get("product");
        if (product != null) {
            Product inCart = productAlreadyInCart(product, products);
            if (inCart != null) {
                inCart.setQuantityOrdered(inCart.getQuantityOrdered() + 1);
            } else {
                product.setQuantityOrdered(1);
                products.add(product);
            }
        } else {
            //we should probably throw an exception, like "unexpected behaviour"
        }
        currentOrder.setProducts(products);
    }

    public void removeSelectedProducts(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produtos removidos do carrinho."));
        currentOrder.removeSelected();
    }

    public void saveCartChanges(ActionEvent actionEvent) {
        Order o = getCurrentOrder();
        if (o != null) {
            List<Product> products = o.getProducts();
            if (products != null) {
                List<Product> updated = new ArrayList<Product>(products);
                for (Product p : products) {
                    if (p.getQuantityOrdered() == 0) {
                        updated.remove(p);
                    }
                }
                o.setProducts(updated);
            }
        }
    }

    private Product productAlreadyInCart(Product product, ArrayList<Product> products) {
        for (Product p : products) {
            if (p.getId() == product.getId())
                return p;
        }
        return null;
    }
}
