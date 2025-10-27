package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import snackattack.pages.adminpanelpages.AddCatManagementPage;
import snackattack.utilities.*;

import java.util.*;

public class AddCatManagementStepdefs {
    AddCatManagementPage addCatManagementPage = new AddCatManagementPage();

    @And("Add Kategori sayfasi yonetimi paneli goruntulenir")
    public void addKategoriSayfasiYonetimiPaneliGoruntulenir() {
        WaitUtils.waitForVisibility(addCatManagementPage.addKategoriSayfasiYonetimPaneli, 4);
        String actualData = addCatManagementPage.addKategoriSayfasiYonetimPaneli.getText();
        String expectedData = "Add Kategori sayfası yönetimi paneli";
        Assert.assertEquals(expectedData, actualData);
    }

    @And("Admin Addition button' a tiklar")
    public void adminAdditionButtonATiklar() {

        addCatManagementPage.additionButton.click();
    }

    @Then("Admin tum ek kategori detaylarini goruntuler")
    public void adminTumEkKategoriDetaylariniGoruntuler() {


        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : addCatManagementPage.tableHeaders) {
            headerTexts.add(header.getText().trim());
        }

        List<String> expectedHeaders = Arrays.asList("ID", "Name", "Description", "Price", "Category", "Actions");
        Assert.assertEquals(expectedHeaders, headerTexts);

    }

    @And("Admin New button' a tiklar")
    public void adminNewButtonATiklar() {
        WaitUtils.waitForVisibility((WebElement) addCatManagementPage.newButton, 5);
        ((WebElement) addCatManagementPage.newButton).click();


    }

    @And("Admin Enter Category Name' e {string} girer")
    public void adminEnterCategoryNameEGirer(String deneme) {
        addCatManagementPage.enterCategoryName.sendKeys(deneme);
    }

    @And("Admin Add button' a tiklar")
    public void adminAddButtonATiklar() throws InterruptedException {
        addCatManagementPage.addButton.click();
        WaitUtils.waitFor(1);
    }

    @And("Basarili alert' i dogrulanir ve onaylanir")
    public void basariliAlertIDogrulanirVeOnaylanir() {

        Assert.assertEquals("Category added successfully!", Driver.getDriver().switchTo().alert().getText());
        ReusableMethods.alertAccept();

    }

    @Then("Kategori listesinde {string} oldugu dogrulanir")
    public void kategoriListesindeOlduguDogrulanir(String deneme) {

        Driver.getDriver().navigate().refresh();

        WaitUtils.waitFor(2);
        List<String> nameSutunList = new ArrayList<>();
        for (WebElement w : addCatManagementPage.nameSutunListLocate) {
            nameSutunList.add(w.getText().trim());
        }
        Assert.assertTrue(nameSutunList.contains(deneme));
    }

    @And("{string} Listeden silinir ve silindigi dogrulanir")
    public void listedenSilinirVeSilindigiDogrulanir(String deneme) {

        ActionsUtils.doubleClick(addCatManagementPage.deleteIconForDeneme);
        WaitUtils.waitFor(2);
        ReusableMethods.alertAccept();
        WaitUtils.waitFor(2);
        Assert.assertEquals("Category deleted successfully!", Driver.getDriver().switchTo().alert().getText());
        WaitUtils.waitFor(3);
        // ReusableMethods.alertAccept();
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ESCAPE).build().perform();

        Driver.getDriver().navigate().refresh();

        List<String> nameSutunList = new ArrayList<>();
        for (WebElement w : addCatManagementPage.nameSutunListLocate) {
            nameSutunList.add(w.getText().trim());
        }
        Assert.assertFalse(nameSutunList.contains(deneme));

    }

    @Then("Alert ile bos kategori eklenmedigi dogrulanir")
    public void alertIleBosKategoriEklenmedigiDogrulanir() {
        WaitUtils.waitFor(2);
        Assert.assertEquals("Category name cannot be empty.", Driver.getDriver().switchTo().alert().getText());
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ESCAPE).build().perform();
        addCatManagementPage.cancelButton.click();
    }

    @And("Admin Name' a {string} girer")
    public void adminNameAGirer(String Sezarsos) {
        addCatManagementPage.nameTextBox.sendKeys(Sezarsos);
    }

    @And("Admin Description' a {string} girer")
    public void adminDescriptionAGirer(String Tamamendogal) {
        addCatManagementPage.descriptionTextArea.sendKeys(Tamamendogal);
    }

    @And("Admin Price' a {int} girer")
    public void adminPriceAGirer(int price) {
        addCatManagementPage.priceTextBox.sendKeys(String.valueOf(price));
    }

    @And("Admin dropdown menuden Salata Soslari secer")
    public void adminDropdownMenudenSalataSoslariSecer() {

        ReusableMethods.ddmVisibleText(addCatManagementPage.ddm, "Salata Soslari");
    }

    @Then("Admin ekleme basarili alert' i dogrular ve onaylar")
    public void adminEklemeBasariliAlertIDogrularVeOnaylar() {

        Assert.assertEquals("Addition added successfully!", Driver.getDriver().switchTo().alert().getText());
        ReusableMethods.alertAccept();
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ESCAPE).build().perform();
        Driver.getDriver().navigate().refresh();

    }


    @Then("Admin Add button' nin tiklanabilir olmadigini dogrular")
    public void adminAddButtonNinTiklanabilirOlmadiginiDogrular() {

        Assert.assertTrue(addCatManagementPage.addButton.isEnabled());

    }
}

