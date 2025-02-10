Feature: Consulta de propietarios
  Como usuario
  Quiero poder consultar los detalles de un propietario
  Para obtener información relevante sobre él

  Scenario: Obtener los detalles de un propietario existente
    Given existe una cuenta con el ID "67a61503aa57e70b1dbb99a1"
    When el usuario consulta los detalles de la cuenta con ID "67a61503aa57e70b1dbb99a1"
    Then se muestran los detalles de la cuenta
