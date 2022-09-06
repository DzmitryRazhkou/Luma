package com.qa.luma.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("=====> " +result.getName()+ " Test started <===== ");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("=====> " +result.getName()+ " Test is successful <===== ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("=====> " +result.getName()+ " Test has failed <===== ");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("=====> " +result.getName()+ " Test has skipped <===== ");
    }


    @Override
    public void onStart(ITestContext context) {
        System.out.println("=====> " +context.getName()+ " Test has started <===== ");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
