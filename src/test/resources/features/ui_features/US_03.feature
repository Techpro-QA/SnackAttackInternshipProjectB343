@US_03
Feature: Admin sayfaya kayıt olabilmelidir. (US_03)

  Background:
    Given Sayfaya gidilir
    When Kullanici loginRegister'a tiklar
    And Kullanici "admin" olarak giriş yapar
    And Admin Admin Management'a tiklar
    And Admin New butonuna tiklar

  @US_03_TC_01
  Scenario: TC_01-Gecerli datalar ile Admin sayfaya kayit olabilmelidir
    Then Admin register panel'ine yönlenmis olmali
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Yeni Admin kaydının başarılı olduğu dogrulanir
    And Sayfa kapatilir

  @TC_02
  Scenario: TC_02 - Firstname alani bos birakildiginda yeni Admin Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanini bos birakir
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Yeni admin kaydının başarılı olmadığı doğrulanır
    And Sayfa kapatilir

  @TC_03
  Scenario: TC_03 - Lastname alani bos birakildiginda yeni Admin Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanini bos birakir
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Yeni admin kaydının başarılı olmadığı doğrulanır
    And Sayfa kapatilir

  @TC_04
  Scenario: TC_04 - Email alani bos birakildiginda yeni Admin Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanini bos birakir
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Yeni admin kaydının başarılı olmadığı doğrulanır
    And Sayfa kapatilir

  @TC_05
  Scenario: TC_05 - User Name alani bos birakildiginda yeni Admin Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanini bos birakir
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Yeni admin kaydının başarılı olmadığı doğrulanır
    And Sayfa kapatilir

  @TC_06
  Scenario: TC_06 - Password alani bos birakildiginda yeni Admin Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanini bos birakir
    And Admin Confirm Password alanini bos birakir
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Yeni admin kaydının başarılı olmadığı doğrulanır
    And Sayfa kapatilir

  @TC_07
  Scenario: TC_07 - Confirm Password alani bos birakildiginda yeni Admin Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanini bos birakir
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Yeni admin kaydının başarılı olmadığı doğrulanır
    And Sayfa kapatilir

  @TC_08
  Scenario: TC_08 - Password 4 karakterden kisa oldugunda Admin Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "abc" yazar
    And Admin Confirm Password alanina "abc" yazar
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Password alti "Password must be at least 4 characters." uyarisi gorunur
    And Sayfa kapatilir

  @TC_09
  Scenario: TC_09 - Confirm Password ve Password eslesmediginde Admin kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "1234567" yazar
    And Admin Confirm Password alanina "12345678" yazar
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Confirm Password alti "Passwords must match." uyarisi gorunur
    And Sayfa kapatilir


  @TC_10
  Scenario: TC_10 - Address alani bos birakildiginda Kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanini bos birakir
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then "Address" alti uyari mesaji gorunur
    And Sayfa kapatilir


  @TC_11
  Scenario: TC_11 - Phone Number alani bos birakildiginda Admin kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanini bos birakir
    When Admin register butonuna tiklar
    Then "Phone Number" alti uyari mesaji gorunur
    And Sayfa kapatilir

  @TC_12
  Scenario: TC_12 - Country select ile telefon formatı uyuşmazsa admin Kayit islemi gerceklesmemelidir (Negative Scenario)
    Then Admin register panel'ine yönlenmis olmali
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina "+1305594898" şeklinde numara girer
    When Admin register butonuna tiklar
    Then 'Invalid phone number.' uyari mesaji gorunur
    And Sayfa kapatilir

  @TC_13
  Scenario: TC_13 - Telefon içinde harf veya özel karakter varsa admin Kayit islemi gerceklesmemelidir (Negative Scenario)
    Then Admin register panel'ine yönlenmis olmali
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "random" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina "542678544*" şeklinde numara girer
    When Admin register butonuna tiklar
    Then 'Invalid phone number.' uyari mesaji gorunur
    And Sayfa kapatilir


  @TC_14
  Scenario: TC_14 - Email formati hatali oldugunda Admin kayit islemi gerceklesmemelidir (Negative)
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "tester.gmail.com" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Email alti "Invalid email" uyarisi gorunur
    And Sayfa kapatilir


  @TC_15
  Scenario: TC_15 - Var olan kayitli bir email ile Admni kayit islemi gerceklesmemelidir (Negative)
    Then Admin register panel'ine yönlenmis olmali
    And Admin First Name alanina gecerli data girer
    And Admin Last Name alanina gecerli data girer
    And Admin Email alanina "nupyd@mailinator.com" yazar
    And Admin User Name alanina gecerli data girer
    And Admin Password alanina "random" yazar
    And Admin Confirm Password alanina Password ile ayni degeri girer
    And Admin Address alanina gecerli data girer
    And Admin Country Code select alanindan "TR" secer
    And Admin Phone Number alanina TR kuralina uygun 10 haneli numara girer
    When Admin register butonuna tiklar
    Then Already Registered mesaji görüntülendiği doğrulanır.
    And Sayfa kapatilir
