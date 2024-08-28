Feature: Crear usuario PetStore

  @test1
  @crearUsuario
  Scenario: Crear usuario
    When creo el usuario con username "jperez", firstname "Juan", lastname "Perez"
    Then el código de respuesta es 200
    And el type es "unknown"

  @test2
  @buscarUsuario
  Scenario: buscar usuario
    When busco el usuario con username "jperez"
    Then el código de respuesta es 200
    And el username es "jperez"

  @test3
  @modificarUsuario
  Scenario: Actualizar el nombre de usuario
    Given el usuario con username "jperez"
    When modifico el usuario con username "jgoyo"
    Then el código de respuesta es 200

  @test4
  @actualizarUsuarioMasivo
  Scenario Outline: Modifico varios datos de usuario de forma masiva
    Given consulto el usuario con nombre "<userNameOld>"
    When actualizo el usuario "<userNameOld>" con nuevo username "<userNameNew>", firstname "<firstName>", lastname "<lastName>", email "<email>", phone "<phone>"
    Then el código de respuesta es 200

    Examples:
      | userNameOld | userNameNew  | firstName | lastName | email             | phone       |
      | jlucas      | userNameNew1 | Lucas     | Juarez   | lucas@example.com | 1234567890  |
