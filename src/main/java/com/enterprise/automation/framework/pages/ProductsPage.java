package com.enterprise.automation.framework.pages;

import com.enterprise.automation.framework.actions.ElementActions;
import org.openqa.selenium.By;

/**
 * Page object representing the SauceDemo products page.
 */
public class ProductsPage {

    private final ElementActions actions = new ElementActions();
    private final By productsTitle = By.className("title");

    /**
     * Returns the products page header text.
     *
     * @return header text
     */
    public String getProductsHeaderText() {
        return actions.getText(productsTitle);
    }

    /**
     * Checks whether the products page is displayed.
     *
     * @return true if the expected products title is displayed
     */
    public boolean isDisplayed() {
        return "Products".equals(getProductsHeaderText());
    }
}