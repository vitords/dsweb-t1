package br.ufsm.inf.bolicho.controller;

import br.ufsm.inf.bolicho.model.Category;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 04/01/13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    private Category currentCategory;

    public CategoryController() {
        currentCategory = new Category();
    }

    public void addCategory(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria "
                + currentCategory.getName()
                + " criada!")
        );

         //   categoryDAO.insert(currentCategory);
            currentCategory = new Category();

    }

    public void removeCategory(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public void updateCategory(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public List<Category> searchCategory(ActionEvent actionEvent) {
        return null; // TODO: Implementar
    }

    public List<Category> getCategoryList() {
           // return categoryDAO.retrieveAll();
        return null;
    }
}
