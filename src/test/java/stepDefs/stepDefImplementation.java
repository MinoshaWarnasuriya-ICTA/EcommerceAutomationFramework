package stepDefs;

import TestComponents.*;
import com.aventstack.extentreports.gherkin.model.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.*;
import pageObjects.*;

import java.io.*;
import java.util.*;

public class stepDefImplementation extends BaseTest {
    LoginPage loginPage;
    SignUpPage signUpPage;
    AccountCreated accountCreated;
    DeleteAccount deleteAccount;
    ContactUs contactUs;
    Products products;
    ProductsDetail prodDetail;
    CartPage cartPage;
    List<String> addedProds;
    List<String> priceList;
    ProductsDetail productsDetail;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    PaymentDonePage paymentDonePage;
    SareeCategoryPage sareePage;
    TopsAndShirts topsAndShirts;
    BrandPage brandPage;
    List<String> addedProdList;


    @Given("user is on landing page")
    public void user_is_on_landing_page() throws IOException {
        launchApplication();
    }

    @Given("user is on login page")
    public void user_is_on_login_page() {
        loginPage = homePage.goToLoginPage();
        //Verify 'Login to your account' is visible
        Assert.assertEquals(loginPage.getLoginToAccMsgeTxt(), "Login to your account");
    }

    @When("^user login with (.+) and (.+)$")
    public void user_login_with_email_and_password(String email, String password) {
        // Click 'Signup / Login' button and submit login details
        loginPage = cartPage.goToLoginPage();
        loginPage.login(email, password);
    }


    @Then("^Logged in as (.+) is visible$")
    public void logged_in_as_username_is_visible(String username) {
        //Verify that 'Logged in as username' is visible
        String actualLoggedInUserText = loginPage.getLoggedInUserTxt();
        String expectedTxt = "Logged in as " + username;
        Assert.assertEquals(actualLoggedInUserText, expectedTxt);
        driver.close();
    }

    @Then("{string} message is displayed")
    public void errorMessageIsDisplayed(String string) {
        //Verify error 'Your email or password is incorrect!' is visible
        String errorMsge = loginPage.getInvalidLoginErrorMsgeText();
        Assert.assertEquals(errorMsge, string);
        driver.close();

    }

    @Given("^user is logged in with (.+) and (.+)$")
    public void userIsLoggedInWithEmailAndPassword(String email, String password) {
        loginPage = homePage.goToLoginPage();
        //Verify 'Login to your account' is visible
        Assert.assertEquals(loginPage.getLoginToAccMsgeTxt(), "Login to your account");
        //Enter correct email address and password
        loginPage.login(email, password);

    }

    @When("user clicks on logout button")
    public void userClicksOnLogoutButton() {
        //Click 'Logout' button
        homePage.userLogout();
    }

    @Then("login page is displayed")
    public void loginPageIsDisplayed() {
        //Verify that user is navigated to login page
        String currentURL = driver.getCurrentUrl();
        String loginURL = "https://automationexercise.com/login";
        Assert.assertEquals(currentURL, loginURL);
        driver.close();

    }


    @When("^user enter sign up (.+) and (.+)$")
    public void userEnterSignUpNameAndEmail(String name, String password) {
        //Enter name and email address
        loginPage.enterName(name);
        loginPage.enterSignupEmail(password);

    }

    @And("user click on sign up button")
    public void userClickOnSignUpButton() {
        //go to sign up page
        signUpPage = loginPage.clickSignUp();
    }


    @Then("ENTER ACCOUNT INFORMATION is displayed")
    public void EnterAccInfoisDisplayed() {
        //Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertEquals(signUpPage.getAccInfoTxt().getText(), "ENTER ACCOUNT INFORMATION");
    }

    @When("^user fill account information with (.+),(.+),(.+),(.+),(.+)$")
    public void userFillAccountInformationWithTitlePasswordDateMonthYear(String title, String password, String date, String month, String year) {
        //Fill details: Title, Name, Email, Password, Date of birth
        signUpPage.selectTitle(title);
        signUpPage.enterPassword(password);
        signUpPage.enterDOB(date, month, year);
    }

    @When("^user fill address information with (.+),(.+),(.+),(.+),(.+),(.+),(.+),(.+),(.+),(.+)$")
    public void userFillAddressInformation(String firstName, String lastName, String company, String address1, String address2, String country, String state, String city, String zipcode, String mobile) {
        signUpPage.enterAddressInfo(firstName, lastName, company, address1, address2, country, state, city, zipcode, mobile);
    }


