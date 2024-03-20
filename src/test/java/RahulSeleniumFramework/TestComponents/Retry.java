package RahulSeleniumFramework.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count=0;
	int maxTry=1; //no. of times you want to retry executing the test to see if its a flaky test or not
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry)
		{
			count++;
			return true; // for this test to be retried, it must return true, the moment it returns false it will exit out of the method and stop retrying
			
		}
		
		return false;
	}

}
