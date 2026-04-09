package com.enterprise.automation.framework.driver;

import com.enterprise.automation.framework.config.FrameworkConfig;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Factory class responsible for creating, storing, and cleaning up WebDriver
 * instances.
 *
 * <p>
 * This implementation uses ThreadLocal so that each test thread gets its own
 * browser
 * instance. This is important for parallel execution and enterprise-scale test
 * runs.
 * </p>
 */
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
        // Prevent instantiation
    }

    /**
     * Initializes the WebDriver for the current thread if it is not already
     * initialized.
     */
    public static void initializeDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        String browser = System.getProperty("browser", FrameworkConfig.getBrowser()).trim().toLowerCase();
        WebDriver webDriver = createDriver(browser);

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(FrameworkConfig.getPageLoadTimeoutSeconds()));

        DRIVER.set(webDriver);
    }

    /**
     * Returns the WebDriver associated with the current thread.
     *
     * @return WebDriver instance for the current thread
     * @throws IllegalStateException if driver was not initialized
     */
    public static WebDriver getDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver is not initialized for the current thread.");
        }
        return webDriver;
    }

    /**
     * Quits the WebDriver for the current thread and removes it from ThreadLocal
     * storage.
     */
    public static void quitDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver != null) {
            webDriver.quit();
            DRIVER.remove();
        }
    }

    /**
     * Creates a browser-specific WebDriver instance.
     *
     * @param browser browser name from config or system property
     * @return WebDriver instance
     */
    private static WebDriver createDriver(String browser) {
        return switch (browser) {
            case "chrome" -> new ChromeDriver(getChromeOptions());
            case "firefox" -> new FirefoxDriver(getFirefoxOptions());
            case "edge" -> new EdgeDriver(getEdgeOptions());
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser + ". Supported browsers are: chrome, firefox, edge.");
        };
    }

    /**
     * Returns Chrome browser options.
     *
     * @return configured ChromeOptions
     */
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        if (FrameworkConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        return options;
    }

    /**
     * Returns Firefox browser options.
     *
     * @return configured FirefoxOptions
     */
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (FrameworkConfig.isHeadless()) {
            options.addArguments("-headless");
        }

        return options;
    }

    /**
     * Returns Edge browser options.
     *
     * @return configured EdgeOptions
     */
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (FrameworkConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }

        return options;
    }
}