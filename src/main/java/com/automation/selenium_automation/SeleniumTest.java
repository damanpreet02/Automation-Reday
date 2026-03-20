package com.automation.selenium_automation;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumTest extends Chrome_settings {

	static String tab = null;

	static Scanner sc = new Scanner(System.in);

	// Input
	public static void input() {
		System.out.println("Enter Tab:");
		tab = sc.nextLine();
	}

	// Login
	public static void login() {
//		if(tab.equals("Login")) {

		WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/form/div[1]/div/div[1]/input")));
		// Wait
		wait.until(d -> {
			String value = phoneField.getAttribute("value");
			return value.matches("\\d{10}");
		});
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/form/div[2]/button")).click();
		System.out.println(driver.getTitle());

		// OTP Wait Loop
		for (int i = 1; i <= 4; i++) {
			WebElement d1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("digit" + i)));
			wait.until(d -> {
				String value = d1.getAttribute("value");
				return value.matches("\\d{1}");
			});
		}

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/button")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div[2]/div/main/div/div[3]/div[1]/div/div[2]/a/button")));
//		}
	}

	// Dashboard or Create Booking
	public static void create_booking() throws InterruptedException {

		driver.findElement(By.xpath("/html/body/div[2]/div/main/div/div[3]/div[1]/div/div[2]/a/button")).click();
		Thread.sleep(2000);

		driver.findElement(By.name("pickupLocation")).sendKeys("Henceforth Solution");
		List<WebElement> suggestions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("pac-item")));
		Thread.sleep(1000);
		suggestions.get(0).click(); // index 0 = second suggestion

		Thread.sleep(2000);
		driver.findElement(By.name("destinationLocation")).sendKeys("Dc Office Mohali");
		List<WebElement> suggestions1 = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("pac-item")));
		suggestions1.get(0).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("/html/body/div[2]/div/main/form/div/div[3]/div[2]/div/div[2]/button")).click();

		Thread.sleep(5000);

		driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[1]/div[2]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[6]/div[2]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[2]/div/main/form/div/div[3]/div[3]/div/div[1]/button")).click();
		driver.findElement(By.name("customerName")).sendKeys("Daman");
		driver.findElement(By.name("customerEmail")).sendKeys("Daman@yopmail.com");
		driver.findElement(By.name("customerPhone")).sendKeys("121232321");
		driver.findElement(
				By.xpath("//html/body/div[2]/div/main/form/div/div[3]/div[3]/div/div[3]/div/div/div[2]/button"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div[2]/div/main/form/div/div[3]/div[3]/div/div[3]/div[2]/div/div[1]/div/div[2]/input"))
				.sendKeys("1234");
		driver.findElement(
				By.xpath("/html/body/div[2]/div/main/form/div/div[3]/div[3]/div/div[3]/div[2]/div/div[2]/button"))
				.click();
		Thread.sleep(800);
		driver.findElement(By.xpath("/html/body/div[2]/div/main/form/div/div[3]/div[4]/div/div/div[2]/button")).click();
		List<WebElement> notifications = driver.findElements(By.xpath("/html/body/section"));

		for (WebElement note : notifications) {
			System.out.println(note.getText());
		}

		tab = null;

	}

	// Cancellation of Ride
	public static void cancle() throws InterruptedException {

		Thread.sleep(5000);
		System.out.print("Enter Input: ");
		String input = sc.nextLine();
		System.out.println(input);
		if (input.equalsIgnoreCase("Cancle")) {
			String Booking_id = driver
					.findElement(By.xpath("/html/body/div[2]/div/main/div/div[1]/div[1]/div[3]/div[1]/div[1]/p[2]"))
					.getText();
			driver.findElement(By.xpath("/html/body/div[2]/div/main/div/div[1]/div[2]/div[2]/button")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("/html/body/div[6]/div[2]/button[2]")).click();
			System.out.println("Ride is cancelled: " + Booking_id);
		}

	}

	// Main
	public static void main(String[] args) throws InterruptedException {
		Chrome_setting();
		login();
		input();

		while (!tab.equalsIgnoreCase("Quit")) {
			if (tab.equalsIgnoreCase("Login")) {
			} else if (tab.equalsIgnoreCase("Create")) {
				create_booking();
				input();
			}
		}
		
		if (tab.equalsIgnoreCase("Quit")) {
		driver.quit();	
		}

	}
}