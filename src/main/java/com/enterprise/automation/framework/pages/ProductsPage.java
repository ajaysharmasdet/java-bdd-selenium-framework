package com.enterprise.automation.framework.pages;

import com.enterprise.automation.framework.actions.ElementActions;
import com.enterprise.automation.framework.waits.WaitManager;
import org.openqa.selenium.By;

/**
 * Page object representing the SauceDemo products page.
 */
public class ProductsPage {

    private final ElementActions actions = new ElementActions();

    private final By productsTitle = By.className("title");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By loginButton = By.id("login-button");

    public String getProductsHeaderText() {
        return actions.getText(productsTitle);
    }

    public boolean isDisplayed() {
        return "Products".equals(getProductsHeaderText());
    }

    public void clickMenu() {
        actions.click(menuButton);
        WaitManager.waitForVisibility(logoutLink);
    }

    public void clickLogout() {
        WaitManager.waitForElementToBeClickable(logoutLink).click();
    }

    public void logout() {
        clickMenu();
        clickLogout();
    }

    public boolean isLoginPageDisplayedAfterLogout() {
        return actions.isDisplayed(loginButton);
    }
}