    @And("user click on create account button")
    public void userClickOnCreateAccountButton() {
        accountCreated = signUpPage.clickCreateAccBtn();
    }

    @Then("{string} is visible")
    public void enterAccInfoisVisible(String string) {
        //Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertEquals(signUpPage.getAccInfoTxt().getText(), "ENTER ACCOUNT INFORMATION");

    }

    @When("user clicks on continue button")
    public void userClicksOnContinueButton() {
        accountCreated.clickContinue();
    }


    @Then("^welcome text with (.+) is visible$")
    public void welcomeTextWithUsernameIsVisible(String firstName) {
        //Verify that 'Logged in as username' is visible
        String welcomMsge = "Logged in as ";
        Assert.assertEquals(homePage.getWelcomeTxt(), welcomMsge + firstName);
    }

    @When("user click on delete account")
    public void userClickOnDeleteAccount() {
        //Click 'Delete Account' button
        deleteAccount = homePage.deleteAcc();

    }

    @Then("{string} text is visible")
    public void accCreatedtextIsVisible(String string) {
        //Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertEquals(accountCreated.getAccCreationTxt(), "ACCOUNT CREATED!");

    }

    @Then("ACCOUNT DELETED! text is displayed")
    public void accountDELETEDTextIsDisplayed() {
        //Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        Assert.assertEquals(deleteAccount.getAccDeletionTxt(), "ACCOUNT DELETED!");
        driver.close();
    }

    @Then("{string} error message is displayed")
    public void existingUserErrorMessageIsDisplayed(String string) {
        //Verify error 'Email Address already exist!' is visible
        Assert.assertEquals(loginPage.getInvalidSignupErrorMsgeText(), string);
        tearDown();
    }

    @When("user click on Contact Us button")
    public void userClickOnContactUsButton() {
        //Click on 'Contact Us' button
        contactUs = homePage.clickContactUs();
    }

    @Then("Get In Touch is visible")
    public void getInTouchIsVisible() {
        //Verify 'GET IN TOUCH' is visible
        Assert.assertEquals(contactUs.getGetInTouchText().getText(), "GET IN TOUCH");


    }

    @When("^user enter contact us details (.+),(.+),(.+),(.+)$")
    public void userEnterContactUsDetailsNameEmailSubjectMessage(String name, String email, String subject, String message) {
        contactUs.sendName(name);
        contactUs.sendEmail(email);
        contactUs.enterSubject(subject);
        contactUs.enterMsge(message);

    }

    @And("^user upload (.+)$")
    public void userUploadFile(String filePath) {
        // Upload file
        contactUs.uploadFile(filePath);
    }

    @And("user click submit button")
    public void userClickSubmitButton() {
        //Click 'Submit' button
        contactUs.clickSubmit();
    }

    @And("user accept alert")
    public void userAcceptAlert() {
        //accept alert
        contactUs.acceptAlert();
    }

    @Then("success message is visible")
    public void successMessageIsVisible() {
        // Verify success message 'Success! Your details have been submitted successfully.' is visible
        String expectedSuccessMsge = "Success! Your details have been submitted successfully.";
        Assert.assertEquals(contactUs.getSuccessMsgeText(), expectedSuccessMsge);
        tearDown();
    }

    @When("user click on Test Cases button")
    public void userClickOnTestCasesButton() {
        //Click on 'Test Cases' button
        homePage.clickTestCases();
    }

    @Then("test cases page is displayed")
    public void testCasesPageIsDisplayed() {
        //Verify user is navigated to test cases page successfully
        String testCasesPageURL = "https://automationexercise.com/test_cases";
        Assert.assertEquals(driver.getCurrentUrl(), testCasesPageURL);
        tearDown();
    }

    @When("user click on Products button")
    public void userClickOnProductsButton() {
        //Go to products page
        products = homePage.goToAllProducts();
    }

    @Then("all products page is displayed")
    public void allProductsPageIsDisplayed() {
        //Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertEquals(products.getAllProductsText(), "All Products".toUpperCase());
    }

    @And("product list is visible")
    public void productListIsVisible() {
        //The products list is visible
        Assert.assertFalse(products.getProdList().isEmpty());
    }


    @When("user click on View Product of first product")
    public void userClickOnViewProductOfFirstProduct() {
        //Click on 'View Product' of first product
        prodDetail = products.viewFirstProd();
    }

    @Then("Product detail Page is visible")
    public void productDetailPageIsVisible() {
        //Verify product detail page is opened
        Assert.assertEquals(driver.getTitle(), "Automation Exercise - Product Details");
    }

