package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) 
	{  
	   String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	   repName="TestReport-"+timeStamp+".html";
	   sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
	   sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
	   sparkReporter.config().setReportName("OpenCart Functional Testing");
	   sparkReporter.config().setTheme(Theme.DARK);
	   extent=new ExtentReports();
	   extent.attachReporter(sparkReporter);
	   extent.setSystemInfo("Applicaion", "OpenCart");
	   extent.setSystemInfo("Module", "Admin");
	   extent.setSystemInfo("SubModule", "Customer");
	   extent.setSystemInfo("User Name", System.getProperty("user.name"));
	   extent.setSystemInfo("Environment", "QA");
	   
	   String os=context.getCurrentXmlTest().getParameter("os");
	   extent.setSystemInfo("Operating System", os);
	   String browser=context.getCurrentXmlTest().getParameter("browser");
	   extent.setSystemInfo("Browser", browser);
	   
	   List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
	   if(!includedGroups.isEmpty())
	   {
		   extent.setSystemInfo("Groups", includedGroups.toString());
	   }  
	}
	
	public void onTestSuccess(ITestResult result) 
	{
	    test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.PASS, result.getName()+"got successfully Executed");
	    
    }
	public void onTestFailure(ITestResult result) 
	{
	     test=extent.createTest(result.getName());
	     test.assignCategory(result.getMethod().getGroups());
	     test.log(Status.FAIL, result.getName()+" got failed");
	     test.log(Status.INFO, result.getThrowable().getMessage());
	     try
	     {
	    	String imgpath= new BaseClass().captureScreen(result.getName());
	    	test.addScreenCaptureFromPath(imgpath);
	     }
	     catch(Exception e)
	     {
	    	e.printStackTrace(); 
	     }
    }
	public void onTestSkipped(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.SKIP, result.getName()+"got skipped");
	    test.log(Status.INFO, result.getThrowable());
	}
	public void onFinish(ITestContext context) 
	{
	    extent.flush();
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String pathOfExtentReport=System.getProperty("user.dir"+"\\reports\\"+repName);
	    File extentReport=new File(pathOfExtentReport);
	    try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
