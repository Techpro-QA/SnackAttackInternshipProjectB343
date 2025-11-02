@US_09 @API_ContactMessage
Feature: Contact Message API Testleri

  @POST_New_ContactMessage
  Scenario: Yeni contact message POST istegi ile olusturulmali
    Given API base url "http://207.154.209.12:8080"
    And ContactMessages save endpoint'ine baglanti kurulur
    When Yeni bir contact message aşağıdaki bilgilerle gönderilir:
      | name   | message   | email                 | subject  |
      | cigdem | abc       | amancigdem@gmail.com  | Malıyet  |
    Then Status code dogrulanir
    And Response içinde "message" alanının "Contact Message with id 48 is CREATED Successfully" içerdigi dogrulanır








 #@GET_By_Subject
 #Scenario: Subject parametresiyle mesajlar aranabilmeli
 #  When Subject "API Test Konu" ile GET isteği gönderilir
 #  Then Status kodu 200 olmalı
 #  And Response içinde "subject" alanı "API Test Konu" içermeli



 #@DELETE_ContactMessage
 #Scenario: Bir contact message kaydı DELETE isteği ile silinebilmeli
 #  Given Silinecek contact message id'si 1 olarak ayarlanır
 #  When DELETE isteği gönderilir
 #  Then Status kodu 200 veya 204 olmalı