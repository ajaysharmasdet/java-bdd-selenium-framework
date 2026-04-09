package com.enterprise.automation.tests.utils;

import com.enterprise.automation.framework.driver.DriverFactory;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Utility class for capturing and attaching screenshots to Cucumber scenarios.
 */
public final class ScreenshotUtils {

    private ScreenshotUtils() {
        // Prevent instantiation
    }

    /**
     * Captures a screenshot from the current browser and attaches it to the
     * scenario.
     *
     * @param scenario current Cucumber scenario
     */
    public static void attachScreenshotToScenario(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.BYTES);

        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}