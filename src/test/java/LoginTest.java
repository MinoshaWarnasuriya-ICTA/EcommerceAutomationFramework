import TestComponents.*;
import com.aventstack.extentreports.*;
import io.github.bonigarcia.wdm.*;
import org.apache.commons.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;
import resources.*;

import java.io.*;
import java.util.*;

public class LoginTest extends BaseTest {
    @Test
    public void validLogin(String email,String password) {
        String name = "Scott";
        LoginPage loginPage = homePage.goToLoginPage();
        //Verify 'Login to your account' is visible
        Assert.assertEquals(loginPage.getLoginToAccMsgeTxt(), "Login to your account");
        //Enter correct email address and password
        loginPage.login(email, password);
        //Verify that 'Logged in as username' is visible
        String actualLoggedInUserText = loginPage.getLoggedInUserTxt();
        String expectedTxt = "Logged in as " + name;
        Assert.assertEquals(actualLoggedInUserText, expectedTxt);
    }

    @Test
    public void invalidLogin() {
        LoginPage loginPage = homePage.goToLoginPage();
        //Verify 'Login to your account' is visible
        Assert.assertEquals(loginPage.getLoginToAccMsgeTxt(), "Login to your account");
        //Enter incorrect email address and password
        String invalidUser = "minosha@gamil.com";
        String invalidPassword = "minos@12";
        loginPage.login(invalidUser, invalidPassword);
        //Verify error 'Your email or password is incorrect!' is visible
        String errorMsge = loginPage.getInvalidLoginErrorMsgeText();
        Assert.assertEquals(errorMsge, "Your email or password is incorrect!");
    }

    @Test
    public void logout() {
      validLogin("scott@gmail.com","scot@123");
        //Click 'Logout' button
        homePage.userLogout();
        //Verify that user is navigated to login page
        String currentURL = driver.getCurrentUrl();
        String loginURL = "https://automationexercise.com/login";
        Assert.assertEquals(currentURL, loginURL);
    }

    @DataProvider
    public Object[][] getLoginData() throws IOException {
    List<HashMap<String, Object>> data = getJsonDataToMap("//src//test//java//data//loginData.json");
    return new Object[][] {{data.get(0)}};
    }


}
