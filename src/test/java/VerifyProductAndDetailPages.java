import TestComponents.*;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class VerifyProductAndDetailPages extends BaseTest {

    @Test
    public void verifyTestCasesPage()
    {
        //Click on 'Test Cases' button
        homePage.clickTestCases();
        //Verify user is navigated to test cases page successfully
        String testCasesPageURL = "https://automationexercise.com/test_cases";
        Assert.assertEquals(driver.getCurrentUrl(),testCasesPageURL);
    }

    @Test
    public void verifyAllProdsAndProdDetails() {
        //Go to products page
        Products products = homePage.goToAllProducts();
        //Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertEquals(products.getAllProductsText(),"All Products".toUpperCase());
        //The products list is visible
        Assert.assertFalse(products.getProdList().isEmpty());
        //Click on 'View Product' of first product
        ProductsDetail prodDetail = products.viewFirstProd();
        //User is landed to product detail page
        String expectedURL="https://automationexercise.com/product_details/1";
        Assert.assertEquals(driver.getCurrentUrl(),expectedURL);
        // Verify that detail is visible: product name, category, price, availability, condition, brand
        Assert.assertTrue(prodDetail.getProdName().isDisplayed());
        Assert.assertTrue(prodDetail.getProdCategory().isDisplayed());
        Assert.assertTrue(prodDetail.getProdPrice().isDisplayed());
        Assert.assertTrue(prodDetail.getAvailability().isDisplayed());
        Assert.assertTrue(prodDetail.getCondition().isDisplayed());
        Assert.assertTrue(prodDetail.getBrand().isDisplayed());
    }
}
