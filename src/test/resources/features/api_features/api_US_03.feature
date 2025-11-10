
@cart-controller @guestToken
Feature: Sepete ürün ekleme

  Background:
    Given API base url "http://207.154.209.12:8080"
    And kullanıcı geçerli bir Guest Token'a sahiptir
    And sepet boşaltılır

  # POST ile sepete ürün eklenir, GET ile doğrulanır
  Scenario Outline: Sepete farklı ürünler ekleme ve doğrulama
    When kullanıcı sepete productId "<id>" ve quantity "<adet>" ile ürün ekler
    Then API yanıt kodu 200 olmalı
    And kullanıcı sepette eklenen ürünün göründüğünü doğrular

    Examples:
      | id | adet |
      | 1  | 1    |
      | 3  | 2    |
      | 8  | 5    |

  # PUT -> Ürün miktarı güncelleme
  Scenario Outline: Kullanıcı sepetteki ürünün miktarını günceller
    When kullanıcı sepete productId "<id>" ve quantity "<adet>" ile ürün ekler
    And kullanıcı sepetteki ürünün miktarını "<yeniAdet>" olarak günceller
    Then kullanıcı sepette ürün miktarının güncellenmiş olduğunu doğrular

    Examples:
      | id | adet | yeniAdet |
      | 1  | 1    | 5        |
      | 3  | 2    | 7        |

  # DELETE -> Ürün silme
  Scenario Outline: Kullanıcı sepetteki ürünü siler
    When kullanıcı sepete productId "<id>" ve quantity "<adet>" ile ürün ekler
    And kullanıcı sepetteki ürünü siler
    Then sepet boş olmalı

    Examples:
      | id | adet |
      | 1  | 1    |
      | 3  | 4    |
