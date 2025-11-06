@US_08_02
Feature: User sepete ekli ürünü/ürünleri sipariş verebilmeli/iptal edebilmeli

  Background:
    Given Sayfaya gidilir
    And Kullanici loginRegister'a tiklar
    And Kullanici registerButon'a tiklar
    And Kullanici First Name alanina gecerli data girer
    And Kullanici Last Name alanina gecerli data girer
    And Kullanici Email alanina "random" yazar
    And Kullanici User Name alanina gecerli data girer
    And Kullanici Password alanina gecerli password yazar
    And Kullanici Confirm Password alanina gecerli password yazar
    And Kullanici Address alanina gecerli data girer
    And Kullanici Country Code select alanindan "TR" secer
    And Kullanici Phone Number alanina tr kuralina uygun 10 haneli numara girer
    And Kullanici register butonuna tiklar
    And Kullanici login sayfasina yonlenir ve URL "/login" olarak devam eder
    And Kullanici Email alanina olusturulan email i girer
    And Kullanici Password alanina gecerli password yazar
    And Kullanici loginButton'a tiklar
    And Kullanici 2 saniye bekler
    And Kullanici Products'a tiklar
    And Kullanici sol ustteki ilk urune tiklar
    And Kullanici Add to Cart'a tiklar

  @US_08_TC_02
  Scenario:
    Given Kullanici sag ustteki ismine tiklar
    When  Kullanici dropdown menüdeki dashboard a tiklar
    And Kullanici user panelden My Cart'a tiklar
    Then Kullanicinin seçtigi urunun sepetindeki urun ile aynı urun oldugu ikinci yol ile dogrulanir
    And Sayfa kapatilir

  @US_08_TC_06_07_08 @closePage #kart ödeme bilgilerinin girilmesi gereken sayfa olusturulmamıs
  Scenario: User ürünün siparisi verip ödeme yapabilmeli
    Given Sag ust kosedeki sepet ikonuna tiklanir
    Then Kullanici Checkout a tiklar
    And Kullanici Create Order a tiklar
    And Acılan pop up tan CREDIT_CARD secilerek Ödeme Yap a tiklanır
    Then Your Orders sayfasında status pending oldugu dogrulanır
    And Acılan odeme sayfasına kredi kartı bilgileri girilir
    And Sayfa kapatilir

  @US_08_TC_09
  Scenario: User ürünün siparisi verip ödeme yapabilmeli
    Given Sag ust kosedeki sepet ikonuna tiklanir
    Then Kullanici Checkout a tiklar
    And Kullanici Create Order a tiklar
    And Acılan pop up tan Cash secilerek Ödeme Yap a tiklanır
    Then Your Orders sayfasında status pending oldugu dogrulanır
    And Sayfa kapatilir

  @US_08_TC_11 @closePage #sayfa ancak refresh edildikten sonra status cancelled oluyor, refresh edimedikce pending te kalıyor
  Scenario: User siparişi iptal edebilmeli
    Given Sag ust kosedeki sepet ikonuna tiklanir
    Then Kullanici Checkout a tiklar
    And Kullanici Create Order a tiklar
    And Acılan pop up tan Cash secilerek Ödeme Yap a tiklanır
    Then Your Orders sayfasında status pending oldugu dogrulanır
    And Your Orders sayfasında Cancel Order a tiklanir
    And Cıkan Are you sure you want to cancel this order? allertte tamama tiklanir
    And Cıkan Order cancelled successfully yazan alertte tamama tiklanir
    Then Your Orders sayfasında status cancelled oldugu dogrulanır
    And Sayfa kapatilir

  @US_08_TC_12
  Scenario: User daha önce iptal etmiş olduğu bir siparişi ikinci kez iptal edememeli
    Given Sag ust kosedeki sepet ikonuna tiklanir
    Then Kullanici Checkout a tiklar
    And Kullanici Create Order a tiklar
    And Acılan pop up tan Cash secilerek Ödeme Yap a tiklanır
    Then Your Orders sayfasında status pending oldugu dogrulanır
    And Your Orders sayfasında Cancel Order a tiklanir
    And Cıkan Are you sure you want to cancel this order? allertte tamama tiklanir
    And Cıkan Order cancelled successfully yazan alertte tamama tiklanir
    And Sayfa yenilenir
    And Your Orders sayfasında Cancel Order a tiklanir
    And Cıkan Are you sure you want to cancel this order? allertte tamama tiklanir
    Then Failed to cancel the order. alertunun acıldıgı dogrulanır
    And Sayfa kapatilir






