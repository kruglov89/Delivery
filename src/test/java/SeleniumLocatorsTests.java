import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static mfti.Constants.BASE_URL;

public class SeleniumLocatorsTests {

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
    void baseLocatorsTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement textInputById = driver.findElement(By.id("my-text-id"));
        textInputById.sendKeys("текст");
        Thread.sleep(1000);
        WebElement passwordByName = driver.findElement(By.name("my-password"));
        passwordByName.sendKeys("1234567890");
        Thread.sleep(1000);
        WebElement textareaByName = driver.findElement(By.name("my-textarea"));
        textareaByName.sendKeys("2+2=4");
        Thread.sleep(1000);
        WebElement disableInputByName = driver.findElement(By.name("my-disabled"));
        disableInputByName.click();
        Thread.sleep(1000);
        WebElement readonlyInputByName = driver.findElement(By.name("my-readonly"));
        readonlyInputByName.click();
        Thread.sleep(1000);
        WebElement dropdownSelectByName = driver.findElement(By.name("my-select"));
        dropdownSelectByName.click();
        Thread.sleep(1000);
        WebElement dropdownDatalistByName = driver.findElement(By.name("my-datalist"));
        dropdownDatalistByName.sendKeys("New York");
        Thread.sleep(1000);
        driver.findElement(By.name("my-file"));
        WebElement checkedCheckboxById = driver.findElement(By.id("my-check-1"));
        checkedCheckboxById.click();
        Thread.sleep(1000);
        WebElement defaultCheckboxById = driver.findElement(By.id("my-check-2"));
        defaultCheckboxById.click();
        Thread.sleep(1000);
        WebElement defaultRadioById = driver.findElement(By.id("my-radio-2"));
        defaultRadioById.click();
        Thread.sleep(1000);
        WebElement checkedRadioById = driver.findElement(By.id("my-radio-1"));
        checkedRadioById.click();
        Thread.sleep(1000);
        WebElement colorPickerByName = driver.findElement(By.name("my-colors"));
        colorPickerByName.click();
        Thread.sleep(1000);
        WebElement datePickerByName = driver.findElement(By.name("my-date"));
        datePickerByName.sendKeys("02/26/2025");
        Thread.sleep(1000);
        WebElement exampleRangeByName = driver.findElement(By.name("my-range"));
        exampleRangeByName.click();
        Thread.sleep(1000);
        WebElement submitButtonByClassName = driver.findElement(By.cssSelector(".btn.btn-outline-primary.mt-3"));
        submitButtonByClassName.click();
        Thread.sleep(1000);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        Thread.sleep(1000);
        WebElement titleByLinkText = driver.findElement(By.linkText("Return to index"));
        titleByLinkText.click();
        Thread.sleep(1000);
    }

    @Test
    void cssSelectorsTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        //id
        WebElement defaultCheckboxById = driver.findElement(By.cssSelector("#my-check-2"));
        defaultCheckboxById.click();
        Thread.sleep(1000);
        //classname
        WebElement textInputByClassName = driver.findElement(By.cssSelector(".form-control"));
        textInputByClassName.sendKeys("textInputByClass");
        Thread.sleep(1000);
        //name
        WebElement passwordByName = driver.findElement(By.cssSelector("[name='my-password']"));
        passwordByName.sendKeys("123454321");
        Thread.sleep(1000);
        //tag
        WebElement textAreaByTag = driver.findElement(By.cssSelector("Textarea"));
        textAreaByTag.sendKeys("textAreaByTag");
        Thread.sleep(1000);
        //Attribute
        WebElement textInputByAttribute = driver.findElement(By.cssSelector("[myprop='myvalue']"));
        textInputByAttribute.sendKeys(" textInputByAttribute");
        Thread.sleep(1000);
        //Tag+Class
        WebElement textInputByTagAndClass = driver.findElement(By.cssSelector("Textarea.form-control"));
        textInputByTagAndClass.sendKeys(" textInputByTagAndClass");
        Thread.sleep(1000);
        //Tag+Id
        WebElement textInputByTagAndId = driver.findElement(By.cssSelector("input#my-check-1"));
        textInputByTagAndId.click();
        Thread.sleep(1000);
        //Tag+Attribute
        WebElement textInputByTagAndAttribute = driver.findElement(By.cssSelector("Textarea[rows='3']"));
        textInputByTagAndAttribute.sendKeys(" textInputByTagAndAttribute");
        Thread.sleep(1000);
        //Class+Class
        WebElement textInputByClassAndClass = driver.findElement(By.cssSelector(".form-label.w-100"));
        textInputByClassAndClass.sendKeys(" textInputByClassAndClass");
        Thread.sleep(1000);
    }

    @Test
    void cssSelectorsTest2() throws InterruptedException {
        //Prefix
        WebElement prefix = driver.findElement(By.cssSelector("a[href^='naviga']"));
        prefix.click();
        Thread.sleep(1000);
        driver.get(BASE_URL);
        //Suffix
        WebElement suffix = driver.findElement(By.cssSelector("a[href$='html']"));
        suffix.click();
        Thread.sleep(1000);
        driver.get(BASE_URL);
        //Substring
        WebElement substring = driver.findElement(By.cssSelector("a[href*='-over']"));
        substring.click();
        Thread.sleep(1000);
        driver.get(BASE_URL);
        //Exact match
        WebElement exactMatch = driver.findElement(By.cssSelector("a[href='drag-and-drop.html']"));
        exactMatch.click();
        Thread.sleep(1000);
        driver.get(BASE_URL);
        //Child
        driver.get(BASE_URL + "web-form.html");
        WebElement childCheckbox = driver.findElement(By.cssSelector("label.form-check-label input[type='checkbox']"));
        childCheckbox.click();
        Thread.sleep(1000);
        //Nth-child
        WebElement nthChildCheckbox = driver.findElement(By.cssSelector("div.form-check label:nth-child(2) input"));
        nthChildCheckbox.click();
        Thread.sleep(1000);
    }

    @Test
    void xpathSelectorsTest() throws InterruptedException {
        driver.get(BASE_URL);
        //По абсолютному пути
        WebElement byAbsolutePath = driver.findElement(By.xpath("/html/body/main/div/div[4]/div[1]/div/div/a[2]"));
        byAbsolutePath.click();
        Thread.sleep(1000);
        driver.get(BASE_URL);
        //По относительному пути
        WebElement byRelativePath = driver.findElement(By.xpath("//a[@href='web-form.html']"));
        byRelativePath.click();
        Thread.sleep(1000);
        //По тегу
        WebElement byTag = driver.findElement(By.xpath("//textarea"));
        byTag.sendKeys("byTag");
        Thread.sleep(1000);
        //По тексту элемента
        WebElement byText = driver.findElement(By.xpath("//h1[text()='Hands-On Selenium WebDriver with Java']"));
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", byText.getText());
        //По частичному тексту
        WebElement byPartialText = driver.findElement(By.xpath("//h1[contains(text(), 'Hands-On Selenium WebDriver')]"));
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", byPartialText.getText());
        //По атрибуту
        WebElement byAttribute = driver.findElement(By.xpath("//*[@myprop='myvalue']"));
        byAttribute.sendKeys("byAttribute");
        Thread.sleep(1000);
        //По потомку
        WebElement child = driver.findElement(By.xpath("//label/input[@id='my-check-1']"));
        child.click();
        Thread.sleep(1000);
        //По предку
        WebElement parent = driver.findElement(By.xpath("//input[@id='my-text-id']/.."));
        Assertions.assertEquals("Text input", parent.getText());
        Thread.sleep(1000);
    }
}
