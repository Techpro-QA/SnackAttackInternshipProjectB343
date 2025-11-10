package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import snackattack.pages.CartPage;
import snackattack.pages.ProductsPage;
import snackattack.utilities.JSUtils;
import snackattack.utilities.ReusableMethods;
import snackattack.utilities.WaitUtils;

import javax.swing.text.Utilities;

public class CartPageStepDefs {

    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();


    @And("Kullanici doner sekmesine tiklar")
    public void kullaniciDonerSekmesineTiklar() {
        JSUtils.JSscrollIntoView(cartPage.donerMenu);
        JSUtils.JSclickWithTimeout(cartPage.donerMenu);
    }


    @And("Kullanici tavuk dönere tiklar")
    public void kullaniciTavukDönereTiklar() {
        JSUtils.JSscrollIntoView(cartPage.tavukDonerMenu);
        JSUtils.JSclickWithTimeout(cartPage.tavukDonerMenu);

    }

    @And("Kullanici mayonez seçer")
    public void kullaniciMayonezSeçer() {
        JSUtils.JSclickWithTimeout(cartPage.mayonezOption);
        JSUtils.JSscrollIntoView(cartPage.mayonezOption);
    }

    @And("Kullanici ketcap seçer")
    public void kullaniciKetcapSeçer() {
        JSUtils.JSclickWithTimeout(cartPage.ketcapOption);
        JSUtils.JSclickWithTimeout(cartPage.ketcapOption);
    }

    @And("Kulanici {string} sekmesine tiklar")
    public void kulaniciSekmesineTiklar(String arg0) {
        if (arg0.contains("+")) {
            JSUtils.JSclickWithTimeout(cartPage.plusButton);
        } else {
            JSUtils.JSclickWithTimeout(cartPage.minusButton);
        }
    }

    @Then("Urun miktarı artığı doğrulanır")
    public void urunMiktarıArtığıDoğrulanır() {
        int counter = Integer.parseInt(cartPage.counter.getText());
        Assert.assertTrue(counter > 1);
    }

    @Then("Urun miktarı azaldığı doğrulanır")
    public void urunMiktarıAzaldığıDoğrulanır() {
        int counter = Integer.parseInt(cartPage.counter.getText());
        Assert.assertTrue(counter < 2);
    }

    @And("Kullanici Add To Cart sekmesine tiklar")
    public void kullaniciAddToCartSekmesineTiklar() {
        ReusableMethods.waitForSecond(3);
        cartPage.addToCartButton.click();

    }

    @Then("Ürünlerin sepete eklendiği doğrulanır")
    public void ürünlerinSepeteEklendiğiDoğrulanır() {
        WaitUtils.waitFor(3);
        String addedMassage = cartPage.itemAddedSuccessfullyMassageText.getText();
        System.out.println(addedMassage);
    }

    @And("Kullanici View Cart iconuna tiklar")
    public void kullaniciViewCartIconunaTiklar() {
        ReusableMethods.waitForSecond(5);
        cartPage.viewCartIcon.click();
    }

    @And("Kullanici checkout butonuna tiklar")
    public void kullaniciCheckoutButonunaTiklar() {
        ReusableMethods.waitForSecond(3);
        cartPage.checkoutButton.click();
    }

    @And("Kullanici Craete Order sekmesine tiklar")
    public void kullaniciCraeteOrderSekmesineTiklar() {
        ReusableMethods.scrollEnd();
        cartPage.createOrderButton.click();
    }


    @And("Odeme sayfasına yönlendirildiği doğrulanır")
    public void odemeSayfasınaYönlendirildiğiDoğrulanır() {
        String paymentMassage = cartPage.odemeMassageText.getText();
        Assert.assertTrue(paymentMassage.contains("Kartla Ödeme"));
    }

    @Then("Sepetteki ürünler doğrulanır")
    public void sepettekiÜrünlerDoğrulanır() {
        String urunAdi = cartPage.productName.getText();
        Assert.assertTrue(urunAdi.contains("Tavuk Döner 100gr"));
        String urunFiyati = cartPage.productPrice.getText();
        Assert.assertTrue(urunFiyati.contains("50.00"));

    }
}
