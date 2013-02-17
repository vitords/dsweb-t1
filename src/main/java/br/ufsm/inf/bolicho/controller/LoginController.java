package br.ufsm.inf.bolicho.controller;

import br.ufsm.inf.bolicho.dao.UserDAO;
import br.ufsm.inf.bolicho.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Vitor
 * Date: 17/02/13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private User loggedUser;
    private String email;
    private String password;
    boolean logged;
    boolean loginFailed;

    public LoginController() {
        loggedUser = null;
        email = "e-mail";
        password = "senha";
        logged = false;
        loginFailed = false;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(boolean loginFailed) {
        this.loginFailed = loginFailed;
    }

    public void login() {
        loginFailed = false;
        loggedUser = new UserDAO().findByEmail(email);
        if (loggedUser != null && loggedUser.getPassword().equals(password)) {
            logged = true;
        } else {
            loginFailed = true;
        }
    }

    public void logout() {
        loggedUser = null;
        logged = false;
        loginFailed = false;
    }

}
