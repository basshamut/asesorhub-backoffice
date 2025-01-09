Feature: Consulta de pacientes
  Como usuario
  Quiero poder consultar los detalles de un paciente

  Scenario: Consultar un propietario
    Given existe un paciente cuyo propietario es "juan.perez@ejemplo.com"
    When el usuario consulta los detalles del paciente cuyo propietario es "juan.perez@ejemplo.com"
    Then se muestran los detalles del paciente
