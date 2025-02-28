import TestComponents.*;
import lombok.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

import java.io.*;
import java.util.*;

public class PlaceOrder extends BaseTest {

    @Test(dataProvider = "getData")
    public void registerWhileCheckout(HashMap<String, String> data) throws InterruptedException {
        //Go to products page
        Products products = homePage.goToAllProducts();
        //Add products to cart
        products.addProdsToCartByName(data.get("products"));
        //View cart page
        CartPage cartPage = homePage.goToCartPage();
        //Verify that cart page is displayed
        Assert.assertEquals(cartPage.getCartPageDisplayText(), "Shopping Cart");
        //Click Proceed To Checkout, then Click 'Register / Login' button
        LoginPage loginPage = cartPage.clickCheckoutToLoginOrSignUp();
        //Fill new user signup details
        SignUpPage signUpPage = loginPage.fillNewUserSignUp(data.get("name"), data.get("email"));
        //Fill all details in Signup and create account
        AccountCreated accCreated = signUpPage.createNewAcc(data.get("title"), data.get("password"),
                data.get("date"), data.get("month"), data.get("year"),
                data.get("firstName"), data.get("lastName"), data.get("company"),
                data.get("address1"), data.get("address2"), data.get("country"), data.get("state"),
                data.get("city"), data.get("zipcode"), data.get("mobile"));
        //Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertEquals(accCreated.getAccCreationTxt(), "ACCOUNT CREATED!");
        accCreated.clickContinue();
        //Verify ' Logged in as username' at top
        Assert.assertEquals(accCreated.getWelcomeTxt(), "Logged in as " + data.get("firstName"));
        //Click 'Cart' button
        accCreated.goToCartPage();
        //Click 'Proceed To Checkout' button
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        //Verify Address Details and Review Your Order
        Assert.assertTrue(checkoutPage.verifyAddressDetails(data.get("title"), data.get("firstName"), data.get("lastName"),
                data.get("company"), data.get("address1"), data.get("address2"), data.get("city"), data.get("state"), data.get("zipcode"),
                data.get("country"), data.get("mobile")));
        checkoutPage.verifyCheckoutProdList(data.get("products"));
        //Enter description in comment text area and click 'Place Order'
        checkoutPage.enterMsge(data.get("message"));
        PaymentPage paymentPage = checkoutPage.clickPlaceOrderBtn();
        //Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.fillPaymentDetails(data.get("name_on_card"), data.get("card_no"), data.get("cvc"), data.get("expire_month"), data.get("expiry_year"));
        //Click 'Pay and Confirm Order' button
        PaymentDonePage paymentDonePage = paymentPage.clickPlaceOrderBtn();
        Thread.sleep(3000);
        //Assert.assertEquals(paymentPage.getSuccessMsge(),"Your order has been placed successfully!");
        //Click 'Delete Account' button
        DeleteAccount deleteAccount = paymentPage.deleteAcc();
        //Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertEquals(deleteAccount.getAccDeletionTxt(), "ACCOUNT DELETED!");
        deleteAccount.clickContinue();

    }

    @Test(dataProvider = "getData")
    public void registerBeforeCheckout(HashMap<String, String> data) {
        LoginPage loginPage = homePage.goToLoginPage();
        //Fill new user signup details
        SignUpPage signUpPage = loginPage.fillNewUserSignUp(data.get("name"), data.get("email"));
        //Fill all details in Signup and create account
        AccountCreated accCreated = signUpPage.createNewAcc(data.get("title"), data.get("password"),
                data.get("date"), data.get("month"), data.get("year"),
                data.get("firstName"), data.get("lastName"), data.get("company"),
                data.get("address1"), data.get("address2"), data.get("country"), data.get("state"),
                data.get("city"), data.get("zipcode"), data.get("mobile"));
        //Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertEquals(accCreated.getAccCreationTxt(), "ACCOUNT CREATED!");
        accCreated.clickContinue();
        //Verify ' Logged in as username' at top
        Assert.assertEquals(accCreated.getWelcomeTxt(), "Logged in as " + data.get("firstName"));
        //Go to products page
        Products products = homePage.goToAllProducts();
        //Add products to cart
        products.addProdsToCartByName(data.get("products"));
        //View cart page
        CartPage cartPage = homePage.goToCartPage();
        //Verify that cart page is displayed
        Assert.assertEquals(cartPage.getCartPageDisplayText(), "Shopping Cart");
        //Click 'Proceed To Checkout' button
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        //Verify Address Details and Review Your Order
        Assert.assertTrue(checkoutPage.verifyAddressDetails(data.get("title"), data.get("firstName"), data.get("lastName"),
                data.get("company"), data.get("address1"), data.get("address2"), data.get("city"), data.get("state"), data.get("zipcode"),
                data.get("country"), data.get("mobile")));
        checkoutPage.verifyCheckoutProdList(data.get("products"));
        //Enter description in comment text area and click 'Place Order'
        checkoutPage.enterMsge(data.get("message"));
        PaymentPage paymentPage = checkoutPage.clickPlaceOrderBtn();
        //Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.fillPaymentDetails(data.get("name_on_card"), data.get("card_no"), data.get("cvc"), data.get("expire_month"), data.get("expiry_year"));
        //Click 'Pay and Confirm Order' button
        PaymentDonePage paymentDonePage = paymentPage.clickPlaceOrderBtn();
        //Assert.assertEquals(paymentPage.getSuccessMsge(),"Your order has been placed successfully!");
        //Click 'Delete Account' button
        DeleteAccount deleteAccount = paymentPage.deleteAcc();
        //Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertEquals(deleteAccount.getAccDeletionTxt(), "ACCOUNT DELETED!");
        deleteAccount.clickContinue();


    }

