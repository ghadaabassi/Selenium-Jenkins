package org.example;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchSteps {
    public WebDriver driver;
    public WebDriverWait wait;
    @Before
    public void createWebDriver() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Ghada\\Downloads\\edgedriver_win64 (1)\\msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-web-security");
        edgeOptions.addArguments("--disable-dev-shm-usage");
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--headless");
        edgeOptions.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        driver = new EdgeDriver(edgeOptions);
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @After
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open google search page")
    public void i_open_google_search_page() {
        driver.get("https://www.google.com");
    }

    @When("I lookup the word {string}")
    public void i_lookup_the_word(String string) {
        // Wait for the search box to be visible and interactable
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(string);

        // Wait for the search button to be clickable
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
        searchButton.click();
    }

    @Then("Search results display the word {string}")
    public void search_results_display_the_word(String string) {
        // Wait for the page title to contain the search word
        wait.until(ExpectedConditions.titleContains(string));

        // Check if the search word is in the page title
        boolean isWordPresent = driver.getTitle().contains(string);
        System.out.println("Search result contains the word: " + isWordPresent);
        assert isWordPresent : "The word is not present in the search results";
    }
}