    @And("^(.+), (.+), (.+), (.+), (.+), (.+) are visible$")
    public void productNameCategoryPriceAvailabilityConditionBrandAreVisible(String productName, String category, String price, String availability, String condition, String brand) {
        // Verify that detail is visible: product name, category, price, availability, condition, brand
        Assert.assertEquals(prodDetail.getProdName(), productName);
        Assert.assertEquals(prodDetail.getProdCategory(), category);
        Assert.assertEquals(prodDetail.getProdPrice(), price);
        Assert.assertEquals(prodDetail.getAvailability(), availability);
        Assert.assertEquals(prodDetail.getCondition(), condition);
        Assert.assertEquals(prodDetail.getBrand(), brand);
        tearDown();
    }

    @When("user enter {string} on search box and click search button")
    public void userEnterProduct_nameOnSearchBoxAndClickSearchButton(String searchProd) {
        //Enter product name in search input and click search button
        products.searchProd(searchProd);
    }

    @Then("{string} text is displayed")
    public void textIsDisplayed(String string) {
        // Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertEquals(products.getSearchProdText(), string);
    }

    @And("all the products with {string} are visible")
    public void allTheProductsWithProdNameAreVisible(String prodName) {
        //Verify all the products related to search are visible
        Assert.assertTrue(products.checkVisibilityOfRelatedProds(prodName));
    }

    @When("user scroll down to footer")
    public void userScrollDownToFooter() {
        //Scroll down to footer
        homePage.scrollToElement(homePage.getFooterElement());
    }


    @Then("Subscription text is displayed")
    public void subscriptionTextIsDisplayed() {
        //Verify text 'SUBSCRIPTION'
        Assert.assertEquals(homePage.getSubscriptionTxt(), "Subscription".toUpperCase());
    }

    @When("user enter {string} and click arrow button")
    public void userEnterEmailAndClickArrowButton(String email) {
        //Enter email address in input and click arrow button
        homePage.subscribe(email);
    }

    @Then("{string} is displayed on footer")
    public void isDisplayedOnFooter(String successMsge) {
        //Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertEquals(homePage.getSuccessSubscribeMsgeText(), successMsge);
        tearDown();

    }

    @Given("user is on Cart Page")
    public void userIsOnCartPage() {
        //user click on cart header button
        cartPage = homePage.goToCartPage();
    }

    @When("user add first and second product to cart")
    public void userAddFirstAndSecondProductToCart() {
        int[] prodIndex = {0, 1};
        //List prodIndexList = Arrays.asList(prodIndex);//convert prod indexes to an array list
        addedProds = products.addProdsToCartFromList(prodIndex);
        //get prices of added products to a list
        priceList = products.getProdPrice(prodIndex);
    }

    @And("user click on cart icon")
    public void userClickOnCartIcon() {
        // Click 'View Cart' button
        cartPage = products.goToCartPage();
    }

    @Then("first and second products are displayed in the cart")
    public void firstAndSecondProductsAreDisplayedInTheCart() {
        //Verify both products are added to Cart
        Assert.assertTrue(cartPage.verifyCartProdNames(addedProds));
    }

    @And("their prices, quantity and total price")
    public void theirPricesQuantityAndTotalPrice() {
        String quantity = "1";
        //Verify their prices, quantity and total price
        Assert.assertTrue(cartPage.verifyPrice(priceList));
        Assert.assertTrue(cartPage.verifyQuantity(quantity));
        cartPage.verifyTotalPrice(priceList);
        tearDown();

    }

    @When("user click on View Product of {string}")
    public void userClickOnViewProductOfProduct(String product) {
        //Click 'View Product' for any product on home page
        productsDetail = products.goToProdDetailPage(product);

    }

    @When("user increase quantity by {string}")
    public void userIncreaseQuantityBy(String quantity) {
        //Increase quantity to 4
        productsDetail.setQuantity(quantity);
    }

    @And("user add product to cart")
    public void userAddProductToCart() {
        //Click 'Add to cart' button
        productsDetail.clickAddToCart();
    }


    @Then("{string} is displayed in cart page with exact {string}")
    public void productIsDisplayedInCartPageWithExactQuantity(String product, String quantity) {
        //Verify that product is displayed in cart page with exact quantity
        Assert.assertTrue(cartPage.verifyCartProdByName(product));
        Assert.assertTrue(cartPage.verifyQuantity(quantity));
        tearDown();
    }


    @When("user add {string},{string},{string} to cart")
    public void userAddToCart(String prod1, String prod2, String prod3) {
        String[] prods = {prod1, prod2, prod3};
        products.addProdListToCart(prods);

    }

