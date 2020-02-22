package landingpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgottenPasswordPage {

    private WebDriver driver;
    private By emailField = By.id("email");
    private By retrievePasswordButton = By.cssSelector("#forgot_password button");
    private By emailMessage = By.id("content");

    public ForgottenPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public SecureAreaPage clickRetrievePasswordButton() {
        driver.findElement(retrievePasswordButton).click();
        return new SecureAreaPage(driver);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public String getEmailMessage() {
        return driver.findElement(emailMessage).getText();
    }
}
