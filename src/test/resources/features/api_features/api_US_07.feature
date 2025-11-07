@API_ContactMessage
Feature: Contact Message API Testleri

  @POST_New_ContactMessage
  @noToken
  Scenario: Yeni contact message POST istegi ile olusturulmali

    Given API base url "http://207.154.209.12:8080"
    And ContactMessages "save" endpoint'ine baglanti kurulur
    When Yeni bir contact message asagıdaki bilgilerle gonderilir
      | name   | message   | email                 | subject  |
      | cigdem | abc       | amancigdem@gmail.com  | Malıyet  |
    Then Statüs kodu 201 olmali
    And Response içinde "status" alanının "CREATED" icerdigi dogrulanır



  @GET_By_Subject
  @adminToken
  Scenario: Subject'e göre mesaj araması yapılmalı

    Given API base url "http://207.154.209.12:8080"
    And ContactMessages "searchBySubject" endpoint'ine baglanti kurulur
    When "Subject" olarak "Malıyet" bilgisi gönderilir
    Then Statüs kodu 200 olmali
    And Response içinde "content.subject" alanının "Malıyet" icerdigi dogrulanır

  @GET_By_Email
  @adminToken
  Scenario: Email'e göre mesaj araması yapılmalı

    Given API base url "http://207.154.209.12:8080"
    And ContactMessages "searchByEmail" endpoint'ine baglanti kurulur
    When "Email" olarak "amancigdem@gmail.com" bilgisi gönderilir
    Then Statüs kodu 200 olmali
    And Response içinde "content.email" alanının "amancigdem@gmail.com" icerdigi dogrulanır

   @GET_Between_Times
   @adminToken
   Scenario: Belirli saat aralığındaki mesajlar listelenmeli
     Given API base url "http://207.154.209.12:8080"
     And ContactMessages "searchBetweenTimes" endpoint'ine baglanti kurulur
     When Saat araligi parametreleri asagidaki gibi gonderilir
       | startHour | startMinute | endHour | endMinute |
       | 13         | 51          | 13      | 59        |
     Then Statüs kodu 200 olmali
     And Response body içinde gelen bilgiler verilen saat aralığına uygun olmalı

   @GET_Between_Dates
   @adminToken
   Scenario: Belirli tarihler arasındaki contact message kayıtları listelenir
     Given API base url "http://207.154.209.12:8080"
     And ContactMessages "searchBetweenDates" endpoint'ine baglanti kurulur
     When Kullanici "2025-10-15" ile "2025-10-20" tarihleri arasindaki verileri ister
     Then Statüs kodu 200 olmali
     And Response body içinde gelen bilgiler verilen gun aralığına uygun olmalı






  @GET_ByContactMessageId
  @adminToken
  Scenario: Belirli bir contact message id'ye göre mesaj getirilmeli

    Given API base url "http://207.154.209.12:8080"
    And ContactMessages "getById" endpoint'ine baglanti kurulur
    When Contact message id olarak 17 bilgisi gonderilir
    And GET isteği gonderilir
    Then Statüs kodu 200 olmali
    And Donen mesaj bilgileri dogrulanmali


  @GET_All
  @adminToken
  Scenario: Butun contact mesajlar listelenmeli

    Given API base url "http://207.154.209.12:8080"
    And ContactMessages "getAll" endpoint'ine baglanti kurulur
    When GET request gönderilir
    Then Statüs kodu 200 olmali
    And Response body boş olmamalı

    
  @DELETE_ContactMessage
  @adminToken
  Scenario: Bir contact message kaydı DELETE isteği ile silinebilmeli

    Given API base url "http://207.154.209.12:8080"
    Given "Silinecek" contact message id'si 20 olarak enpoint ayarlanır
    When DELETE isteği gönderilir
    Then Statüs kodu 200 olmali
    And Contact Message with id "20" is DELETED Successfully mesajı doğrulanır

