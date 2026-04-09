package com.enterprise.automation.tests.hooks;

import com.enterprise.automation.framework.config.FrameworkConfig;
import com.enterprise.automation.framework.driver.DriverFactory;
import com.enterprise.automation.tests.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Cucumber hooks for browser setup and cleanup.
 */
public class TestHooks {

    /**
     * Initializes browser before each scenario and opens the application URL.
     */
    @Before
    public void setUp() {
        DriverFactory.initializeDriver();
        DriverFactory.getDriver().get(FrameworkConfig.getBaseUrl());
    }

    /**
     * Attaches screenshot on failure and quits browser after each scenario.
     *
     * @param scenario current Cucumber scenario
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtils.attachScreenshotToScenario(scenario);
        }
        DriverFactory.quitDriver();
    }
}