package com.vetsmart.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetsmart.controller.PatientController;
import com.vetsmart.cucumber.config.CucumberSpringConfiguration;
import com.vetsmart.dto.PatientDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ContextConfiguration(classes = {CucumberSpringConfiguration.class, PatientController.class})
public class OwnersSteps {

    @Autowired
    private PatientController patientController;

    private PatientDto patientDto;

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
        assertThat(patientDto.getName(), is("Fifi"));
        assertThat(patientDto.getSpecies(), is("Dog"));
    }
}