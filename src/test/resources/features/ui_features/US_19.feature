@US_19
  Feature: Admin Add Cat Management üzerinde islem yapabilmeli

    Background:
      Given Sayfaya gidilir
      When Kullanici loginRegister'a tiklar
      And Kullanici "admin" olarak giriş yapar
      And Admin Add.Cat.Management'a tiklar


    @US19_TC01
    Scenario:TC01 - Admin tüm kategorileri goruntuleyebilmeli
      When Add Kategori sayfasi yonetimi paneli goruntulenir
      And Admin Addition button' a tiklar
      And Admin tum ek kategori detaylarini goruntuler
      And Sayfa kapatilir



    @US19_TC02
    Scenario:TC02 - Admin Add Cat Management üzerinde kategori ekleyebilmeli ve silebilmeli
      When Add Kategori sayfasi yonetimi paneli goruntulenir
      And Admin New button' a tiklar
      And Admin Enter Category Name' e "deneme" girer
      And Admin Add button' a tiklar
      Then Basarili alert' i dogrulanir ve onaylanir
      Then Kategori listesinde "deneme" oldugu dogrulanir
      Then "deneme" Listeden silinir ve silindigi dogrulanir
      And Sayfa kapatilir

    @US19_TC03
    Scenario:TC03 - Admin Add Cat Management üzerinde boş kategori ekleyememeli (Negative Scenario)
      When Add Kategori sayfasi yonetimi paneli goruntulenir
      And Admin New button' a tiklar
      And Admin Add button' a tiklar
      Then Alert ile bos kategori eklenmedigi dogrulanir
      And Sayfa kapatilir


    @US19_TC04
    Scenario:TC04 - Admin Add Cat Management üzerinde yeni eklemeler yapabilmeli (Positive Scenario)
      When Add Kategori sayfasi yonetimi paneli goruntulenir
      And Admin Addition button' a tiklar
      And Admin New button' a tiklar
      And Admin Name' a "Sezar sos" girer
      And Admin Description' a "Tamamen dogal" girer
      And Admin Price' a 6 girer
      And Admin dropdown menuden Salata Soslari secer
      And Admin Add button' a tiklar
      Then Admin ekleme basarili alert' i dogrular ve onaylar
      And Sayfa kapatilir

     @US19_TC05
    Scenario:TC04 - Admin Add Cat Management üzerinde sadece price ve addition category ile kayıt ekleyememli (Negative Scenario)
       When Add Kategori sayfasi yonetimi paneli goruntulenir
       And Admin Addition button' a tiklar
       And Admin New button' a tiklar
       And Admin Price' a 7 girer
       And Admin dropdown menuden Salata Soslari secer
       Then Admin Add button' nin tiklanabilir olmadigini dogrular
