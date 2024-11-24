package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDef {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setupWebDriver() {
        // Set the Edge driver system property
       System.setProperty("webdriver.edge.driver", "C:/Users/Ghada/Downloads/edgedriver_win64 (1)/msedgedriver.exe");

        // Set up EdgeOptions
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-web-security");
        edgeOptions.addArguments("--disable-dev-shm-usage");
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--headless");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        // Instantiate EdgeDriver with the given options
        driver = new EdgeDriver(edgeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @After
    public void teardownWebDriver() {
        // Quit the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("Open the Edge and launch the application")
    public void open_the_Edge_and_launch_the_application() {
        driver.get("https://opensource-demo.orangehrmlive.com/");}

    @When("I enter the Username {string} and Password {string}")
    public void i_enter_the_Username_and_Password(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(password);
    }


    @Then("I click on the Reset button to clear the credentials")
    public void i_click_on_the_Login_button() {
        // Wait for the Login button to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-button.oxd-button--main.orangehrm-login-button")
        )).click();
    }

}
