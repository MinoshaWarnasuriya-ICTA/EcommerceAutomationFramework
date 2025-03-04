import TestComponents.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class ProductReview extends BaseTest {

    @Test
    public void addReviewToProduct()
    {
        // Click on 'Products' button
        Products products = homePage.goToAllProducts();
        //Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertEquals(products.getAllProductsText(),"All Products".toUpperCase());
        //Click on 'View Product' button
        String prodName = "Regular Fit Straight Jeans";
        ProductsDetail productsDetail =products.viewSelectedProd(prodName);
        // Verify 'Write Your Review' is visible
        Assert.assertEquals(productsDetail.getWriteReviewTitleText(),"Write Your Review".toUpperCase());
        // Enter name, email and review and click 'Submit' button
        String name = "Minosha";
        String email = "mds@yahoo.com";
        String review = "This product is awesome!";
        productsDetail.addReview(name,email,review);
        //Verify success message 'Thank you for your review.'
        Assert.assertEquals(productsDetail.getSuccessAlertMsgeText(),"Thank you for your review.");
    }
}
