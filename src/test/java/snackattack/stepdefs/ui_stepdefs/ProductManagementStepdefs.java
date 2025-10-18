package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import snackattack.pages.adminpanelpages.ProductManagementPage;

public class ProductManagementStepdefs {
    ProductManagementPage productManagementPage = new ProductManagementPage();

    @And("Admin güncelleyeceği urunune tiklar")
    public void adminGüncelleyeceğiUrununeTiklar() {

        if(productManagementPage.firstProductRow.isDisplayed()) {
            productManagementPage.firstProductRow.click();
        }

    }



}
