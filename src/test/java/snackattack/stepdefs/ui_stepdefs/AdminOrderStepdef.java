package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import snackattack.pages.AdminPanelPage;
import snackattack.pages.HomePage;
import snackattack.pages.adminpanelpages.OrderManagementPage;
import snackattack.pages.adminpanelpages.ProductManagementPage;
import snackattack.utilities.ActionsUtils;
import snackattack.utilities.Driver;
import snackattack.utilities.WaitUtils;

import java.time.Duration;
import java.util.List;

public class AdminOrderStepdef {

    HomePage homePage = new HomePage();
    OrderManagementPage orderManagementPage = new OrderManagementPage();
    AdminPanelPage adminPanelPage = new AdminPanelPage();
    ProductManagementPage productPage = new ProductManagementPage();
    WebDriver driver = Driver.getDriver();


    @When("Admin {string} görüntüler")
    public void adminSayfayiVeyaListeleriGoruntuler(String sayfaAdi) {
        System.out.println("Admin " + sayfaAdi + " görüntülüyor.");
        Assert.assertTrue("All Orders başlığı görüntülenemedi!", orderManagementPage.AdminOrderlist.isDisplayed());
    }

    @And("Admin Order Details sayfasını görüntüler")
    public void adminOrderDetailsSayfasiniGoruntuler() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {



            orderManagementPage.ilkSiparis.click();
            System.out.println("✅ En üstteki siparişe tıklandı.");
            Thread.sleep(3000);

            // Yeni sayfada "Order Details" başlığını bekle

            // Görüntülendiğini doğrula
            Assert.assertTrue(
                    "'Order Details' başlığı görüntülenemedi!",
                    orderManagementPage.orderDetailsYazisi.isDisplayed()
            );
            Thread.sleep(3000);
            System.out.println("✅ 'Order Details' sayfası başarıyla görüntülendi.");

        } catch (StaleElementReferenceException e) {
            System.out.println("♻️ Element yenilendi, tekrar deneniyor...");
            WebElement ilkSiparisYeniden = driver.findElement(By.xpath("(//table//tbody//tr)[1]"));
            ilkSiparisYeniden.click();
            Thread.sleep(3000);
        }
    }


    @And("Admin {string} başlığını görüntüler")
    public void adminBasligiGoruntuler(String baslik) {
        WebElement headerElement = driver.findElement(By.xpath("//th[contains(text(),'" + baslik + "')]"));
        Assert.assertTrue(baslik + " başlığı bulunamadı!", headerElement.isDisplayed());
        System.out.println("✅ Görüntülenen başlık: " + baslik);
    }


    @When("Admin {string} durumundaki siparişi {string} olarak değiştirir")
    public void adminSiparisDurumunuDegistirir(String mevcutDurum, String yeniDurum) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean bulundu = false;

        while (true) {
            // 1️⃣ Mevcut sayfadaki tüm dropdown'ları bul
            List<WebElement> dropdownList = driver.findElements(By.xpath("//table//tbody//tr//select"));

            for (WebElement dropdown : dropdownList) {
                Select select = new Select(dropdown);
                String seciliDurum = select.getFirstSelectedOption().getText().trim();

                // 2️⃣ Eğer bu siparişin durumu aranan durumsa
                if (seciliDurum.equalsIgnoreCase(mevcutDurum)) {
                    System.out.println("✅ Mevcut durumu '" + mevcutDurum + "' olan sipariş bulundu.");

                    // 3️⃣ Yeni durumu seç
                    select.selectByVisibleText(yeniDurum);
                    ActionsUtils.pressEnter();
                    Thread.sleep(1000);

                    // 4️⃣ Eğer alert varsa yakala ve kabul et
                    try {
                        WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(5));
                        Alert alert = waitAlert.until(ExpectedConditions.alertIsPresent());
                        System.out.println("⚠️ Alert geldi: " + alert.getText());
                        alert.accept();
                        Thread.sleep(500); // alert kapandıktan sonra kısa bekleme
                    } catch (TimeoutException e) {
                        // Alert gelmediyse sorun yok, devam et
                    }

                    System.out.println("➡️ Sipariş durumu '" + yeniDurum + "' olarak değiştirildi.");

                    // 5️⃣ Değişiklik gerçekten oldu mu kontrol et
                    String guncelDurum = select.getFirstSelectedOption().getText().trim();
                    Assert.assertEquals("Sipariş durumu değiştirilemedi!", yeniDurum, guncelDurum);
                    System.out.println("✅ Değişiklik doğrulandı: " + guncelDurum);

                    bulundu = true;
                    break;
                }
            }

            // Eğer bulunduysa, artık sonraki sayfalara bakmaya gerek yok
            if (bulundu) break;

            // 6️⃣ "Next" butonunu bulana kadar scroll yap
            WebElement nextButton = null;
            for (int i = 0; i < 20; i++) { // max 20 kez scroll dene
                List<WebElement> nextButtons = driver.findElements(By.xpath("//a[@class='page-link']//span[@aria-hidden='true' and text()='›']"));
                if (!nextButtons.isEmpty() && nextButtons.get(0).isDisplayed() && nextButtons.get(0).isEnabled()) {
                    nextButton = nextButtons.get(0);

                    // Butonu görünür konuma getir
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block: 'center', inline: 'center'});", nextButton);
                    Thread.sleep(500); // scroll sonrası kısa bekleme
                    break;
                }
                ActionsUtils.scrollDown(); // sayfayı aşağı kaydır
                Thread.sleep(500); // scroll sonrası kısa bekleme
            }

            // Eğer hiç “Next” butonu yoksa veya pasifse → döngü bitir
            if (nextButton == null) {
                System.out.println("⚠️ '" + mevcutDurum + "' durumunda sipariş hiçbir sayfada bulunamadı.");
                break;
            }

            // 7️⃣ “Next” butonuna tıklayıp sayfanın yüklenmesini bekle
            System.out.println("⏭️ '" + mevcutDurum + "' durumunda sipariş bulunamadı, sonraki sayfaya geçiliyor...");
            nextButton.click();
            Thread.sleep(1500); // sayfa yenilenmesi için bekleme
        }

        // 8️⃣ Hiçbir sayfada uygun sipariş bulunamadıysa testi fail et
        if (!bulundu) {
            Assert.fail("❌ '" + mevcutDurum + "' durumunda bir sipariş hiçbir sayfada bulunamadı!");
        }
    }




}
