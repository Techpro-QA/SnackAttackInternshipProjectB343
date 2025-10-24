
@DB_US_12_Payment_Validation

  Feature: Bir Admin olarak,
           veritabanında saklanan ödeme isim ve bilgilerini doğrulamak istiyorum,
           böylece veri doğruluğunu ve tutarlılığını sağlayabilirim.

    Background:
      Given Database baglantisi kurulur

      Scenario: Admin olarak veritabanındaki ödeme isim ve bilgileri dogrulanmali
        When Payments tablosu veritabaninda aranir
        And Payment bilgileri UI dan alinir
        Then Payment bilgileri dogrulanir
        And Baglanti kapatilir