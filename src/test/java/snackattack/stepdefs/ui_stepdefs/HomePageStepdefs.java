package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import snackattack.pages.HomePage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.JSUtils;


public class HomePageStepdefs {

    HomePage homePage = new HomePage();

    @Given("Sayfaya gidilir")
    public void sayfayaGidilir() {
        Driver.getDriver().get(ConfigReader.getProperty("snackUrl"));
    }

    @And("Kullanici loginRegister'a tiklar")
    public void kullaniciLoginRegisterATiklar() {
        homePage.homeLoginRegisterButton.click();
    }



    @And("Kullanici logoya tiklar")
    public void kullaniciLogoyaTiklar() {
        JSUtils.JSscrollIntoView(homePage.homeLogo);
        homePage.homeLogo.click();
    }

    @And("Kullanici Products'a tiklar")
    public void kullaniciProductsATiklar() {
        JSUtils.JSscrollIntoView(homePage.homeProducts);
        homePage.homeProducts.click();
    }

    @And("Kullanici About'a tiklar")
    public void kullaniciAboutATiklar() {
        JSUtils.JSscrollIntoView(homePage.homeAbout);
        homePage.homeAbout.click();
    }

    @And("Kullanici Contact'a tiklar")
    public void kullaniciContactATiklar() {
        JSUtils.JSscrollIntoView(homePage.homeContact);
        homePage.homeContact.click();
    }

    @And("Kullanici Cart'a tiklar")
    public void kullaniciCartATiklar() {
        JSUtils.JSscrollIntoView(homePage.homeViewCart);
        homePage.homeViewCart.click();
    }

    @And("Kullanici {string} olarak giriş yapar")
    public void kullaniciOlarakGirişYapar(String name) {

        String email = name.equals("admin") ? ConfigReader.getProperty("adminEmail") : ConfigReader.getProperty("userEmail");
        String password = name.equals("admin") ? ConfigReader.getProperty("adminPassword") : ConfigReader.getProperty("userPassword");

        homePage.loginEmailTextBox.sendKeys(email);
        homePage.loginPasswordTextBox.sendKeys(password);
        homePage.loginButton.click();
    }
}
