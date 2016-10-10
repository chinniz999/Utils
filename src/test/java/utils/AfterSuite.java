package utils;

import java.util.List;
import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;

public class AfterSuite implements ISuiteListener {

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

		Map<ITestResult, List<Throwable>> result = ErrorCollector.getVerificationFailuresMap();
		for (Map.Entry<ITestResult, List<Throwable>> s : result.entrySet()) {

			System.out.println(s.getKey() + "--------->" + s.getValue());

		}

	}

}
