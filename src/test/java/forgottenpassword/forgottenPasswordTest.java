package forgottenpassword;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class forgottenPasswordTest extends BaseTests {

    @Test
    public void testEmailMessage() {
        var forgotPasswordPage = homePage.clickForgotPassword();
        forgotPasswordPage.setEmail("george@google.com");
        forgotPasswordPage.clickRetrievePasswordButton();
        assertTrue(forgotPasswordPage.getEmailMessage().contains("Your e-mail's been sent!"));
    }
}
