import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    WebDriver driver;

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void openSiteTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/", driver.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
    }

    @Test
    void openNavigation() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        WebElement navigationLink = driver.findElement(By.xpath("//a[@href = 'navigation1.html']"));
        navigationLink.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", driver.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        
    }
}
