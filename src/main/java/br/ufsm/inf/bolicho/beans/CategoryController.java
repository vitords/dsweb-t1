package br.ufsm.inf.bolicho.beans;

import br.ufsm.inf.bolicho.dao.CategoryDAO;
import br.ufsm.inf.bolicho.dao.DAOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class CategoryController implements Serializable {

    private Category currentCategory;
    private CategoryDAO categoryDAO;

    public CategoryController() {
        currentCategory = new Category();
        categoryDAO = new CategoryDAO();
        categoryDAO.initialize();
    }

    public void addCategory(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria "
                + currentCategory.getName()
                + " criada!")
        );

        try {
            categoryDAO.insert(currentCategory);
            currentCategory = new Category();
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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
        try {
            return categoryDAO.retrieveAll();
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
