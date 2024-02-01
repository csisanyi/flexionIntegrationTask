package com.flexion.funflowers;


import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

public class Main {
    public static void main(String[] args) {
        FunFlowersIntegration integration = new FunFlowersIntegration("OfficialTestIDSandorCsicsman");
        IntegrationTestRunner testRunner = new IntegrationTestRunner();
        testRunner.runTests(integration);
    }
}