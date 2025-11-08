@Addition_Controller
Feature: Addition API Testleri

  Background:
    Given API base url "http://207.154.209.12:8080"

  @AdditionGet
  @adminToken
  Scenario: Tüm addition'lar listelenebilmeli
    When Admin "GET" isteğini "/addition" endpointine gönderir
    Then Gelen Status kodu 200 olmalı

  @AdditionPut
  @adminToken
  Scenario: Admin bir addition güncelleyebilmeli
    Given Admin güncellenecek addition'i hazırlar
    When Admin "PUT" isteğini "/addition" endpointine gönderir
    Then Gelen Status kodu 200 olmalı
    And Addition güncellendigi dogrulanir

  @AdditionPost
  @adminToken
  Scenario: Yeni addition oluştur
    Given Yeni bir addition hazırlar
    When Admin "POST" isteğini "/addition" endpointine gönderir
    Then Gelen Status kodu 200 olmalı
    And Addition başarıyla oluşturulmalı

  @AdditionDelete
  @adminToken
  Scenario: Kullanıcı bir addition silebilmeli
    # POST ile oluşturulan addition ID'si kullanılarak silinecek
    When Kullanıcı bir addition siler
    Then Gelen Status kodu 200 olmalı
