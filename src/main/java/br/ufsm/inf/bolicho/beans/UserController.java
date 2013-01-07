package br.ufsm.inf.bolicho.beans;

import br.ufsm.inf.bolicho.dao.DAOException;
import br.ufsm.inf.bolicho.dao.UserDAO;

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
 * Date: 02/01/13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {

    private User currentUser;
    private UserDAO userDAO;
    private User[] selectedUsers;

    public UserController() {
        currentUser = new User();
        userDAO = new UserDAO();
        userDAO.initialize();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addUser(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "Usuário "
                        + actionEvent.getComponent().getAttributes().get("firstName")
                        + " "
                        +actionEvent.getComponent().getAttributes().get("lastName")
                        + " cadastrado com sucesso!")
        );

        try {
            userDAO.insert(currentUser);
            currentUser = new User();
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void removeUser(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public void updateUser(ActionEvent actionEvent) {
        // TODO: Implementar
    }

    public List<User> searchUser(ActionEvent actionEvent) {
        return null; // TODO: Implementar
    }

    public List<User> getUserList() {
        try {
            return userDAO.retrieveAll();
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public void setSelectedUsers(User[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public User[] getSelectedUsers() {
        return this.selectedUsers;
    }

    public void removeSelectedUsers(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuários removidos."));
        for (User selectUser : selectedUsers) {
            try {
                this.userDAO.delete(selectUser);
            } catch (DAOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
}
