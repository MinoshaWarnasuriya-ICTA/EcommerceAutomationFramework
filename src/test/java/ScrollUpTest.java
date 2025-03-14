import TestComponents.*;
import org.testng.*;
import org.testng.annotations.*;

public class ScrollUpTest extends BaseTest {

    @Test
    public void scrollUpWithArrowBtn()
    {
        //Scroll down page to bottom
            homePage.scrollToElement(homePage.getFooterElement());
            // Verify 'SUBSCRIPTION' is visible
        Assert.assertEquals(homePage.getSubscriptionTxt(),"Subscription".toUpperCase());
        // Click on arrow at bottom right side to move upward
        homePage.clickUpArrow();
        //Verify that page is scrolled up and
        //'Full-Fledged practice website for Automation Engineers' text is visible on screen
        homePage.waitForVisibilityOfElement(homePage.carouselText);
        Assert.assertEquals(homePage.getCarouselText(),"Full-Fledged practice website for Automation Engineers");
    }

    @Test
    public void scrollUpWithoutArrowBtn()
    {
        //Scroll down page to bottom
        homePage.scrollToElement(homePage.getFooterElement());
        // Verify 'SUBSCRIPTION' is visible
        Assert.assertEquals(homePage.getSubscriptionTxt(),"Subscription".toUpperCase());
        // Scroll up page to top
        homePage.scrollToElement(homePage.getHeaderSection());
        //Verify that page is scrolled up and
        //'Full-Fledged practice website for Automation Engineers' text is visible on screen
        homePage.waitForVisibilityOfElement(homePage.carouselText);
        Assert.assertEquals(homePage.getCarouselText(),"Full-Fledged practice website for Automation Engineers");

    }
}
