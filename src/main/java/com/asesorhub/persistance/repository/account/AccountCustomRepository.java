package com.asesorhub.persistance.repository.account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class AccountCustomRepository {
    private final MongoTemplate mongoTemplate;

    public Page<Account> getWithFilters(MultiValueMap<String, String> params) {

        int page = Integer.parseInt(Objects.requireNonNull(params.getFirst("page")));
        int size = Integer.parseInt(Objects.requireNonNull(params.getFirst("size")));
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        // Construcción de la consulta
        Query query = new Query();
        if (params.containsKey("role")) {
            query.addCriteria(Criteria.where("role").is(params.getFirst("role")));
        }
        if (params.containsKey("isActive") && (Objects.equals(params.getFirst("isActive"), "ACTIVE"))) {
            query.addCriteria(Criteria.where("isActive").is(true));
        }
        if (params.containsKey("isActive") && (Objects.equals(params.getFirst("isActive"), "INACTIVE"))) {
            query.addCriteria(Criteria.where("isActive").is(false));
        }
        if (params.containsKey("type") && (Objects.equals(params.getFirst("type"), "ADVISOR"))) {
            query.addCriteria(Criteria.where("isAdvisor").is(true));
        }
        if (params.containsKey("type") && (Objects.equals(params.getFirst("type"), "ADVISEE"))) {
            query.addCriteria(Criteria.where("isAdvisee").is(true));
        }
        if (params.containsKey("email")) {
            query.addCriteria(Criteria.where("email").regex(Objects.requireNonNull(params.getFirst("email")), "i"));
        }

        query.with(pageable);

        List<Account> accounts = mongoTemplate.find(query, Account.class);
        long count = mongoTemplate.count(query.skip(0).limit(0), Account.class);

        return new PageImpl<>(accounts, pageable, count);
    }
}
