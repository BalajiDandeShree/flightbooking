package BalajiSoft.data;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() {
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
		extentSparkReporter.config().setReportName("Web Automation Result");
		extentSparkReporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(extentSparkReporter);
	    extent.setSystemInfo("Tester", "Balaji Dande");	
	    
	    return extent;
	}

}
