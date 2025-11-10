@Email_Controller
Feature: Email Controller API'sindeki email gönderme işleminin test edilmesi

  # POST /email/send
  # Bu senaryoda adminin, geçerli bir kullanıcıya email gönderme işlemi doğrulanır
  @PostEmailSend
  @adminToken
  Scenario: Admin geçerli bir kullanıcıya email gönderebilmeli
    Given "EmailSend" endpointine baglanti kurulur
    When Admin geçerli kullanıcıya email gönderir ve response alınır
    Then Status codenun 200 oldugu dogrulanir
    And Response body icinde basarili email gönderimi mesaji dogrulanmali

