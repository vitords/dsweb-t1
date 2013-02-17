package br.ufsm.inf.bolicho.controller;

import br.ufsm.inf.bolicho.dao.UserDAO;
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
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {

    private User currentUser;
    private User[] selectedUsers;

    public UserController() {
        currentUser = new User();
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
                        + currentUser.getFirstName()
                        + " "
                        + currentUser.getLastName()
                        + " cadastrado com sucesso!")
        );

        new UserDAO().salvar(currentUser);
        currentUser = new User();

    }

    public void removeUser(ActionEvent actionEvent) {
        new UserDAO().excluir(currentUser.getId());
    }

    public void updateUser(ActionEvent actionEvent) {
        new UserDAO().alterar(currentUser);
    }

    public List<User> searchUser(ActionEvent actionEvent) {
        // TODO: Implementar
        return null;
    }

    public List<User> getUserList() {
        try {
            return new UserDAO().findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public void setSelectedUsers(User[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public User[] getSelectedUsers() {
        return this.selectedUsers;
    }

    public void removeSelectedUsers(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuários removidos."));
        for (User selectedUser : selectedUsers) {
            new UserDAO().excluir(selectedUser.getId());
        }
    }
}
