import TestComponents.*;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class Subscription extends BaseTest {


    @Test
    public void verifySubscriptionInHomePage() {
        //Scroll down to footer
        homePage.scrollWindow(8000);
        //Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.getSubscriptionTxt(),"Subscription".toUpperCase());
        //Enter email address in input and click arrow button
        homePage.subscribe("devon@gmail.com");
        //Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertEquals(homePage.getSuccessSubscribeMsgeText(),"You have been successfully subscribed!");
    }

    @Test
    public void verifySubscriptionInCartPage()
    {
        CartPage cartPage =homePage.goToCartPage();
        //Scroll down to footer
     homePage.scrollWindow(8000);
        //Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.getSubscriptionTxt(),"Subscription".toUpperCase());
        //Enter email address in input and click arrow button
        homePage.subscribe("devon@gmail.com");
        //Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertEquals(homePage.getSuccessSubscribeMsgeText(),"You have been successfully subscribed!");



    }
}
