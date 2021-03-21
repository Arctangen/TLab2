package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Locale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ex2 {
    private WebDriver driver;//в  loginPage + setLogin, страницы возвращают новые страницы
    private String baseUrl;
    String[] serviceoptions, OptionsSide;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    String[] headers, textUnderPic;
    List<WebElement> list;
    Pattern patternWater, patternWind, patternSelen, patternYellow, patternWaterFalse, patternWindFalse;



    @BeforeTest
    public void setUp() {
        patternWater = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Water: condition changed to true");
        patternWind = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Wind: condition changed to true");
        patternSelen = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ metal: value changed to Selen");
        patternYellow = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Colors: value changed to Yellow");
        patternWaterFalse = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Water: condition changed to false");
        patternWindFalse = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Wind: condition changed to false");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arcta\\Downloads\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://jdi-testing.github.io/jdi-light/index.html";
        driver.get(baseUrl);
    }

    @Test
    public void HPTest() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(driver.getTitle().contains("Home Page"));
    }

    @Test
    public void LoginTest() {
        driver.findElement(By.id("user-icon")).click();
        driver.findElement(By.id("name")).sendKeys("Roman");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Jdi1234");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void UserNameTest() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(By.id("user-name")).getText(), "ROMAN IOVLEV");
    }

    @Test
    public void ServiceTest() {
        serviceoptions = new String[]{
                "Support", "Dates", "Search", "Complex Table", "Simple Table",
                "User Table", "Table with pages", "Different elements", "Performance"
        };
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.cssSelector("li.dropdown>a.dropdown-toggle")).click();
        List<WebElement> list = driver.findElements(By.cssSelector("ul.dropdown-menu>li>a"));
        Assert.assertFalse(list.isEmpty());
        int i = 0;
        for (WebElement we : list) {
            softAssert.assertEquals(we.getText(), serviceoptions[i++].toUpperCase(Locale.ROOT));
        }
        softAssert.assertAll();
    }

    @Test
    public void SubCatTest() {
        OptionsSide = new String[] {
                "Support", "Dates", "Complex Table", "Simple Table", "Search",
                "User Table", "Table with pages", "Different elements", "Performance"
        };
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.cssSelector("li.menu-title[index='3']")).click();
        list = driver.findElements(By.cssSelector("li.menu-title[index='3'] li[index]>a"));
        Assert.assertFalse(list.isEmpty(), "Step 6 list was empty.");
        int i = 0;
        for (WebElement we : list) {
            softAssert.assertEquals(we.getText(), OptionsSide[i++]);
        }
        softAssert.assertAll();
    }

    @Test
    public void ServicTest() {
        driver.findElement(By.cssSelector("ul.sub>li[index='8']")).click();
    }

    @Test
    public void InterfaceTest() {
        SoftAssert softAssert = new SoftAssert();
        list = driver.findElements(By.cssSelector("input[type='checkbox']"));
        softAssert.assertEquals(list.size(), 4);
        // 4 radios
        list = driver.findElements(By.cssSelector("input[type='radio']"));
        softAssert.assertEquals(list.size(), 4);
        // 1 dropdown
        list = driver.findElements(By.cssSelector("select.uui-form-element"));
        softAssert.assertEquals(list.size(), 1);
        // 2 buttons
        WebElement webElement = driver.findElement(By.cssSelector("button.uui-button"));
        softAssert.assertTrue(webElement.isEnabled());
        webElement = driver.findElement(By.cssSelector("input.uui-button"));
        softAssert.assertTrue(webElement.isEnabled());
        softAssert.assertAll();
    }

    @Test
    public void RightSecTest() {
        SoftAssert softAssert = new SoftAssert();
        WebElement webElement = driver.findElement(By.cssSelector("#mCSB_2"));
        softAssert.assertTrue(webElement.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void LeftSecTest() {
        SoftAssert softAssert = new SoftAssert();
        WebElement webElement = driver.findElement(By.cssSelector("#mCSB_1"));
        softAssert.assertTrue(webElement.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void boxesTest() {
        list = driver.findElements(By.cssSelector("label.label-checkbox"));
        Assert.assertFalse(list.isEmpty());
        for (WebElement we : list) {
            if (we.getText().equals("Water") || we.getText().equals("Wind")) {
                we.click();
            }
        }
    }

    @Test
    public void LogTest() {
        SoftAssert softAssert = new SoftAssert();
        list = driver.findElements(By.cssSelector("ul.panel-body-list.logs li"));
        Assert.assertFalse(list.isEmpty());
        softAssert.assertTrue(patternWind.matcher(list.get(0).getText()).matches());
        softAssert.assertTrue(patternWater.matcher(list.get(1).getText()).matches());
        softAssert.assertAll();
    }

    @Test
    public void SelenTest() {
        list = driver.findElements(By.cssSelector("label.label-radio"));
        Assert.assertFalse(list.isEmpty());
        for (WebElement we : list) {
            if (we.getText().equals("Selen")) {
                we.click();
            }
        }

    }

    @Test
    public void YellowTest() {
        driver.findElement(By.cssSelector("select.uui-form-element")).click();
        list = driver.findElements(By.cssSelector("select.uui-form-element>option"));
        for (WebElement we : list) {
            if (we.getText().equals("Yellow")) {
                we.click();
            }
        }
    }

    @AfterTest
    public void Down() {
        driver.close();
        driver.quit();
    }
}


