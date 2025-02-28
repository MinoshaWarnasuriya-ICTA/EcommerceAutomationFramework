import TestComponents.*;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class FillContactUsForm extends BaseTest {

    @Test
    public void fillContactUsForm() {

        //Click on 'Contact Us' button
        ContactUs contactUs = homePage.clickContactUs();
        //Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactUs.getGetInTouchText().isDisplayed());
        // Enter name, email, subject and message
        String name = "Kylie";
        String email = "kylieejenner@gmail.com";
        String subject = "Request for Quotation";
        String message = "Hi! I'd like to request for product quotation";
        contactUs.sendName(name);
        contactUs.sendEmail(email);
        contactUs.enterSubject(subject);
        contactUs.enterMsge(message);
        // Upload file
        String filePath = "F:/Udemy/API/students.json";
        contactUs.uploadFile(filePath);
        //Click 'Submit' button
        contactUs.clickSubmit();
        //accept alert
        contactUs.acceptAlert();
        // Verify success message 'Success! Your details have been submitted successfully.' is visible
        String expectedSuccessMsge = "Success! Your details have been submitted successfully.";
        Assert.assertEquals(contactUs.getSuccessMsgeText(),expectedSuccessMsge);
        //Click 'Home' button and verify that landed to home page successfully
        contactUs.clickHomeBtn();
        Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/");

    }

}
