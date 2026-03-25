package com.automation.selenium_automation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Chrome_settings {
	
	static Scanner sc = new Scanner(System.in);

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	static void Chrome_setting() {
		
		System.out.print("Enter Sub Domain");
		String sub_dom = sc.nextLine();
		
		
		String Url = "https://"+sub_dom+".dev.corporate.readytaxxi.com/";
		// Configure chrome to allow notifications
		
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 1); // 1 = Allow
		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(Url);

	}

}
