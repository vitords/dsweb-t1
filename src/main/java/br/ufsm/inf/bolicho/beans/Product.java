package br.ufsm.inf.bolicho.beans;

import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 27/12/12
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */

@Named
@SessionScoped
public class Product implements Serializable {

    private int id;
    private String name;
    private String description;
    private double price;
    private double weight;
    private int quantityInStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

}
