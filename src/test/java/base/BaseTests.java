package base;

import com.google.common.io.Files;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import landingpage.HomePage;
import utils.EventReporter;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class BaseTests {

//    private WebDriver driver;
    private EventFiringWebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/browserBinaries/chromedriverMac");
//        driver = new ChromeDriver();
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventReporter());
//        driver.get("https://the-internet.herokuapp.com/");
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        goBackToHomePage();
        setCookie();
//        homePage = new HomePage(driver);
//        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(375, 812));
//        WebElement inputLink =  driver.findElement(By.linkText("Inputs"));
//        List<WebElement> links = driver.findElements(By.tagName("a"));
//        System.out.println(links.size());
//        inputLink.click();
//        WebElement shift = driver.findElement(By.linkText("Shifting Content"));
//        shift.click();
//        WebElement example1 = driver.findElement(By.linkText("Example 1: Menu Element"));
//        example1.click();
//        List<WebElement> buttons = driver.findElements(By.xpath("//*[@id=\"content\"]/div/ul/li/a"));
//        System.out.println(buttons.size());
//
//        System.out.println(driver.getTitle());
    }

    @BeforeMethod
    public void goBackToHomePage() {
        driver.get("https://the-internet.herokuapp.com/");
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot taken " + screenshot.getAbsolutePath());
            try {
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
//        options.setHeadless(true);
//        options.addArguments("disable-infobars");
        return options;
    }

    private void setCookie() {
        Cookie cookie = new Cookie.Builder("tau", "123")
                .domain("the-internet.herokuapp.com")
                .build();
        driver.manage().addCookie(cookie);
    }
}
