package com.enterprise.automation.framework.pages;

import com.enterprise.automation.framework.actions.ElementActions;
import org.openqa.selenium.By;

/**
 * Page object representing the SauceDemo login page.
 */
public class LoginPage {

    private final ElementActions actions = new ElementActions();

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public void enterUsername(String username) {
        actions.type(usernameInput, username);
    }

    public void enterPassword(String password) {
        actions.type(passwordInput, password);
    }

    public void clickLogin() {
        actions.click(loginButton);
    }

    public boolean isLoginButtonDisplayed() {
        return actions.isDisplayed(loginButton);
    }

    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductsPage();
    }

    public void attemptLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return actions.getText(errorMessage);
    }
}