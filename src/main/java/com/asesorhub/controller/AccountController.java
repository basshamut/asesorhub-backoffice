package com.asesorhub.controller;

import com.asesorhub.dto.AccountRequestDto;
import com.asesorhub.dto.AccountResponseDto;
import com.asesorhub.dto.LoginRequestJson;
import com.asesorhub.dto.LoginResponseJson;
import com.asesorhub.service.user.AccountService;
import com.asesorhub.service.user.JwtService;
import com.asesorhub.utils.enums.AccountType;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.asesorhub.utils.Constants.API_VERSION_PATH;

@Tag(name = "Accounts", description = "Account operations")
@RestController
@RequestMapping(value = API_VERSION_PATH)
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseJson> login(@RequestBody @NotNull LoginRequestJson loginRequestJson) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestJson.getUsername(), loginRequestJson.getPassword()));
        if (authentication.isAuthenticated()) {
            var loginResponseJson = LoginResponseJson.builder()
                    .type("Bearer")
                    .token(jwtService.generateToken(authentication))
                    .build();
            return ResponseEntity.ok().body(loginResponseJson);
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponseDto> saveUser(@RequestBody @NotNull AccountRequestDto accountRequestDto, @RequestParam("type") AccountType type) {
        var user = accountService.saveUser(accountRequestDto, type);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/accounts/{email}")
    public ResponseEntity<Void> assignAccountType(@RequestParam("type")AccountType type, @PathVariable String email) {
        accountService.assignAccountType(email, type);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<AccountResponseDto> updateById(@PathVariable String id, @RequestBody AccountRequestDto accountRequestDto) {
        var updatedAdvisee = accountService.updateById(id, accountRequestDto);
        return new ResponseEntity<>(updatedAdvisee, HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        accountService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/accounts/search")
    @Parameters({
            @Parameter(name = "page", description = "Número de página", required = true),
            @Parameter(name = "size", description = "Tamaño de la página", required = true),
            @Parameter(name = "email", description = "Filtrar por email"),
            @Parameter(
                    name = "type",
                    description = "Filtrar por tipo de cuenta",
                    schema = @Schema(allowableValues = {"ADVISOR", "ADVISEE"})
            ),
            @Parameter(
                    name = "active",
                    description = "Filtrar por estado de cuenta",
                    schema = @Schema(allowableValues = {"ACTIVE", "INACTIVE"})
            )
    })
    public ResponseEntity<Page<AccountResponseDto>> getAccounts(@Parameter(description = "Query parameters for filtering accounts") @RequestParam(required = false) MultiValueMap<String, String> params) {
        var advisees = accountService.getWithFilters(params);
        return new ResponseEntity<>(advisees, HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountResponseDto> findById(@PathVariable String id) {
        var advisee = accountService.getById(id);
        return new ResponseEntity<>(advisee, HttpStatus.OK);
    }

}
