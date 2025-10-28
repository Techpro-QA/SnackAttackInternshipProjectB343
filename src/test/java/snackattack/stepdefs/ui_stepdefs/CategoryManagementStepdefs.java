package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import snackattack.pages.adminpanelpages.CategoryManagementPage;
import snackattack.utilities.Driver;
import snackattack.utilities.ReusableMethods;
import snackattack.utilities.TestData;

import java.util.ArrayList;
import java.util.List;


public class CategoryManagementStepdefs {
    CategoryManagementPage categoryManagementPage= new CategoryManagementPage();


    @When("Admin Category Management Panelinde New butonuna tiklar")
    public void adminCategoryManagementPanelindeNewButonunaTiklar() {
        categoryManagementPage.categoryManagementNewButton.click();

    }

    @And("Admin Name textbox'ini {string} ile doldurur")
    public void adminNameTextboxIniIleDoldurur(String categoryName) {
        categoryManagementPage.createCategoryNameTextBox.sendKeys(categoryName);
        TestData.expextedCategoryName= categoryName;

    }

    @And("Active checkbox'inin secili oldugu kontrol edilir")
    public void activeCheckboxIninSeciliOlduguKontrolEdilir() {
        if (!categoryManagementPage.createCategoryActiveCheckBox.isSelected()){
            categoryManagementPage.createCategoryActiveCheckBox.click();
        }
    }

    @And("Admin save butonuna tiklar")
    public void adminSaveButonunaTiklar() {
        categoryManagementPage.createCategorySaveButton.click();
    }

    @Then("Kategorinin eklendigi kontrol edilir")
    public void kategorininEklendigiKontrolEdilir() {


        List<WebElement> rows = categoryManagementPage.allCategoryTable;

// her satırın 2. hücresindeki Name sütununu kontrol et
        List<String> actualNames = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 1) { // header'ı atla
                actualNames.add(cells.get(1).getText());
            }
        }


        Assert.assertTrue(actualNames.contains(TestData.expextedCategoryName));



    }

    @Then("Kategorinin eklenmedigi kontrol edilir")
    public void kategorininEklenmedigiKontrolEdilir() {
        ReusableMethods.checkValidationMessage(Driver.getDriver(), categoryManagementPage.createCategoryNameTextBox,"Lütfen bu alanı doldurun.");

    }

    @When("Admin silinmesi hedeflenilen kategorinin action bolumundeki delete icon'ina tiklar")
    public void adminSilinmesiHedeflenilenKategorininActionBolumundekiDeleteIconInaTiklar() {

        TestData.expextedCategoryName= categoryManagementPage.firstRowCategoryName.getText();
        categoryManagementPage.firstRowDeleteButton.click();
    }

    @And("Admin Allert mesajininda tamam'a tiklar")
    public void adminAllertMesajinindaTamamATiklar() {
       ReusableMethods.alertAccept();
    }

    @Then("Kategorinin silindigi kontrol edilir")
    public void kategorininSilindigiKontrolEdilir() {
        Assert.assertNotEquals(categoryManagementPage.firstRowCategoryName.getText(), TestData.expextedCategoryName);

    }

    @Then("Something went wrong. Please try again. mesajinin alindigi kontrol edilir")
    public void somethingWentWrongPleaseTryAgainMesajininAlindigiKontrolEdilir() {

        Assert.assertTrue(categoryManagementPage.somethingWentWrongPleaseTryAgainText.isDisplayed());

    }

    @And("Admin Allert mesajininda iptal'e tiklar")
    public void adminAllertMesajinindaIptalETiklar() {
        ReusableMethods.alertDismiss();
    }

    @Then("Kategorinin silinmedigi kontrol edilir")
    public void kategorininSilinmedigiKontrolEdilir() {
        Assert.assertEquals(categoryManagementPage.firstRowCategoryName.getText(), TestData.expextedCategoryName);
    }

    @Then("Aktif sutunun goruntulendigi kontrol edilir")
    public void aktifSutununGoruntulendigiKontrolEdilir() {
        Assert.assertTrue(categoryManagementPage.firstRowActiveCell.isDisplayed());
        Assert.assertTrue(categoryManagementPage.secondRowActiveCell.isDisplayed());
        Assert.assertTrue(categoryManagementPage.thirdRowActiveCell.isDisplayed());

    }
}
