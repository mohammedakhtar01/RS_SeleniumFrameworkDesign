package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObjects();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //  to avoid concurrency issue during reporting , when Test are run in Parallel
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		test =extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //  to avoid concurrency issue during reporting , when Test are run in Parallel
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		//test.log(Status.PASS, "Test Passed");// Not Thread Safe
		extentTest.get().log(Status.PASS, "Test Passed"); // Thread Safe
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		//test.fail(result.getThrowable()); //Not Thread Safe
		extentTest.get().fail(result.getThrowable()); // Thread Safe
		
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // Not Thread Safe
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // Thread Safe
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extent.flush();
		
	}

		
	
}
