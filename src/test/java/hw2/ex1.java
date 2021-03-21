package hw2;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ex1 {
    private WebDriver driver;//в  loginPage + setLogin, страницы возвращают новые страницы
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    String[] headers, textUnderPic;
    List<WebElement> list;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arcta\\Downloads\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://jdi-testing.github.io/jdi-light/index.html";
        driver.get(baseUrl);
    }

    @Test
    public void OpenUrlTest() {
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test
    public void TitleTest() {
        Assert.assertTrue(driver.getTitle().contains("Home Page"));
    }

    @Test
    public void LoginTest() {
        driver.findElement(By.id("user-icon")).click();
        driver.findElement(By.id("name")).sendKeys("Roman");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Jdi1234");
        driver.findElement(By.id("login-button")).click();

        //Assert.assertTrue(driver.getTitle().contains("Home Page"));
    }

    @Test
    public void UserNameTest() {
        Assert.assertEquals(driver.findElement(By.id("user-name")).getText(), "ROMAN IOVLEV");
    }

    @Test
    public void BrowserTitleTest() {
        Assert.assertTrue(driver.getTitle().contains("Home Page"));
    }

    @Test
    public void ItemsTest() {
        list = driver.findElements(By.cssSelector("ul.uui-navigation.nav.navbar-nav.m-l8 a"));
        int i = 0;
        for (WebElement we : list) {
            if (we.isDisplayed() && we.isEnabled()) {
                Assert.assertEquals(headers[i++], we.getText());
            }
        }
    }

    @Test
    public void ImagesTest() {
        list = driver.findElements(By.cssSelector(".benefit-icon"));
        Assert.assertEquals(list.size(), 4);
        for (WebElement we : list) {
            Assert.assertTrue(we.isDisplayed());
        }
    }

    @Test
    public void TextTest() {
        list = driver.findElements(By.cssSelector(".benefit-icon+.benefit-txt"));
        Assert.assertEquals(list.size(), 4);
        int i = 0;
        for (WebElement we : list) {
            Assert.assertEquals(textUnderPic[i++], we.getText());
        }
    }

    @Test
    public void TextHeaderTest() {
        WebElement webElement = driver.findElement(By.cssSelector("h3.main-title.text-center"));
        Assert.assertTrue(webElement.isDisplayed());
        String str = webElement.getText();
        Assert.assertEquals(str, "EPAM FRAMEWORK WISHES…");

        webElement = driver.findElement(By.cssSelector("p.main-txt.text-center"));
        Assert.assertTrue(webElement.isDisplayed());
        str = webElement.getText();

        Assert.assertEquals(str, "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR " +
                "INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION " +
                "ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN " +
                "REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.".toUpperCase(Locale.ROOT));
    }

    @Test
    public void IframeTest() {
        list = driver.findElements(By.cssSelector("iframe"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void LogoTest() {
        driver.switchTo().frame("second_frame");
        WebElement webElement = driver.findElement(By.cssSelector("img#epam-logo"));
        Assert.assertTrue(webElement.isDisplayed());
    }

    @Test
    public void DefWinTest() {
        driver.switchTo().defaultContent();
    }

    @Test
    public void SubHeaderTest() {
        WebElement webElement = driver.findElement(By.cssSelector("h3.text-center>a"));
        Assert.assertTrue(webElement.isDisplayed());
        String str = webElement.getText();
        Assert.assertEquals(str, "JDI GITHUB");
    }

    @Test
    public void LinkTest() {
        WebElement webElement = driver.findElement(By.cssSelector("h3.text-center>a[target=_blank]"));
        Assert.assertTrue(webElement.isDisplayed());
        String str = webElement.getAttribute("href");
        Assert.assertEquals(str, "https://github.com/epam/JDI");
    }

    @Test
    public void LeftSecTest() {
        WebElement webElement = driver.findElement(By.cssSelector("ul.sidebar-menu"));
        Assert.assertTrue(webElement.isDisplayed());
    }

    @Test
    public void FooterTest() {
        WebElement webElement = driver.findElement(By.cssSelector("footer"));
        Assert.assertTrue(webElement.isDisplayed());
    }



    @AfterTest
    public void Down() {
        driver.close();
        driver.quit();
    }
}