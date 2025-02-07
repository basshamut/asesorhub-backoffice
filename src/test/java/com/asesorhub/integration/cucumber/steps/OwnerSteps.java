package com.asesorhub.integration.cucumber.steps;

import com.asesorhub.controller.OwnerController;
import com.asesorhub.dto.OwnerResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
@SpringBootTest
public class OwnerSteps {

    @Autowired
    private OwnerController ownerController;

    private OwnerResponseDto ownerResponseDto;

    @Given("existe un propietario con el ID {string}")
    public void existOwnerById(String userId) {
        log.info("Ejecutado el GIVEN");

        var responseEntity = ownerController.findById(userId);
        var resultBody = responseEntity.getBody();

        // Validamos que el propietario existe
        assertThat("El propietario debería existir en la base de datos", resultBody, is(notNullValue()));
    }

    @When("el usuario consulta los detalles del propietario con ID {string}")
    public void theUserQueriesTheUserDetailsByID(String userId) {
        log.info("Ejecutado el WHEN");

        var responseEntity = ownerController.findById(userId);
        ownerResponseDto = responseEntity.getBody();

        // Validamos que se obtuvo una respuesta
        assertThat("La respuesta no debe ser nula", ownerResponseDto, is(notNullValue()));
    }

    @Then("se muestran los detalles del propietario")
    public void theUserDetailsAreDisplayed() {
        log.info("Ejecutado el THEN");

        // Validamos que el ID no sea nulo
        assertThat("El ID del propietario no debe ser nulo", ownerResponseDto.getId(), is(notNullValue()));

        // Validamos que haya información básica del propietario
        assertThat("El nombre del propietario no debe estar vacío", ownerResponseDto.getName(), is(not(emptyOrNullString())));
        assertThat("El email del propietario no debe estar vacío", ownerResponseDto.getEmail(), is(not(emptyOrNullString())));
    }
}
