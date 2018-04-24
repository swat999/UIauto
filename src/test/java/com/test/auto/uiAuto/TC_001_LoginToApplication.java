package com.test.auto.uiAuto;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.test.auto.uiAuto.testBase.TestBase;

public class TC_001_LoginToApplication extends TestBase{
	
	
@Test
public void start_test() {
	init();
	
}

@AfterTest
public void end_Test() {
	close_browser();
}
}
