@US_01
Feature: Login/Register Testi

    Background:
      Given Sayfaya gidilir
      And   Kullanici loginRegister'a tiklar
      And "kullanici" registerButon'a tiklar

      @TC_01
      Scenario:  TC_01 - Gecerli datalar ile Kullanici sayfaya kayit olabilmelidir (Positive)
        And "kullanici" First Name alanina gecerli data girer
        And "kullanici" Last Name alanina gecerli data girer
        And "kullanici" Email alanina "random" yazar
        And "kullanici" User Name alanina gecerli data girer
        And "kullanici" Password alanina "random" yazar
        And "kullanici" Confirm Password alanina Password ile ayni degeri girer
        And "kullanici" Address alanina gecerli data girer
        And "kullanici" Country Code select alanindan "TR" secer
        And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
        When "kullanici" register butonuna tiklar
        Then "kullanici" login sayfasina yonlenir ve URL "/login" olarak devam eder
        And Sayfa kapatilir


  @TC_02
  Scenario: TC_02 - Firstname alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanini bos birakir
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "First Name" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir


  @TC_03
  Scenario: TC_03 - Last Name alani bos birakildiginda kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanini bos birakir
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "Last Name" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_04
  Scenario: TC_04 - Email alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanini bos birakir
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "Email" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_05
  Scenario: TC_05 - User Name alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanini bos birakir
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "User Name" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_06
  Scenario: TC_06 - Password alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanini bos birakir
    And "kullanici" Confirm Password alanini bos birakir
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "Password" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_07
  Scenario: TC_07 - Confirm Password alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanini bos birakir
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "Confirm Password" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_08
  Scenario: TC_08 - Password 4 karakterden kisa oldugunda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "abc" yazar
    And "kullanici" Confirm Password alanina "abc" yazar
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then Password alti "Password must be at least 4 characters." uyarisi gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_09
  Scenario: TC_09 - Confirm Password ve Password eslesmediginde Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "1234567" yazar
    And "kullanici" Confirm Password alanina "12345678" yazar
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then Confirm Password alti "Passwords must match." uyarisi gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_10
  Scenario: TC_10 - Address alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanini bos birakir
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then "Address" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_11
  Scenario: TC_11 - Phone Number alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanini bos birakir
    When "kullanici" register butonuna tiklar
    Then "Phone Number" alti uyari mesaji gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_12
  Scenario: TC_12 - Email formati hatali oldugunda Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "tester.gmail.com" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When "kullanici" register butonuna tiklar
    Then Email alti "Invalid email" uyarisi gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_13
  Scenario: TC_13 - Zaten kayitli bir email ile Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "user_email" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "user_password" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina "userPhone" girer
    When "kullanici" register butonuna tiklar
    Then "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_14
  Scenario: TC_13 - Telefon içinde harf veya özel karakter varsa Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina harf veya ozel karakter icerikli "530-123-ABCD" yazar
    When "kullanici" register butonuna tiklar
    Then Phone Number alti "Invalid phone number." uyarisi gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir

  @TC_15
  Scenario: TC_12 - Country select ile telefon formatı uyuşmazsa Kayit islemi gerceklesmemelidir (Negative)
    And "kullanici" First Name alanina gecerli data girer
    And "kullanici" Last Name alanina gecerli data girer
    And "kullanici" Email alanina "random" yazar
    And "kullanici" User Name alanina gecerli data girer
    And "kullanici" Password alanina "random" yazar
    And "kullanici" Confirm Password alanina Password ile ayni degeri girer
    And "kullanici" Address alanina gecerli data girer
    And "kullanici" Country Code select alanindan "TR" secer
    And "kullanici" Phone Number alanina ulke formatina uymayan sekilde "1234568975" yazar
    When "kullanici" register butonuna tiklar
    Then Phone Number alti "Invalid phone number." uyarisi gorunur
    And "kullanici" kayit sayfasinda kalir ve URL "/register" olarak devam eder
    And Sayfa kapatilir








