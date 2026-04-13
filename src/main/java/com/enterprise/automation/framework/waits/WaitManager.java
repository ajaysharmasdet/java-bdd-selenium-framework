package com.enterprise.automation.framework.waits;

import com.enterprise.automation.framework.config.FrameworkConfig;
import com.enterprise.automation.framework.driver.DriverFactory;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Central wait utility class for explicit waits.
 *
 * <p>
 * This class provides reusable wait methods so that Selenium synchronization
 * logic is centralized and not duplicated across pages or actions.
 * </p>
 */
public final class WaitManager {

    private static final Logger log = LoggerFactory.getLogger(WaitManager.class);

    private WaitManager() {
        // Prevent instantiation
    }

    /**
     * Waits until the element located by the given locator becomes visible.
     *
     * @param locator element locator
     * @return visible WebElement
     */
    public static WebElement waitForVisibility(By locator) {
        log.debug("Waiting for visibility of: {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element located by the given locator becomes clickable.
     *
     * @param locator element locator
     * @return clickable WebElement
     */
    public static WebElement waitForElementToBeClickable(By locator) {
        log.debug("Waiting for element to be clickable: {}", locator);
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the element located by the given locator is present in the DOM.
     *
     * @param locator element locator
     * @return present WebElement
     */
    public static WebElement waitForPresence(By locator) {
        log.debug("Waiting for presence of: {}", locator);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits until the element located by the given locator becomes invisible.
     *
     * @param locator element locator
     * @return true if the element becomes invisible, otherwise false
     */
    public static boolean waitForInvisibility(By locator) {
        log.debug("Waiting for invisibility of: {}", locator);
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits until the page title contains the expected text.
     *
     * @param titleText expected partial title text
     * @return true if title contains expected text
     */
    public static boolean waitForTitleContains(String titleText) {
        log.debug("Waiting for title to contain: '{}'", titleText);
        return getWait().until(ExpectedConditions.titleContains(titleText));
    }

    /**
     * Creates and returns a configured WebDriverWait instance.
     *
     * @return configured WebDriverWait
     */
    private static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(FrameworkConfig.getExplicitWaitSeconds()));

        wait.ignoring(StaleElementReferenceException.class);
        return wait;
    }
}
