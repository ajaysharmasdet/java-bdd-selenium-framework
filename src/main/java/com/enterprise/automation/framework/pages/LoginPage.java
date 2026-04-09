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

    /**
     * Enters username into the username field.
     *
     * @param username username value
     */
    public void enterUsername(String username) {
        actions.type(usernameInput, username);
    }

    /**
     * Enters password into the password field.
     *
     * @param password password value
     */
    public void enterPassword(String password) {
        actions.type(passwordInput, password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        actions.click(loginButton);
    }

    /**
     * Checks whether login button is displayed.
     *
     * @return true if displayed, otherwise false
     */
    public boolean isLoginButtonDisplayed() {
        return actions.isDisplayed(loginButton);
    }

    /**
     * Performs login and returns the next page object.
     *
     * @param username username value
     * @param password password value
     * @return ProductsPage after successful login
     */
    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductsPage();
    }
}