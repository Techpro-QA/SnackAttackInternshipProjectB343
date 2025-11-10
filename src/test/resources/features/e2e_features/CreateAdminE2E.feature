Feature:

  Scenario: TC_01-Gecerli datalar ile Yeni Admin kaydı yapabilmelidir.
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Admin Management'a tiklar
    And New Buttonuna tiklar
    And "Admin" First Name alanina gecerli data girer
    And "Admin" Last Name alanina gecerli data girer
    And "Admin" Email alanina "random" yazar
    And "Admin" User Name alanina gecerli data girer
    And "Admin" Password alanina "random" yazar
    And "Admin" Confirm Password alanina Password ile ayni degeri girer
    And "Admin" Address alanina gecerli data girer
    And "Admin" Country Code select alanindan "TR" secer
    And "Admin" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "Admin" register butonuna tiklar
    Then Yeni Admin kaydının başarılı olduğu dogrulanir
    And Sayfa kapatilir

  Scenario: Database ürün güncelleme kontrol
    Given Veritabanı bağlantısı kurulur
    When Olusturulan admin icin "select * from snack_attack_db.users where user_name=" sorgusu gonderilir
    Then adminin olusturuldugu database'de kontrol edilir
    And Baglanti kapatilir