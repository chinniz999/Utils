package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

public class TestContext implements ITestListener {
	
	String testName;

	public void onTestStart(ITestResult result) {

		String testName2=result.getName();
		testName=testName2;
		
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Passed--->" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped--->" + result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		

	}

	public void onStart(ITestContext context) {
		
		XmlTest xmlTest=context.getCurrentXmlTest();
		List classes=xmlTest.getClasses();
		System.out.println(classes);
		
	}

	public void onFinish(ITestContext context) {
		File file=new File(System.getProperty("user.dir")+"\\test-output\\"+testName+".csv");
		PrintWriter printWriter = null;
		try {
			printWriter=new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Map<ITestResult, List<Throwable>> result = ErrorCollector.getVerificationFailuresMap();
		for (Map.Entry<ITestResult, List<Throwable>> s : result.entrySet()) {
			System.out.println(s.getKey().getName());
			System.out.println(s.getValue());
			
			printWriter.write(s.getKey().getName()+","+s.getValue().toString());
			printWriter.write("\n");
			
		
		}

		printWriter.flush();
		printWriter.close();
		
	}

}