    @Then("cart page is displayed")
    public void cartPageIsDisplayed() {
        //Verify that cart page is displayed
        Assert.assertEquals(cartPage.getCartPageDisplayText(), "Shopping Cart");
    }


    @When("user click proceed to checkout and click register or login")
    public void userClickProceedToCheckoutAndClickRegisterLogin() {
        //Click Proceed To Checkout and Click 'Register / Login' button
         loginPage = cartPage.clickCheckoutToLoginOrSignUp();

    }

    @And("click proceed to checkout")
    public void clickProceedToCheckout() {
        //Click 'Proceed To Checkout' button
        checkoutPage = cartPage.goToCheckoutPage();
    }


    @And("user enter message and click on place order button")
    public void userEnterMessageAndClickOnPlaceOrderButton() {
        //Enter description in comment text area and click 'Place Order'
        checkoutPage.enterMsge("Please deliver with care");
        paymentPage = checkoutPage.clickPlaceOrderBtn();

    }

    @And("user enter payment details and click confirm order button")
    public void userEnterPaymentDetailsAndClickConfirmOrderButton() throws InterruptedException {
        //Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.fillPaymentDetails("R Fernando", "200878785678", "223", "09", "26");
        //Click 'Pay and Confirm Order' button
        paymentDonePage = paymentPage.clickPlaceOrderBtnForPageTrans();

    }

    @Then("{string} is displayed")
    public void successMessageIsDisplayed(String success_msge) throws InterruptedException {
        Assert.assertEquals(paymentPage.getSuccessMsge(), success_msge);

    }


    @When("user click on proceed to checkout")
    public void userClickOnProceedToCheckout() {
        //Click 'Proceed To Checkout' button
        checkoutPage = cartPage.goToCheckoutPage();
    }

    @When("user add {string} to cart")
    public void userAddToCart(String prod) {
        //Add products to cart
        products.addProdToCartByName(prod);

    }

    @Given("user is on products page")
    public void userIsOnProductsPage() {
        products = homePage.goToAllProducts();
    }

    @When("user remove {string} from the cart")
    public void userRemoveProductFromTheCart(String removeProd) {
        //Click 'X' button corresponding to particular product
        String prodToRemove = removeProd;
        cartPage.removeProds(prodToRemove);
    }

    @Then("{string} is not visible in the cart")
    public void productIsNotVisibleInTheCart(String trashProd) {
        //Verify that product is removed from the cart
        Assert.assertTrue(cartPage.verifyRemovalOfProd(trashProd));
    }


    @When("user click on women category on left side bar")
    public void userClickOnWomenCategoryOnLeftSideBar() {
        //Verify that categories are visible on left side bar
        Assert.assertEquals(homePage.getCategoryTitle(), "Category".toUpperCase());
        //Click on 'Women' category
        homePage.clickWomenCategory();
    }

    @And("user click on {string} under the {string}")
    public void userClickOn(String subCategory, String category) {
        if (category.equalsIgnoreCase("Women")) {
            if (subCategory.equalsIgnoreCase("Dress")) {
                homePage.selectWomenDress();

            } else if (subCategory.equalsIgnoreCase("Tops")) {
                homePage.selectWomenTops();
            } else {
                homePage.selectSareeCategory();
            }
        } else if (category.equalsIgnoreCase("Men")) {
            if (subCategory.equalsIgnoreCase("TShirt")) {
                homePage.selectMenTShirt();
            } else {
                homePage.selectMenJeans();
            }
        } else {
            if (subCategory.equalsIgnoreCase("Dress")) {
                homePage.selectKisdDress();

            } else {
                homePage.clickKidsTopsAndShirts();
                topsAndShirts = new TopsAndShirts(driver);
            }

        }
    }


    @Then("category page is displayed with {string}")
    public void categoryPageIsDisplayedWith(String title) {
        //Verify that category page is displayed
        Assert.assertEquals(topsAndShirts.getTitle(), title);

    }

    @When("user click on {string} on left side bar")
    public void userClickOnOnLeftSideBar(String category) {
        homePage.viewMainCategory(category);

    }

    @Then("brands are visible on the left side bar")
    public void brandsAreVisibleOnTheLeftSideBar() {
        //Verify that Brands are visible on left side bar
        Assert.assertEquals(products.getBrandsTitle(), "Brands".toUpperCase());
    }

    @When("user select a {string}")
    public void userSelectA(String brand) {
        brandPage = products.goToBrandPage(brand);
    }

