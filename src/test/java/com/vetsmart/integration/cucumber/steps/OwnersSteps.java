package com.vetsmart.integration.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetsmart.controller.OwnerController;
import com.vetsmart.dto.OwnerResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.vetsmart.integration.cucumber.config.CucumberSpringConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ContextConfiguration(classes = {CucumberSpringConfiguration.class, OwnerController.class})
public class OwnersSteps {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OwnerController ownerController;

    private OwnerResponseDto ownerResponseDto;

    @Given("existe un propietario con el ID {int}")
    public void existUserById(long userId) throws Exception {
        System.out.println("Ejecutado el GIVEN");

        var responseEntity = ownerController.findById(userId);
        var resultBody = responseEntity.getBody();

        assertThat(resultBody, is(notNullValue()));
    }

    @When("el usuario consulta los detalles del propietario con ID {int}")
    public void elUsuarioConsultaLosDetallesDelUsuarioConID(long userId) throws Exception {
        System.out.println("Ejecutado el WHEN");
        ownerResponseDto = ownerController.findById(userId).getBody();
    }

    @Then("se muestran los detalles del propietario")
    public void seMuestranLosDetallesDelUsuario() throws UnsupportedEncodingException, JsonProcessingException {
        System.out.println("Ejecutado el THEN");
        assertThat(ownerResponseDto.getId(), is(notNullValue()));
        assertThat(ownerResponseDto.getName(), is("John Doe"));
        assertThat(ownerResponseDto.getEmail(), is("john.doe@example.com"));
    }
}