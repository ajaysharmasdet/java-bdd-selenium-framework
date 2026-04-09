package com.enterprise.automation.tests.stepdefinitions;

import com.enterprise.automation.framework.pages.LoginPage;
import com.enterprise.automation.framework.pages.ProductsPage;
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

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.attemptLogin(username, password);
    }

    @Then("the products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        productsPage = new ProductsPage();

        Assertions.assertTrue(
                productsPage.isDisplayed(),
                "Products page is not displayed after login.");
    }

    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedMessage) {
        Assertions.assertEquals(
                expectedMessage,
                loginPage.getErrorMessage(),
                "Unexpected error message displayed.");
    }

    @Given("the user logs in with valid username and password")
    public void theUserLogsInWithValidUsernameAndPassword() {
        productsPage = loginPage.login("standard_user", "secret_sauce");
        Assertions.assertTrue(productsPage.isDisplayed(), "Login failed for valid user.");
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        productsPage.logout();
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        Assertions.assertTrue(
                productsPage.isLoginPageDisplayedAfterLogout(),
                "User was not redirected to login page after logout.");
    }
}