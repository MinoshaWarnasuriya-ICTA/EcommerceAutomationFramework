import TestComponents.*;
import org.slf4j.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class CheckoutPageTest extends BaseTest {

    //private static final Logger log = LoggerFactory.getLogger(CheckoutPageTest.class);

    @Test
    public void verifyAddressDetailsInCheckout()
    {
        String firstName ="Kiara";
        String email ="kia@gmail.com";
        String password ="kia@123";
        String title ="Mrs";
        String lastName ="Lee";
        String company="Uber";
        String address1 ="No:90";
        String address2 ="Baker Street";
        String country="Israel";
        String state = "Central District";
        String zipcode="009";
        String mobile="0345768";
        String city="Jerusalem";
        //Click 'Signup / Login' button
        LoginPage loginPage =homePage.goToLoginPage();
        //Fill all details in Signup and create account
        SignUpPage signUpPage =loginPage.fillNewUserSignUp(firstName,email);
        AccountCreated accCreated = signUpPage.createNewAcc(title,password,"23","April","2001",firstName,
                lastName,company,address1,address2,country,state,city,
        zipcode,mobile);
        //Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertEquals(accCreated.getAccCreationTxt(),"ACCOUNT CREATED!");
        accCreated.clickContinue();
        //Verify ' Logged in as username' at top
       Assert.assertEquals(accCreated.getLoggedInUserTxt(),"Logged in as "+firstName);
       //Add products to cart
        Products products = homePage.goToAllProducts();
        String[] prodList={"Men Tshirt","Winter Top"};
        products.addProdListToCart(prodList);
        //Click 'Cart' button
        CartPage cartPage = homePage.goToCartPage();
        //Verify that cart page is displayed
        Assert.assertEquals(cartPage.getCartPageDisplayText(),"Shopping Cart");
        //Click Proceed To Checkout
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        //Verify that the billing address is same address filled at the time registration of account
        Assert.assertTrue(checkoutPage.verifyBillingAddressDetails(title,firstName,lastName,company,address1,address2,
                city,state,zipcode,country,mobile));
        //Verify that the delivery address is same address filled at the time registration of account
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressDetails(title,firstName,lastName,company,address1,address2,
                city,state,zipcode,country,mobile));

        //Click 'Delete Account' button
        DeleteAccount deleteAccount =homePage.deleteAcc();
        //Verify 'ACCOUNT DELETED!' and click 'Continue' button
       Assert.assertEquals(deleteAccount.getAccDeletionTxt(),"ACCOUNT DELETED!");
       deleteAccount.clickContinue();



    }
}
