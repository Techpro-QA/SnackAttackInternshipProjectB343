@US_14
Feature: Admin yeni bir ürün ekleyebilmeli

  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Product Management'a tiklar
    And Admin New butonuna tiklar

  @US14_TC01
  Scenario: TC01 - Admin tüm alanlari eksiksiz doldurarak ürün ekleyebilmeli (Positive Scenario)
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "50" ile doldurur
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "HAMBURGER" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin "C:\Users\seyda\Desktop\hamburgerimg.webp" resim ekler
    And Admin Available ve Active seceneklerini secer
    And Admin Create Product butonuna tiklar
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin Create Product butonuna tiklar
    Then Urunun eklendigi dogrulanir
    And Sayfa kapatilir

  @US14_TC02
  Scenario: TC02 - Admin Discount bilgisi girilmeden de ürün ekleyebilmeli (Positive Scenario)
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "50" ile doldurur
    And Admin İndirim Textbox'ini "" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "HAMBURGER" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin "C:\Users\seyda\Desktop\hamburgerimg.webp" resim ekler
    And Admin Available ve Active seceneklerini secer
    And Admin Create Product butonuna tiklar
    And Admin İndirim Textbox'ini "" ile doldurur
    And Admin Create Product butonuna tiklar
    Then Urunun eklendigi dogrulanir
    And Sayfa kapatilir

  @US14_TC03
  Scenario: TC03 - Admin "Name" alanini bos bırakirsa ürün ekleyememeli (Negative Scenario)
    And Admin Ürün Adı Textbox'ini "" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "50" ile doldurur
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "HAMBURGER" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin Available ve Active seceneklerini secer
    And Admin Create Product butonuna tiklar
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin Create Product butonuna tiklar
    Then Urunun eklenemedigi dogrulanir
    And Sayfa kapatilir

  @US14_TC04
  Scenario: TC04 - Admin Price'ı negatif ya da '0' girerse ürün ekleyememeli (Negative Scenario)
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "-10" ile doldurur
    And Admin İndirim Textbox'ini "0" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "HAMBURGER" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin Available ve Active seceneklerini secer
    And Admin Create Product butonuna tiklar
    Then Zorunlu alanlari doldurma dogrulanir
    And Sayfa kapatilir

  @US14_TC05
  Scenario: TC05 - Admin, Discount değeri Price'dan büyükse ürün ekleyememeli -BUG (Negative Scenario)
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "35" ile doldurur
    And Admin İndirim Textbox'ini "50" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "HAMBURGER" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin Available ve Active seceneklerini secer
    And Admin Create Product butonuna tiklar
    Then Urun eklenememeli
    And Sayfa kapatilir

  @US14_TC06
  Scenario: TC06 - Admin Categories seçmeden ürün ekleyememeli -BUG  (Negative Scenario)
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "50" ile doldurur
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    And Admin Available ve Active seceneklerini secer
    And Admin Create Product butonuna tiklar
    Then Urun eklenememeli
    And Sayfa kapatilir

  @US14_TC07
  Scenario: TC07 - Admin Image yüklemeden ürün ekleyememeli -BUG  (Negative Scenario)
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "50" ile doldurur
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "HAMBURGER" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    # Image yükleme adımı atlandı
    And Admin Available ve Active seceneklerini secer
    And Admin Create Product butonuna tiklar
    Then Urun eklenememeli
    And Sayfa kapatilir

  @US14_TC08
  Scenario: TC08 - Admin "Available" ve "Active" secmeden ürün ekleyememeli -BUG  (Negative Scenario)
    And Admin Ürün Adı Textbox'ini "random" ile doldurur
    And Admin Açıklama Textbox'ini "Cheeseburger" ile doldurur
    And Admin İçerik Textbox'ini "Et" ile doldurur
    And Admin Fiyat Textbox'ini "50" ile doldurur
    And Admin İndirim Textbox'ini "35" ile doldurur
    And Admin submit butonuna scroll yapar
    And Admin "HAMBURGER" kategorisini secer
    And Admin "Soslar" Ek Kategorisini secer
    # Checkbox seçilmedi
    And Admin Available ve Active seceneklerini secmez
    And Admin Create Product butonuna tiklar
    Then Urun eklenememeli
    And Sayfa kapatilir