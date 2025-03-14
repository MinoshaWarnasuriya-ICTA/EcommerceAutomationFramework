import TestComponents.*;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class RegisterUserFrmwrk extends BaseTest {
    @Test
    public void registerUserWithFrameWork() {

        String userName = "Scott";
        String password = "scot@123";
        // Launch browser and navigate to application
        LoginPage loginPage = homePage.goToLoginPage();
        //Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.getSignUpTxt().isDisplayed());
        //Enter name and email address
        loginPage.enterName(userName);
        loginPage.enterSignupEmail("scott@yahoo.com");
        //go to sign up page
        SignUpPage signUpPage = loginPage.clickSignUp();

        //Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertEquals(signUpPage.getAccInfoTxt().getText(),"ENTER ACCOUNT INFORMATION");
        //Fill details: Title, Name, Email, Password, Date of birth
        signUpPage.selectTitle("Mr");
        signUpPage.clearAndEnterName(userName);
        signUpPage.enterPassword(password);
        String date = "17";
        String month = "December";
        String year = "1998";
        signUpPage.enterDOB(date, month, year);
        signUpPage.selectNewsletterCheckbox();
        signUpPage.selectPartnerCheckbox();
        //Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        String firstName = "Scott";
        String lastName = "Smith";
        String company = "IFS";
        String address1 = "No:365";
        String address2 = "Colombo";
        String country = "New Zealand";
        String state = "Florida";
        String city = "Alberta";
        String zipcode = "0023";
        String mobile = "01234567";
        signUpPage.enterAddressInfo(firstName, lastName, company, address1, address2, country, state, city, zipcode, mobile);
        AccountCreated accountCreated = signUpPage.clickCreateAccBtn();

        //Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertEquals(accountCreated.getAccCreationTxt(),"ACCOUNT CREATED!");
        accountCreated.clickContinue();

        //Verify that 'Logged in as username' is visible
        String welcomMsge = "Logged in as ";
        Assert.assertEquals(homePage.getWelcomeTxt(), welcomMsge + firstName);


//        //Click 'Delete Account' button
//        DeleteAccount deleteAccount = homePage.deleteAcc();
//
//
//        //Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
//        Assert.assertEquals(deleteAccount.getAccDeletionTxt(),"ACCOUNT DELETED!");


    }
}
