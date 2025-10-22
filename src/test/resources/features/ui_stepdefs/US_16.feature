@US16
Feature: Admin herhangi bir ürünü silebilmeli
  Scenario: Admin herhangi bir ürünü silebilmeli
    Given Sayfaya gidilir
  And Kullanici loginRegister'a tiklar
  And Kullanici "admin" olarak giriş yapar
  And Admin Product Management'a tiklar
  And Admin silmek istedigi urunun Actions kismindaki çop kutusu(delete) icon'una tiklar
  And Admin Are you sure you want to delete this product? alertini görür ve Tamam'a tiklar
  And Admin mevcut mu? checkbox'indaki tiki kaldirir(unsellected)
  And Admin Güncelle butonuna tiklar
  Then Urunun silindigi kontrol edilir
  And Sayfa kapatilir







