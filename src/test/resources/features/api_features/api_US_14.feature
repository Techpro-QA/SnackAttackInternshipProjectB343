Feature: SSE Event Dinleme

  Scenario: Kullanıcı SSE akışını dinleyebilmeli
    And Kullanıcı SSE "order-sse/subscribe" endpointine bağlanır ve eventleri dinler
