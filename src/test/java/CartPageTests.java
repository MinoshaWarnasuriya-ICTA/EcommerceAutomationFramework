import TestComponents.*;
import com.google.common.base.*;
import org.openqa.selenium.interactions.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

import java.util.*;

public class CartPageTests extends BaseTest {

    @Test
    public void addProdsToCart() {
        String quantity = "1";
        //Go to products page
        Products products = homePage.goToAllProducts();
        //Hover over first and second products,click 'Add to cart' and Click 'Continue Shopping' button
        int[] prodIndex = {0,1};
        //List prodIndexList = Arrays.asList(prodIndex);//convert prod indexes to an array list
        List<String> addedProds = products.addProdsToCartFromList(prodIndex);
        //get prices of added products to a list
        List<String> priceList = products.getProdPrice(prodIndex);
        // Click 'View Cart' button
        CartPage cartPage = products.goToCartPage();
        //Verify both products are added to Cart
        Assert.assertTrue(cartPage.verifyCartProdNames(addedProds));
        //Verify their prices, quantity and total price
        Assert.assertTrue(cartPage.verifyPrice(priceList));
        Assert.assertTrue(cartPage.verifyQuantity(quantity));
        cartPage.verifyTotalPrice(priceList);

    }


    @Test
    public void verifyProdQuantityInCart() {
        String productName = "Fancy Green Top";
        String quantity = "4";
        //Go to products page
        Products products = homePage.goToAllProducts();
        //Click 'View Product' for any product on home page
        ProductsDetail productsDetail = products.goToProdDetailPage(productName);
        //Verify product detail page is opened
        Assert.assertEquals(driver.getTitle(), "Automation Exercise - Product Details");
        //Increase quantity to 4
        productsDetail.setQuantity(quantity);
        //Click 'Add to cart' button
        productsDetail.clickAddToCart();
        //Click 'View Cart' button
        CartPage cartPage = productsDetail.goToCartPage();
        //Verify that product is displayed in cart page with exact quantity
        Assert.assertTrue(cartPage.verifyCartProdByName(productName));
        Assert.assertTrue(cartPage.verifyQuantity(quantity));
    }

    @Test(retryAnalyzer = Retry.class)
    public void removeProductsFromCart()
    {
        //Go to products page
        Products products = homePage.goToAllProducts();
        //Add Products to cart
        String[] productList = {"Winter Top","Frozen Tops For Kids","Green Side Placket Detail T-Shirt"};
        products.addProdListToCart(productList);
        //Click 'Cart' button
        CartPage cartPage = products.goToCartPage();
        //Verify that cart page is displayed
        Assert.assertEquals(cartPage.getCartPageDisplayText(), "Shopping Cart");
        //Click 'X' button corresponding to particular product
        String prodToRemove ="Winter Top";
        cartPage.removeProds(prodToRemove);
        //Verify that product is removed from the cart
        Assert.assertTrue(cartPage.verifyRemovalOfProd(prodToRemove));

    }

    @Test
    public void verifyCartProdsAfterLogin() throws InterruptedException {
        //Click on 'Products' button
        Products products = homePage.goToAllProducts();
        // Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertEquals(products.getAllProductsText(),"All Products".toUpperCase());
        //Enter product name in search input and click search button
       String productName ="shirt";
        products.searchProd(productName);
        //Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertEquals(products.getSearchProdText(),"SEARCHED PRODUCTS");
        //Verify all the products related to search are visible
        Assert.assertTrue(products.checkVisibilityOfRelatedProds(productName));
        //Add those products to cart
       List<String> addedProdList =products.addAllSearchedProdsToCart();
        System.out.println(addedProdList);
       // Click 'Cart' button and verify that products are visible in cart
        CartPage cartPage = products.goToCartPage();
        Assert.assertTrue(cartPage.verifyCartProdNames(addedProdList));
        // Click 'Signup / Login' button and submit login details
        LoginPage loginPage = cartPage.goToLoginPage();
        loginPage.login("rajithaw@yahoo.com","rajitha@123");
        //Again, go to Cart page
        products.goToCartPage();
        //Verify that those products are visible in cart after login as well
        Assert.assertTrue(cartPage.verifyCartProdNames(addedProdList));

    }

    @Test
    public void addToCartFromRecommendedItems() throws InterruptedException {
        //Scroll to bottom of page
        homePage.scrollToRecommendedItems();
        //Verify 'RECOMMENDED ITEMS' are visible
        Assert.assertEquals(homePage.getRecommendedItemsText(),"recommended items".toUpperCase());
        String recommendedProduct = "Men Tshirt";
        Thread.sleep(7000);
        //Click on 'Add To Cart' on Recommended product
        homePage.addRecommendedProdToCart(recommendedProduct);
        // view cart
        CartPage cartPage =homePage.clickViewCartLink();
        //Verify that product is displayed in cart page
        Assert.assertTrue(cartPage.verifyCartProdByName(recommendedProduct));


    }
}
