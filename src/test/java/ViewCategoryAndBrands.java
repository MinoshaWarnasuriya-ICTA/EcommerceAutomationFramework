import TestComponents.*;
import org.testng.*;
import org.testng.annotations.*;
import pageObjects.*;

public class ViewCategoryAndBrands extends BaseTest {

    @Test
    public void viewWomenCategory() {
        //Verify that categories are visible on left side bar
        Assert.assertEquals(homePage.getCategoryTitle(),"Category".toUpperCase());
        //Click on 'Women' category
        homePage.clickWomenCategory();
        //click on saree category
        SareeCategoryPage sareePage = homePage.selectSareeCategory();
        //Verify that category page is displayed
        Assert.assertEquals(sareePage.getSareePageCatName(),"Women > Saree");
    }

    @Test
    public void viewMenCategory()
    {
        //Verify that categories are visible on left side bar
        Assert.assertEquals(homePage.getCategoryTitle(),"Category".toUpperCase());
        //Click on 'Men' category
        homePage.clickMenCategory();
        //click on TShirt category
        TShirtCategoryPage tShirtPage  =homePage.selectTshirtCategory();
        //Verify that category page is displayed
        Assert.assertEquals(tShirtPage.getTShirtCatPageTitle(),"Men - Tshirts Products".toUpperCase());

    }

    @Test
    public void viewBrandProducts()
    {
        //Click on 'Products' button
        Products products =homePage.goToAllProducts();
        //Verify that Brands are visible on left side bar
        Assert.assertEquals(products.getBrandsTitle(),"Brands".toUpperCase());
        //Click on any brand name
        String brand = "Madame";
        BrandPage brandPage = products.goToBrandPage( brand);
        //Verify that user is navigated to brand page and brand products are displayed
        Assert.assertTrue(brandPage.getBrandPageTitle().contains(brand.toUpperCase()));
        Assert.assertFalse(brandPage.getBrandProdList().isEmpty());


    }
}
