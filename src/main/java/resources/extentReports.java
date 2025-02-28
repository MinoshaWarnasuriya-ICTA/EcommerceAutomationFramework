package resources;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;

public class extentReports {

    public static ExtentReports getExtentObject() {
        //ExtentSpaktReporter, ExtentReports

        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//report//index.html");
        reporter.config().setDocumentTitle("Automation Exercise Web Results");
        reporter.config().setDocumentTitle("Web Automation Test Result");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Engineer","Minosha Warnasuriya");
      return extent;


    }


}
