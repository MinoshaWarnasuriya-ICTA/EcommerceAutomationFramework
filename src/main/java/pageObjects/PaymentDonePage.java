package pageObjects;

import abstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.io.*;

public class PaymentDonePage extends AbstractComponent {
    WebDriver driver;
    public PaymentDonePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".check_out")
    WebElement downloadInvoiceBtn;

    @FindBy(css = ".btn-primary")
    WebElement continueBtn;

    public void clickDownloadInvoiceBtn()
    {
        downloadInvoiceBtn.click();
    }

    public boolean verifyInvoiceDownload(String fileName)
    {
        boolean flag = false;
        String dirPath = "C://Users//Minosha Warnasuriya//Downloads";
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files.length == 0 || files == null) {
            System.out.println("The directory is empty");
            flag = false;
        } else {
            for (File file : files) {
                if (file.getName().contains(fileName)) {
                    System.out.println(file.getName() + " is present");
                    break;
                }
                flag = true;

            }
        }
        return flag;
      }

public void clickContinueBtn()
{
    continueBtn.click();
}
}
