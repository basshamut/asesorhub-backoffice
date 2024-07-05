Feature: Consulta de naves espaciales
  Como usuario
  Quiero poder consultar los detalles de un propietario

  Scenario: Consultar un propietario
    Given existe un propietario con el ID 1
    When el usuario consulta los detalles del propietario con ID 1
    Then se muestran los detalles del propietario