    @Then("{string} and brand products are displayed")
    public void brandAndProductsAreDisplayed(String brand_title) {
        //Verify that user is navigated to brand page and brand products are displayed
        Assert.assertTrue(brandPage.getBrandPageTitle().contains(brand_title.toUpperCase()));
        Assert.assertFalse(brandPage.getBrandProdList().isEmpty());
    }

    @When("user add all search products to cart")
    public void userAddAllSearchProductsToCart() throws InterruptedException {
        //Add those products to cart
        addedProdList = products.addAllSearchedProdsToCart();
        System.out.println(addedProdList);
    }

    @Then("all added products are available in the cart")
    public void allAddedProductsAreAvailableInTheCart() {
        // Click 'Cart' button and verify that products are visible in cart
        Assert.assertTrue(cartPage.verifyCartProdNames(addedProdList));

    }

    @When("user click on View Product of a {string}")
    public void userClickOnViewProductOfA(String prodName) {
        productsDetail = products.viewSelectedProd(prodName);
    }


    @Then("Write Your Review is visible")
    public void writeYourReviewIsVisible() {
        // Verify 'Write Your Review' is visible
        Assert.assertEquals(productsDetail.getWriteReviewTitleText(), "Write Your Review".toUpperCase());

    }

    @When("user enter {string},{string} and {string} and click submit")
    public void userEnterAnd(String name, String email, String review) {
        productsDetail.addReview(name, email, review);
    }

    @Then("Thank you for your review message is displayed")
    public void thankYouForYourReviewMessageIsDisplayed() {
        //Verify success message 'Thank you for your review.'
        Assert.assertEquals(productsDetail.getSuccessAlertMsgeText(), "Thank you for your review.");
    }

    @Given("user scroll down to recommended items")
    public void userScrollDownToRecommendedItems() {
        //Scroll to bottom of page
        homePage.scrollToRecommendedItems();
    }

    @Then("recommended items are visible")
    public void recommendedItemsAreVisible() {
        //Verify 'RECOMMENDED ITEMS' are visible
        Assert.assertEquals(homePage.getRecommendedItemsText(), "recommended items".toUpperCase());
    }

    @When("user add the {string} to cart")
    public void userAddTheToCart(String recProd) throws InterruptedException {
        Thread.sleep(5000);
        //Click on 'Add To Cart' on Recommended product
        homePage.addRecommendedProdToCart(recProd);
    }

    @Then("{string} is displayed in the cart")
    public void isDisplayedInTheCart(String recProd) {
        //Verify that product is displayed in cart page
        Assert.assertTrue(cartPage.verifyCartProdByName(recProd));
        tearDown();

    }

    @And("user click on view cart link")
    public void userClickOnViewCartLink() {
        cartPage = homePage.clickViewCartLink();
    }

    @When("user scroll down page to bottom")
    public void userScrollDownPageToBottom() {
        //Scroll down page to bottom
        homePage.scrollToElement(homePage.getFooterElement());
    }


    @Then("subscription text is visible")
    public void subscriptionTextIsVisible() {
        // Verify 'SUBSCRIPTION' is visible
        Assert.assertEquals(homePage.getSubscriptionTxt(), "Subscription".toUpperCase());
    }

    @When("user click on up arrow button to move upward")
    public void userClickOnUpArrowButtonToMoveUpward() {
        // Click on arrow at bottom right side to move upward
        homePage.clickUpArrow();
    }

    @Then("page is scrolled up and {string} text is visible on screen")
    public void pageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen(String verifyText) {
        //Verify that page is scrolled up and
        //'Full-Fledged practice website for Automation Engineers' text is visible on screen
        homePage.waitForVisibilityOfElement(homePage.carouselText);
        Assert.assertEquals(homePage.getCarouselText(), verifyText);
        tearDown();
    }

    @When("user scroll up page to top")
    public void userScrollUpPageToTop() {
        // Scroll up page to top
        homePage.scrollToElement(homePage.getHeaderSection());
    }

    @And("user verify address and review order")
    public void userVerifyAddressAndReviewOrder() {

    }

    @And("user click on download invoice")
    public void userClickOnDownloadInvoice() throws InterruptedException {
        //Click 'Download Invoice' button and verify invoice is downloaded successfully.
        paymentDonePage.clickDownloadInvoiceBtn();
        Thread.sleep(3000);
    }

    @Then("invoice is downloaded successfully")
    public void invoiceIsDownloadedSuccessfully() {
        Assert.assertTrue(paymentDonePage.verifyInvoiceDownload("invoice"));
        tearDown();
    }
}
