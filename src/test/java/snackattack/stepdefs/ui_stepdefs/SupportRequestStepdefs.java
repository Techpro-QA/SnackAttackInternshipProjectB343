package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import snackattack.pages.adminpanelpages.SupportRequestsPage;
import snackattack.utilities.ConfigReader;
import snackattack.utilities.Driver;
import snackattack.utilities.ReusableMethods;
import snackattack.utilities.WaitUtils;

import java.util.List;

public class SupportRequestStepdefs {

    SupportRequestsPage supportRequestsPage = new SupportRequestsPage();


    @Then("Request kayitlarinin liste halinde goruldugu dogrulanir")
    public void requestKayitlarininListeHalindeGorulduguDogrulanir() {

        Assert.assertTrue(
                "Support Request listesi goruntulenemedi",
                !supportRequestsPage.rows.isEmpty() && supportRequestsPage.rows.get(0).isDisplayed()
        );
    }

    @When("Support request listesinde herhangi bir kaydin goruntuleme butonu tiklanir")
    public void supportRequestListesindeHerhangiBirKaydinGoruntulemeButonuTiklanir() {

        supportRequestsPage.supportRequestViewButton.click();

    }

    @Then("Detay ekraninin acildigi dogrulanir")
    public void detayEkranininAcildigiDogrulanir() {

        Assert.assertTrue(supportRequestsPage.detailOfRequest.isDisplayed());
        Assert.assertEquals("Message Details", supportRequestsPage.messageDetailTitle.getText());
        Assert.assertTrue(supportRequestsPage.messageDetailParagraphs.size() >= 4);

    }

    @When("Filtre bolumune tiklanir")
    public void filtreBolumuneTiklanir() {

        supportRequestsPage.dropdownMenu.click();

    }

    @And("All secenegi secilir")
    public void allSecenegiSecilir() {

        ReusableMethods.ddmVisibleText(supportRequestsPage.dropdownMenu, "All");

    }

   @And("Search butonuna tiklanir")
   public void searchButonunaTiklanir() {

       WaitUtils.waitFor(3);
       ReusableMethods.click(supportRequestsPage.searchButton);
       WaitUtils.waitFor(2);
   }

    @Then("Tum requestlerin goruntulendigi dogrulanir")
    public void tumRequestlerinGoruntulendigiDogrulanir() {

        int rowCount = supportRequestsPage.rows.size();
        System.out.println("Tablodaki request sayısı: " + rowCount);

        Assert.assertTrue("Filtreleme sonucu beklenen sayida kayit goruntulenemedi", rowCount >= 2);
    }


    @And("Email seceneği secilir")
    public void emailSeceneğiSecilir() {

        ReusableMethods.ddmVisibleText(supportRequestsPage.dropdownMenu, "Email");

    }

    @And("Detayı istenen email adresi adres kutusuna yazilir")
    public void detayıIstenenEmailAdresiAdresKutusunaYazilir() {

        supportRequestsPage.emailInput.clear();
        supportRequestsPage.emailInput.sendKeys(ConfigReader.getProperty("userEmail"));

    }

    @Then("Secilen email adresi dısında maillere ait bilgilerin listelendigi dogrulanir")
    public void secilenEmailAdresiDısındaMaillereAitBilgilerinListelendigiDogrulanir() {

        String expectedEmail = ConfigReader.getProperty("userEmail");
        WaitUtils.waitFor(2);
        Assert.assertFalse("Hiç kayıt bulunamadı!", supportRequestsPage.emailList.isEmpty());
        for (WebElement emailCell : supportRequestsPage.emailList) {
            String actualEmail = emailCell.getText().trim();
            System.out.println("Listede bulunan email: " + actualEmail);
            //Assert.assertNotEquals("Farklı email bulundu!", expectedEmail, actualEmail);
            Assert.assertEquals(expectedEmail, actualEmail);
        }
    }


    @And("Subject seceneği secilir")
    public void subjectSeceneğiSecilir() {

        ReusableMethods.ddmVisibleText(supportRequestsPage.dropdownMenu, "Subject");

    }

    @And("Subject alanına {string} filtre kriteri girilir")
    public void subjectAlanınaFiltreKriteriGirilir(String subject) {

        supportRequestsPage.subjectInput.clear();
        supportRequestsPage.subjectInput.sendKeys(subject);

    }




    @Then("Sadece ilgili subjecte sahip Support Request'lerin listelendigi dogrulanir")
    public void sadeceIlgiliSubjecteSahipSupportRequestLerinListelendigiDogrulanir() {


        String expectedSubject = supportRequestsPage.subjectName.getText();
        List<WebElement> subjects = supportRequestsPage.subjectList;

        for (WebElement subject : subjects) {
            Assert.assertTrue(
                    "Listelenen subject beklenen değeri içermiyor: " + subject.getText(),
                    subject.getText().contains(expectedSubject)
            );

        }


    }

    @And("Date seceneği secilir")
    public void dateSeceneğiSecilir() {

        ReusableMethods.ddmVisibleText(supportRequestsPage.dropdownMenu, "Date");
    }


    @And("Date alanına date filtre kriteri girilir")
    public void dateAlanınaDateFiltreKriteriGirilir() {

        supportRequestsPage.dateInput1.sendKeys("23-10-2025");
        supportRequestsPage.dateInput2.sendKeys("23-10-2025");

    }

    @Then("Sadece ilgili {string} değerine sahip Support Request'in listelendiği doğrulanır")
    public void sadeceIlgiliDeğerineSahipSupportRequestInListelendiğiDoğrulanır(String date) {

        //?????????????????????????????????????


    }

    @And("Time secenegi secilir")
    public void timeSecenegiSecilir() {

        ReusableMethods.ddmVisibleText(supportRequestsPage.dropdownMenu, "Time");
    }

    @And("Time alanına gecerli filtre kriteri girilir")
    public void timeAlanınaGecerliFiltreKriteriGirilir() {

        supportRequestsPage.hour1.sendKeys("13");
        supportRequestsPage.minute1.sendKeys("06");
        supportRequestsPage.hour2.sendKeys("13");
        supportRequestsPage.minute2.sendKeys("06");

    }

    @Then("Sadece girilen time değerine sahip Support Request kayıtlarının listelendiği doğrulanır")
    public void sadeceGirilenTimeDeğerineSahipSupportRequestKayıtlarınınListelendiğiDoğrulanır() {

//????????????????????????????????????????????????????


    }

    @And("Herhangi bir mesajin yaninda bulunan Delete ikonuna tiklanir")
    public void herhangiBirMesajinYanindaBulunanDeleteIkonunaTiklanir() {

        supportRequestsPage.deleteButtton.click();
    }

    @And("Onay penceresinde Yes secenegine tiklanir")
    public void onayPenceresindeYesSecenegineTiklanir() {

        supportRequestsPage.confirmYesButton.click();
        
    }

    @Then("Basarili silme bildirimi {string} gorulur")
    public void basariliSilmeBildirimiGorulur(String arg0) {

        Assert.assertTrue(supportRequestsPage.successMsj.isDisplayed());


    }
}