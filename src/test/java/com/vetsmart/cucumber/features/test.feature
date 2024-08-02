Feature: Consulta de pacientes
  Como usuario
  Quiero poder consultar los detalles de un paciente

  Scenario: Consultar un propietario
    Given existe un paciente cuyo propietario es "test@test.com"
    When el usuario consulta los detalles del paciente cuyo propietario es "test@test.com"
    Then se muestran los detalles del paciente
