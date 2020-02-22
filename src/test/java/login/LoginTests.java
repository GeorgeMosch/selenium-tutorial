package login;

import base.BaseTests;
import org.testng.annotations.Test;
import landingpage.LoginPage;
import landingpage.SecureAreaPage;

import static org.testng.Assert.*;

public class LoginTests extends BaseTests {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
//        assertEquals(secureAreaPage.getAlertText(),
//                "You logged into a secure area! x",
//                "Alert text is incorrect");
        assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"), "Alert text is incorrect");
    }
}
