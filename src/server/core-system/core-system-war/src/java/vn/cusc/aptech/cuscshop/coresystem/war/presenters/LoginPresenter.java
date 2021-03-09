package vn.cusc.aptech.cuscshop.coresystem.war.presenters;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cuscshop.coresystem.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cuscshop.coresystem.war.app.helpers.ViewHelper;
import vn.cusc.aptech.cuscshop.coresystem.war.session.AuthSession;

@Named(value = "loginPresenter")
@ViewScoped
public class LoginPresenter implements Serializable {

    @Inject
    private ViewHelper viewHelper;
    @Inject
    private AuthSession authSession;

    private String username;
    private String usernameInputStyleClass;

    private String password;
    private String passwordInputStyleClass;

    private String authMessage;

    @PostConstruct
    public void init() {
        username = null;
        usernameInputStyleClass = null;

        password = null;
        passwordInputStyleClass = null;

        authMessage = null;
    }

    public String login() {
        boolean usernameValid = Pattern.matches(ValidationHelper.usernamePattern, username);
        boolean passwordValid = Pattern.matches(ValidationHelper.passwordPattern, password);

        usernameInputStyleClass = usernameValid ? null : ValidationHelper.invalidStyleClass;
        passwordInputStyleClass = passwordValid ? null : ValidationHelper.invalidStyleClass;

        if (!usernameValid || !passwordValid) {
            authMessage = null;
            return null;
        }

        authSession.login(username, password);
        if (authSession.isLoggedIn()) {
            authMessage = null;
            return viewHelper.getPage("dashboard/home");
        } else {
            authMessage = "Username or password incorrect.";
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameInputStyleClass() {
        return usernameInputStyleClass;
    }

    public void setUsernameInputStyleClass(String usernameInputStyleClass) {
        this.usernameInputStyleClass = usernameInputStyleClass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordInputStyleClass() {
        return passwordInputStyleClass;
    }

    public void setPasswordInputStyleClass(String passwordInputStyleClass) {
        this.passwordInputStyleClass = passwordInputStyleClass;
    }

    public String getAuthMessage() {
        return authMessage;
    }

    public void setAuthMessage(String authMessage) {
        this.authMessage = authMessage;
    }

}
