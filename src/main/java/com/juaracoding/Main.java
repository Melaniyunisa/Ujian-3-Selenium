package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Windows Maximize");
        driver.get("https://www.saucedemo.com/");
        System.out.println("Open Browser");

        //Login User
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Login Behasil");

        System.out.println("Test URL/Link");
        String urlInventory = driver.getCurrentUrl();
        assertEquals(urlInventory, "https://www.saucedemo.com/inventory.html");

        System.out.println("Test Tulisan Products");
        String actualTextProduct = driver.findElement(By.xpath("//span[@class='title']")).getText();
        assertEquals(actualTextProduct, "Products");

        delay(3);

        //Add 1 product to cart
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        System.out.println("Add product to cart");

        System.out.println("Test tulisan Remove");
        String actualTextRemove =  driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).getText();
        assertEquals(actualTextRemove, "Remove");

        delay(3);

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        System.out.println("Test cek produk di cart");
        String actualTextKeranjang = driver.findElement(By.xpath("//div[@class='cart_quantity']")).getText();
        assertEquals(actualTextKeranjang, "1");

        delay(5);

        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();


        //Logout User
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        delay(5);
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
        System.out.println("Logout berhasil");

        String urlUtama= driver.getCurrentUrl();
        assertEquals(urlUtama, "https://www.saucedemo.com/");

        delay(3);

        //Scenario Negative Test Case
        //Input username atau password yang salah
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Test Salah Input Username/Password");

        String error = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertEquals(error, "Username and password do not match");

        delay(3);

        //input password kosong
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Test input password kosong");

        String errorPassword = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertEquals(errorPassword, "Password is required");

        delay(3);

        //input username kosong
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Test input username kosong");

        String errorUsername = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertEquals(errorUsername, "Username is required");

        delay(5);

        driver.quit();
    }

    static void assertEquals(String actual, String expected){
        if(actual.contains(expected)){
            System.out.println("passed");
        }
        else {
            System.out.println("failed");
        }
    }

    static void delay(long detik){
        try {
            Thread.sleep(1000*detik);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}