package br.ufsm.inf.bolicho.beans;

import br.ufsm.inf.bolicho.dao.DAOException;
import br.ufsm.inf.bolicho.dao.OrderDAO;

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
    private OrderDAO orderDAO;

    public OrderController() {
        currentOrder = new Order();
        orderDAO = new OrderDAO();
        orderDAO.initialize();
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void addOrder(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido realizado!"));

        try {
            orderDAO.insert(currentOrder);
            currentOrder = new Order();
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void removeOrder(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public void updateOrder(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public List<Product> searchOrder(ActionEvent actionEvent) {
        return null; // TODO: Implementar
    }

    public List<Order> getOrderList() {
        try {
            return orderDAO.retrieveAll();
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public void addProduct(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto adicionado ao carrinho."));
        ArrayList<Product> products = (ArrayList<Product>) currentOrder.getProducts();
        if (products == null) {
            products = new ArrayList<Product>();
        }
        products.add((Product) actionEvent.getComponent().getAttributes().get("product"));
        //TODO: Testar se essa merda não é null
        currentOrder.setProducts(products);
    }

    public void removeSelectedProducts(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produtos removidos do carrinho."));
        currentOrder.removeSelected();
    }

}
