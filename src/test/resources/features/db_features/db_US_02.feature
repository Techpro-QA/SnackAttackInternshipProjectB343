

@db_US_02_additions_category
Feature: additions_category isimlerini ve bilgilerini dogrula

  Scenario:  Ekler kategorilerinin bilgileri veritabanından dogrulanmali
Given Veritabanı bağlantısı kurulur
When additions_category tablosu veritabaninda aranir
Then additions_category bilgileri veritabaninda bulunmalidir
And Baglanti kapatilir

