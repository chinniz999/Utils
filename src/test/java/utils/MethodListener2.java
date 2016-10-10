package utils;

import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

public class MethodListener2 implements IInvokedMethodListener2{

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		List<Throwable> verificationFailures = ErrorCollector.getVerificationFailures();
		Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {

			
			/*if (verificationFailures.size() > 0) {

				result.setStatus(ITestResult.FAILURE);
				Retry retry = new Retry();
				retry.retry(result);

			}*/
			// if there are verification failures...
			if (verificationFailures.size() > 0) {

				// set the test to failed
				result.setStatus(ITestResult.FAILURE);

				/*ITestResult result2 = Reporter.getCurrentTestResult();
				System.out.println(result2.getName() + " -----> " + result2.isSuccess());*/

				// if there is an assertion failure add it to
				// verificationFailures
				if (result.getThrowable() != null) {
					verificationFailures.add(result.getThrowable());

				}

				int size = verificationFailures.size();
				// if there's only one failure just set that
				if (size == 1) {
					result.setThrowable(verificationFailures.get(0));
				} else {
					// create a failure message with all failures and stack
					// traces (except last failure)
					StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):\n\n");
					for (int i = 0; i < size - 1; i++) {
						failureMessage.append("Failure ").append(i + 1).append(" of ").append(size).append(":\n");
						Throwable t = verificationFailures.get(i);
						String fullStackTrace = Utils.stackTrace(t, false)[1];
						failureMessage.append(fullStackTrace).append("\n\n");
					}

					// final failure
					Throwable last = verificationFailures.get(size - 1);
					failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":\n");
					failureMessage.append(last.toString());

					// set merged throwable
					Throwable merged = new Throwable(failureMessage.toString());
					merged.setStackTrace(last.getStackTrace());

					result.setThrowable(merged);
				}
			}
		}
		
	
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		
		
	}

}
