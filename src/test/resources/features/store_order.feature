Feature: Validación del API de Store en PetStore

  @test1
    @validarCreacionOrden
  Scenario Outline: Creación y consulta de una orden en la tienda
    When creo una orden en la tienda con los siguientes datos
      | id        | <id>        |
      | petId     | <petId>     |
      | quantity  | <quantity>  |
      | shipDate  | <shipDate>  |
      | status    | <status>    |
      | complete  | <complete>  |
    Then el código de respuesta de la creación de la orden es 200
    And la respuesta de la creación de la orden contiene los datos enviados

    When consulto la orden con ID <id>
    Then el código de respuesta de la consulta de la orden es 200
    And la orden contiene los siguientes datos
      | id        | <id>        |
      | petId     | <petId>     |
      | quantity  | <quantity>  |
      | shipDate  | <shipDate>  |
      | status    | <status>    |
      | complete  | <complete>  |

    Examples:
      | id  | petId | quantity | shipDate                | status    | complete |
      | 1   | 1     | 3        | 2024-08-28T09:00:00.000+0000 | approved  | true     |
      | 2   | 2     | 2        | 2024-08-29T10:00:00.000+0000 | delivered | false    |

  Scenario: Intentar crear una orden con datos inválidos
    When intento crear una orden en la tienda con los siguientes datos
      | id       | invalid       |
      | petId    | 1             |
      | quantity | three         |
      | shipDate | invalid-date  |
      | status   | unknown       |
      | complete | yes           |
    Then el código de respuesta de la creación fallida de la orden es 500
    And el mensaje de error es "something bad happened"