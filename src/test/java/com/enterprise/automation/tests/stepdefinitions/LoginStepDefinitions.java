package com.enterprise.automation.tests.stepdefinitions;

import com.enterprise.automation.framework.pages.LoginPage;
import com.enterprise.automation.framework.pages.ProductsPage;
import com.enterprise.automation.framework.utils.TestDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

/**
 * Step definitions for login feature.
 */
public class LoginStepDefinitions {

    private final LoginPage loginPage = new LoginPage();
    private ProductsPage productsPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        Assertions.assertTrue(
                loginPage.isLoginButtonDisplayed(),
                "Login page is not displayed.");
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        productsPage = loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password"));
    }

    @Then("the products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        Assertions.assertTrue(
                productsPage.isDisplayed(),
                "Products page is not displayed after login.");
    }
}