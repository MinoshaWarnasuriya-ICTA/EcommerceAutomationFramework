import TestComponents.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class SearchProduct extends BaseTest {

    @Test
    public void searchForProducts()
    {
        String prodName = "Jeans";
        //Click on 'Products' button
        Products products = homePage.goToAllProducts();
        //Verify user is navigated to ALL PRODUCTS page successfully
        String prodPageUrl="https://automationexercise.com/products";
        Assert.assertEquals(driver.getCurrentUrl(),prodPageUrl);
        //Enter product name in search input and click search button
        products.searchProd(prodName);
        // Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertEquals(products.getSearchProdText(),"SEARCHED PRODUCTS");
        //Verify all the products related to search are visible
        Assert.assertTrue(products.checkVisibilityOfRelatedProds(prodName));

    }
}
