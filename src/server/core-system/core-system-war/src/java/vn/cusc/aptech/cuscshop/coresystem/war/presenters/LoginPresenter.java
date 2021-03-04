package vn.cusc.aptech.cuscshop.coresystem.war.presenters;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cuscshop.coresystem.war.helpers.ValidationHelper;
import vn.cusc.aptech.cuscshop.coresystem.war.helpers.ViewHelper;
import vn.cusc.aptech.cuscshop.coresystem.war.session.AuthenticationSession;

@Named(value = "loginPresenter")
@ViewScoped
public class LoginPresenter implements Serializable {

    @Inject
    private AuthenticationSession authenticationSession;

    private String username;
    private String usernameInputStyleClass;

    private String password;
    private String passwordInputStyleClass;

    private String authMessage;

    @PostConstruct
    public void initialize() {
        this.username = null;
        this.usernameInputStyleClass = null;

        this.password = null;
        this.passwordInputStyleClass = null;

        this.authMessage = null;
    }

    public String login() {
        boolean usernameValid = Pattern.matches(ValidationHelper.usernamePattern, this.username);
        boolean passwordValid = Pattern.matches(ValidationHelper.passwordPattern, this.password);

        this.usernameInputStyleClass = usernameValid ? null : ValidationHelper.invalidStyleClass;
        this.passwordInputStyleClass = passwordValid ? null : ValidationHelper.invalidStyleClass;

        if (!usernameValid || !passwordValid) {
            this.authMessage = null;
            return null;
        }

        this.authenticationSession.login(this.username, this.password);
        if (this.authenticationSession.isLoggedIn()) {
            this.authMessage = null;
            return ViewHelper.redirect("/pages/dashboard/home");
        } else {
            this.authMessage = "Username or password incorrect.";
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
