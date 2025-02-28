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
        int[] prodIndex = {0, 1};
        List prodIndexList = Arrays.asList(prodIndex);//convert prod indexes to an array list
        List<String> addedProds = products.addProdsToCartFromList(prodIndexList);
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
        String[] prodsToRemove ={"Winter Top"};
        cartPage.removeProds(prodsToRemove);
        //Verify that product is removed from the cart
        Assert.assertTrue(cartPage.verifyRemovalOfProd(prodsToRemove));

    }

}
