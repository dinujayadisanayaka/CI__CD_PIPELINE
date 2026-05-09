package githubactions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Web Page Title Verification")
@Feature("Title Check for Popular Websites")
public class Demo {
    protected WebDriver driver;

    @BeforeMethod
    @Step("Setting up Chrome WebDriver with headless options")
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
    }

    @Test
    @Story("Verify GitHub page title")
    @Description("Navigate to GitHub and verify the page title contains 'GitHub'")
    public void testcase1(){
        navigateToUrl("https://github.com");
        String title = verifyPageTitle();
        Assert.assertTrue(title.contains("GitHub"), "Title should contain GitHub");
        attachScreenshot("GitHub Home Page");
    }

    @Test
    @Story("Verify Google page title")
    @Description("Navigate to Google and verify the page title contains 'Google'")
    public void testcase2(){
        navigateToUrl("https://google.com");
        String title = verifyPageTitle();
        Assert.assertTrue(title.contains("Google"), "Title should contain Google");
        attachScreenshot("Google Home Page");
    }

    @Test
    @Story("Verify Facebook page title")
    @Description("Navigate to Facebook and verify the page title contains 'Facebook'")
    public void testcase3(){
        navigateToUrl("https://facebook.com");
        String title = verifyPageTitle();
        Assert.assertTrue(title.contains("Facebook"), "Title should contain Facebook");
        attachScreenshot("Facebook Home Page");
    }

    @Test
    @Story("Verify Yahoo page title")
    @Description("Navigate to Yahoo and verify the page title contains 'Yahoo'")
    public void testcase4(){
        navigateToUrl("https://yahoo.com");
        String title = verifyPageTitle();
        Assert.assertTrue(title.contains("Yahoo"), "Title should contain Yahoo");
        attachScreenshot("Yahoo Home Page");
    }

    @Step("Navigate to URL: {url}")
    private void navigateToUrl(String url) {
        driver.get(url);
    }

    @Step("Verify and return the page title")
    private String verifyPageTitle() {
        String title = driver.getTitle();
        Allure.addAttachment("Page Title", title);
        System.out.println("Title: " + title);
        return title;
    }

    @Attachment(value = "{description}", type = "image/png")
    private byte[] attachScreenshot(String description) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    @Step("Tearing down WebDriver")
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
