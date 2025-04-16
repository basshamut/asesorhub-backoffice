package com.asesorhub.integration.cucumber.steps;

import com.asesorhub.controller.AccountController;
import com.asesorhub.dto.AccountResponseDto;
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
public class AdvisorSteps {

    @Autowired
    private AccountController accountController;

    private AccountResponseDto accountResponseDto;

    @Given("existe una cuenta con el ID {string}")
    public void existAdvisorById(String userId) {
        log.info("Ejecutado el GIVEN");

        var responseEntity = accountController.findById(userId);
        var resultBody = responseEntity.getBody();

        // Validamos que el propietario existe
        assertThat("La cuenta debería existir en la base de datos", resultBody, is(notNullValue()));
    }

    @When("el usuario consulta los detalles de la cuenta con ID {string}")
    public void theAdvisorDetailsByID(String userId) {
        log.info("Ejecutado el WHEN");

        var responseEntity = accountController.findById(userId);
        accountResponseDto = responseEntity.getBody();

        // Validamos que se obtuvo una respuesta
        assertThat("La respuesta no debe ser nula", accountResponseDto, is(notNullValue()));
    }

    @Then("se muestran los detalles de la cuenta")
    public void theAdvisorDetailsAreDisplayed() {
        log.info("Ejecutado el THEN");

        // Validamos que el ID no sea nulo
        assertThat("El ID de la cuenta no debe ser nulo", accountResponseDto.getId(), is(notNullValue()));

        // Validamos que haya información básica del propietario
        assertThat("El nombre de la cuenta no debe estar vacío", accountResponseDto.getName(), is(not(emptyOrNullString())));
        assertThat("El email de la cuenta no debe estar vacío", accountResponseDto.getUsername(), is(not(emptyOrNullString())));
    }
}
