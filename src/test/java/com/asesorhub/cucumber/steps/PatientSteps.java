package com.asesorhub.cucumber.steps;

import com.asesorhub.controller.PatientController;
import com.asesorhub.cucumber.config.CucumberSpringConfiguration;
import com.asesorhub.dto.PatientDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ContextConfiguration(classes = {CucumberSpringConfiguration.class, PatientController.class})
public class PatientSteps {

    @Autowired
    private PatientController patientController;

    private Set<PatientDto> patientDto;

    @Given("existe un paciente cuyo propietario es {string}")
    public void existPatientByEmail(String email) {
        var responseEntity = patientController.findByEmail(email);
        var resultBody = responseEntity.getBody();

        assertThat("El paciente debe existir", resultBody, notNullValue());
    }

    @When("el usuario consulta los detalles del paciente cuyo propietario es {string}")
    public void userRequestsPatientDetailsByEmail(String email) {
        patientDto = patientController.findByEmail(email).getBody();
    }

    @Then("se muestran los detalles del paciente")
    public void verifyPatientDetails() {
        assertThat("Los detalles del paciente deben ser mostrados", patientDto, notNullValue());
        assertThat("Los detalles del paciente deben ser mostrados", patientDto.size(), greaterThan(0));
        for (PatientDto patient : patientDto) {
            assertThat("El paciente debe tener un nombre", patient.getName(), notNullValue());
            assertThat("El paciente debe tener una especie", patient.getSpecies(), notNullValue());
            assertThat("El paciente debe tener una raza", patient.getBreed(), notNullValue());
        }
    }
}