import TestComponents.*;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class ExistingUserReg extends BaseTest {

    @Test
    public void invalidRegistration()
    {
        //Click on 'Signup / Login' button
        LoginPage loginPage = homePage.goToLoginPage();

        //Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.getSignUpTxt().isDisplayed());

        //Enter name and already registered email address
        loginPage.enterName("Scott");
        loginPage.enterSignupEmail("scott@gmail.com");

        //Click signup button
        SignUpPage signUpPage = loginPage.clickSignUp();

        //Verify error 'Email Address already exist!' is visible
        Assert.assertEquals(loginPage.getInvalidSignupErrorMsgeText(),"Email Address already exist!");

    }
}
