import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static mfti.Constants.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumActionTests {

    WebDriver driver;
    @BeforeEach
    void start() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void webFormOpenTest() throws InterruptedException {
        WebElement webFormButton = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        Thread.sleep(1000);
        webFormButton.click();
        WebElement title = driver.findElement(By.className("display-4"));
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", driver.getCurrentUrl());
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", title.getText());
        Thread.sleep(1000);
    }
    @Test
    void textInputTest() throws InterruptedException {
        driver.get(BASE_URL + "web-form.html");
        WebElement textInput = driver.findElement(By.cssSelector("#my-text-id"));
        Thread.sleep(1000);
        textInput.sendKeys("test1234");
        Thread.sleep(1000);
        Assertions.assertEquals("test1234", textInput.getAttribute("value"));
    }

    @Test
    void textInputClearTest() throws InterruptedException {
        driver.get(BASE_URL + "web-form.html");
        WebElement textInput = driver.findElement(By.cssSelector("#my-text-id"));
        Thread.sleep(1000);
        textInput.sendKeys("test1234");
        Thread.sleep(1000);
        textInput.clear();
        Thread.sleep(1000);
        Assertions.assertEquals("", textInput.getAttribute("value"));
    }

    @Test
    void disabledInputTests() {
        driver.get(BASE_URL + "web-form.html");
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        Assertions.assertFalse(disabledInput.isEnabled());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> disabledInput.sendKeys("test"));
        Assertions.assertEquals("Disabled input", disabledInput.getAttribute("placeholder"));
    }

    @Test
    void selectFromListTests() throws InterruptedException {
        driver.get(BASE_URL + "web-form.html");
        WebElement dropdownSelectMenu = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdownSelectMenu);
        Thread.sleep(1000);
        select.selectByIndex(1);
        Assertions.assertEquals("One", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        select.selectByValue("2");
        Assertions.assertEquals("Two", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        select.selectByVisibleText("Three");
        Assertions.assertEquals("Three", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        select.selectByIndex(0);
        Assertions.assertEquals("Open this select menu", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
    }

    @Test
    void fileUploadTest() throws IOException, InterruptedException {
        String filePath = "src/main/resources/text.txt";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        System.out.println("Содержимое файла: " + content);
        URL url = SeleniumActionTests.class.getClassLoader().getResource("text.txt");
        String absolutePath = null;
        if (url != null) {
            absolutePath = new File(url.getPath()).getAbsolutePath();
            System.out.println("Абсолютный путь к файлу: " + absolutePath);
        } else {
            System.out.println("Ресурс не найден.");
        }
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement fileUpload = driver.findElement(By.name("my-file"));
        fileUpload.sendKeys(absolutePath);
        Thread.sleep(5000);
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
        submit.click();
        Thread.sleep(5000);
        assertThat(driver.getCurrentUrl()).contains("text.txt");
    }

    @Test
    void navigationTest () throws InterruptedException {
        driver.get(BASE_URL + "navigation1.html");
        WebElement navigationPageOne = driver.findElement(By.xpath("//a[@href='navigation1.html']"));
        Thread.sleep(1000);
        navigationPageOne.click();
        WebElement text1 = driver.findElement(By.className("lead"));
        Assertions.assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", text1.getText());
        Thread.sleep(1000);
        WebElement navigationPageTwo = driver.findElement(By.xpath("//a[@href='navigation2.html']"));
        Thread.sleep(1000);
        navigationPageTwo.click();
        WebElement text2 = driver.findElement(By.className("lead"));
        Assertions.assertEquals("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", text2.getText());
        Thread.sleep(1000);
        WebElement navigationPageThree = driver.findElement(By.xpath("//a[@href='navigation3.html']"));
        Thread.sleep(1000);
        navigationPageThree.click();
        WebElement text3 = driver.findElement(By.className("lead"));
        Assertions.assertEquals("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", text3.getText());
        Thread.sleep(1000);
    }

    @Test
    void dropdownMenuTest () throws InterruptedException {
        driver.get(BASE_URL + "dropdown-menu.html");
        Thread.sleep(1000);
        WebElement leftClick = driver.findElement(By.id("my-dropdown-1"));
        new Actions(driver)
                .click(leftClick)
                .perform();
        Thread.sleep(1000);
        WebElement rightClick = driver.findElement(By.id("my-dropdown-2"));
        new Actions(driver)
                .contextClick(rightClick)
                .perform();
        Thread.sleep(1000);
        WebElement doubleClick = driver.findElement(By.id("my-dropdown-3"));
        new Actions(driver)
                .doubleClick(doubleClick)
                .perform();
        Thread.sleep(1000);
    }

    @Test
    void dragAndDropTest () throws InterruptedException {
        driver.get(BASE_URL + "drag-and-drop.html");
        Thread.sleep(1000);
        WebElement draggablePanel = driver.findElement(By.id("draggable"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(draggablePanel, 570, 0).perform();
        Thread.sleep(1000);
    }
}
