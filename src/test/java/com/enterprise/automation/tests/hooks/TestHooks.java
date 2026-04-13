package com.enterprise.automation.tests.hooks;

import com.enterprise.automation.framework.config.FrameworkConfig;
import com.enterprise.automation.framework.driver.DriverFactory;
import com.enterprise.automation.tests.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cucumber hooks for browser setup and cleanup.
 */
public class TestHooks {

    private static final Logger log = LoggerFactory.getLogger(TestHooks.class);

    /**
     * Initializes browser before each scenario and opens the application URL.
     */
    @Before
    public void setUp(Scenario scenario) {
        log.info("Starting scenario: '{}'", scenario.getName());
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
            log.warn("Scenario FAILED: '{}' — capturing screenshot", scenario.getName());
            ScreenshotUtils.attachScreenshotToScenario(scenario);
        } else {
            log.info("Scenario PASSED: '{}'", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
