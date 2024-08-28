Feature: Consultar inventario de la tienda

  @test1
  @consultaInventario
  Scenario: Consultar inventario de la tienda por status
    When consulto el inventario de la tienda
    Then el código de respuesta del inventario es 200
    And el inventario contiene las propiedades adicionales

