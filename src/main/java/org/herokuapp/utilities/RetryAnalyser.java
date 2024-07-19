package org.herokuapp.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class is defined to help re-execute the test cases that have failed
 * Re-execution is tried once and then reported as failure if the test doesn't pass during retry
 * retry method from IRetryAnalyzer interface needs to be provided with an implementation
 */
public class RetryAnalyser implements IRetryAnalyzer {

    int count = 0;
    int retryCount = 1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < retryCount){
            count++;
            return true;
        }
        return false;
    }
}