    @Test(dataProvider = "getData",retryAnalyzer = Retry.class)
    public void loginBeforeCheckout(HashMap<String,String> data){
        Object name = "Scott";
        LoginPage loginPage = homePage.goToLoginPage();
        //Verify 'Login to your account' is visible
        Assert.assertEquals(loginPage.getLoginToAccMsgeTxt(), "Login to your account");
        //Enter correct email address and password
        loginPage.login("scott@gmail.com", "scot@123");
        //Verify that 'Logged in as username' is visible
        String actualLoggedInUserText = loginPage.getLoggedInUserTxt();
        String expectedTxt = "Logged in as " + name;
        Assert.assertEquals(actualLoggedInUserText, expectedTxt);
        // Add products to cart
        Products products =loginPage.goToAllProducts();
        products.addProdsToCartByName(data.get("products"));
        //Click 'Cart' button
        CartPage cartPage = products.goToCartPage();
        //Verify that cart page is displayed
        Assert.assertEquals(cartPage.getCartPageDisplayText(), "Shopping Cart");
        //Click 'Proceed To Checkout' button
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        //Verify Address Details and Review Your Order
//        Assert.assertTrue(checkoutPage.verifyAddressDetails("Mr", data.get("firstName"), data.get("lastName"),
//                data.get("company"), data.get("address1"), data.get("address2"), data.get("city"), data.get("state"), data.get("zipcode"),
//                data.get("country"), data.get("mobile")));
//        checkoutPage.verifyCheckoutProdList(data.get("products"));
        //Enter description in comment text area and click 'Place Order'
        checkoutPage.enterMsge(data.get("message"));
        PaymentPage paymentPage = checkoutPage.clickPlaceOrderBtn();
        //Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.fillPaymentDetails(data.get("name_on_card"), data.get("card_no"), data.get("cvc"), data.get("expire_month"), data.get("expiry_year"));
        //Click 'Pay and Confirm Order' button
        PaymentDonePage paymentDonePage = paymentPage.clickPlaceOrderBtn();
        //Assert.assertEquals(paymentPage.getSuccessMsge(),"Your order has been placed successfully!");
        //Click 'Delete Account' button
        DeleteAccount deleteAccount = paymentPage.deleteAcc();
        //Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertEquals(deleteAccount.getAccDeletionTxt(), "ACCOUNT DELETED!");
        deleteAccount.clickContinue();

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, Object>> data = getJsonDataToMap("//src//test//java//data//purchaseData.json");
        return new Object[][]{{data.get(0)}};
    }

//    @DataProvider
//    public Object[][] getLoginData() throws IOException {
//        List<HashMap<String, Object>> data = getJsonDataToMap("//src//test//java//data//loginData.json");
//        return new Object[][] {{data.get(0)}};
//        }

//        @DataProvider
//    public Object[][] combinedDataProvider() throws IOException {
//        Object[][] registerData =getData();
//            Object[][] loginData = getLoginData();
//
//            Object[][] combinedData = new Object[registerData.length+loginData.length][];
//            System.arraycopy(registerData, 0, combinedData, 0, registerData.length);
//            System.arraycopy(loginData, 0, combinedData, registerData.length, loginData.length);
//
//            return combinedData;
//        }

}
