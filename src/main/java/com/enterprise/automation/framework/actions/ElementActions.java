package com.enterprise.automation.framework.actions;

import com.enterprise.automation.framework.driver.DriverFactory;
import com.enterprise.automation.framework.waits.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reusable wrapper class for common element-level actions.
 *
 * <p>
 * This class centralizes basic Selenium interactions so that page classes
 * remain
 * clean and readable.
 * </p>
 */
public class ElementActions {

    private static final Logger log = LoggerFactory.getLogger(ElementActions.class);

    /**
     * Clicks an element after waiting for it to become clickable.
     *
     * @param locator element locator
     */
    public void click(By locator) {
        log.debug("Clicking element: {}", locator);
        WaitManager.waitForElementToBeClickable(locator).click();
    }

    /**
     * Clears existing value and types a new value into the element.
     *
     * @param locator element locator
     * @param value   value to enter
     */
    public void type(By locator, String value) {
        log.debug("Typing '{}' into element: {}", value, locator);
        WebElement element = WaitManager.waitForVisibility(locator);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Returns visible text from the element.
     *
     * @param locator element locator
     * @return element text
     */
    public String getText(By locator) {
        log.debug("Getting text from element: {}", locator);
        return WaitManager.waitForVisibility(locator).getText();
    }

    /**
     * Checks whether an element is displayed.
     *
     * <p>
     * Returns false instead of failing immediately when the element is not found
     * within the configured wait time.
     * </p>
     *
     * @param locator element locator
     * @return true if displayed, otherwise false
     */
    public boolean isDisplayed(By locator) {
        try {
            return WaitManager.waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException exception) {
            return false;
        }
    }

    /**
     * Returns the current browser page title.
     *
     * @return page title
     */
    public String getPageTitle() {
        return DriverFactory.getDriver().getTitle();
    }

    /**
     * Returns the current browser URL.
     *
     * @return current page URL
     */
    public String getCurrentUrl() {
        return DriverFactory.getDriver().getCurrentUrl();
    }
}
