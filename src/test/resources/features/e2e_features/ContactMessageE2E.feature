@E2E_Contact_Message

Feature: Contact Message end to end doğrulama

  #Scenario: Kullanıcı ui uzerinden contact message formunu doldurur ve olusturulan mesaj api ve db de dogrulanır

  @e2e_ui_contact
  @noToken
  Scenario: Kullanıcı tum alanları dogru dolduruldugunda basarili gonderim yapabilmeli

    Given Sayfaya gidilir
    And  Kullanici Contact'a tiklar
    When Kullanıcı formu aşağıdaki bilgilerle doldurur ve gönderir
      | name  | email              | subject  | message             |
      | Eren  | amaneren@gmail.com | Tatlı    | End to End kontrol  |
    Then Then Kullanıcı mesaj oluşturuldu bilgisini görür


  @e2e_api_contact
  @adminToken
  Scenario: Email'e göre mesaj araması yapılmalı

    Given API base url "http://207.154.209.12:8080"
    And ContactMessages "searchByEmail" endpoint'ine baglanti kurulur
    When "Email" olarak "amaneren@gmail.com" bilgisi gönderilir
    Then Statüs kodu 200 olmali
    And Response içinde "content.email" alanının "amaneren@gmail.com" icerdigi dogrulanır



  @e2e_db_contact
  @adminToken
  Scenario Outline: Contact_message tablosunda belirli email adresi ve bilgileri dogrulanmali

    Given Veritabanı bağlantısı kurulur
    When "<email>" email adresine ait kayit sorgulanir
    Then Email "<email>" icin name "<name>" message "<message>" subject "<subject>" olmalidır
    And Baglanti kapatilir
    Examples:
     | email               | name  | message             | subject |
     | amaneren@gmail.com  | Eren  | End to End kontrol  | Tatlı   |





