package com.asesorhub.service.user;

import com.asesorhub.config.security.PasswordEncoderConfig;
import com.asesorhub.dto.AccountRequestDto;
import com.asesorhub.dto.AccountResponseDto;
import com.asesorhub.exception.ServiceException;
import com.asesorhub.persistance.repository.account.AccountCustomRepository;
import com.asesorhub.service.user.mapper.AccountMapper;
import com.asesorhub.utils.ControllerUtils;
import com.asesorhub.utils.PasswordUtils;
import com.asesorhub.utils.enums.AccountType;
import lombok.AllArgsConstructor;
import com.asesorhub.dto.AuthDto;
import com.asesorhub.persistance.repository.account.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountCustomRepository accountCustomRepository;
    private final PasswordUtils passwordUtils;

    public AuthDto loadUserByUsername(String username) {
        //TODO enchufar aqui Auth0
        final Set<String> authorities = new HashSet<>();

        var user = accountRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            authorities.add("ROLE_" + user.getRole());
            var pass = new String(Base64.getDecoder().decode(user.getPassword()));

            return new AuthDto(user.getEmail(), pass, authorities);
        }

        return null;
    }

    public AccountResponseDto saveUser(AccountRequestDto accountRequestDto, AccountType type) {
        var user = accountRepository.findByEmail(accountRequestDto.getUsername());

        if (Objects.isNull(user)) {
            var passInBase64 = passwordUtils.buildPassword(accountRequestDto.getPassword());

            var newUser = AccountMapper.MAPPER.mapToEntity(accountRequestDto);
            newUser.setPassword(passInBase64);
            newUser.setIsAdvisee(type.equals(AccountType.ADVISEE));
            newUser.setIsAdvisor(type.equals(AccountType.ADVISOR));
            newUser.setIsActive(true);
            newUser.setCreatedAt(new Date());
            newUser.setUpdatedAt(null);
            newUser.setRole("USER");

            var userSaved = accountRepository.save(newUser);
            return AccountMapper.MAPPER.mapToDto(userSaved);
        }

        throw new ServiceException("User already exists", 400);
    }

    public Page<AccountResponseDto> getWithFilters(MultiValueMap<String, String> params) {
        if (!ControllerUtils.isValidPagination(params.getFirst("page"), params.getFirst("size"))) {
            throw new ServiceException("Invalid pagination parameters", HttpStatus.BAD_REQUEST.value());
        }

        var pageable = Pageable
                .ofSize(Integer.parseInt(Objects.requireNonNull(params.getFirst("size"))))
                .withPage(Integer.parseInt(Objects.requireNonNull(params.getFirst("page"))));

        var list = accountCustomRepository.getWithFilters(params);

        return new PageImpl<>(list.getContent().stream().map(AccountMapper.MAPPER::mapToDto).toList(), pageable, list.getTotalElements());
    }

    public AccountResponseDto getById(String id) {
        return accountRepository.findById(id)
                .map(AccountMapper.MAPPER::mapToDto)
                .orElseThrow(() -> new ServiceException("Advisor not found", HttpStatus.NOT_FOUND.value()));
    }

    public AccountResponseDto updateById(String id, AccountRequestDto advisorDetails) {
        return accountRepository.findById(id).map(advisor -> {
            advisor.setName(advisorDetails.getName() != null ? advisorDetails.getName() : advisor.getName());
            advisor.setPhone(advisorDetails.getPhone() != null ? advisorDetails.getPhone() : advisor.getPhone());
            advisor.setIsActive(advisorDetails.getIsActive() != null ? advisorDetails.getIsActive() : advisor.getIsActive());
            advisor.setUpdatedAt(new Date());
            var updatedAdvisor = accountRepository.save(advisor);
            return AccountMapper.MAPPER.mapToDto(updatedAdvisor);
        }).orElseThrow(() -> new ServiceException("Advisor not found", HttpStatus.NOT_FOUND.value()));
    }

    public void deleteById(String id) {
        var advisor = accountRepository.findById(id);
        if (advisor.isPresent()) {
            advisor.get().setIsActive(false);
            accountRepository.save(advisor.get());
        } else {
            throw new ServiceException("Advisor not found", HttpStatus.NOT_FOUND.value());
        }
    }

    public void assignAccountType(String email, AccountType type) {
        var user = accountRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            throw new ServiceException("User not found", HttpStatus.NOT_FOUND.value());
        }

        accountCustomRepository.applyAccountType(user, type);
    }
}
