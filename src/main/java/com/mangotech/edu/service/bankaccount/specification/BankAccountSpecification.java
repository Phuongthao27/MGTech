package com.mangotech.edu.service.bankaccount.specification;

import com.mangotech.edu.domain.BankAccountEntity;
import com.mangotech.edu.service.bankaccount.param.BankAccountParam;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BankAccountSpecification implements Specification<BankAccountEntity> {

    private BankAccountParam param;
    private String operator;

    @Override
    public Predicate toPredicate(Root<BankAccountEntity> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (operator.equalsIgnoreCase("LIKE")) {
            if (param.getBankName() != null) {
                predicates.add(cb.like(cb.lower(root.get("bankName")), "%" + param.getBankName().toLowerCase() + "%"));
            }
            if (param.getFacility() != null) {
                predicates.add(cb.like(cb.lower(root.get("facility")), "%" + param.getFacility().toLowerCase() + "%"));
            }
            if (param.getName() != null) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + param.getName().toLowerCase() + "%"));
            }
            if (param.getNote() != null) {
                predicates.add(cb.like(cb.lower(root.get("note")), "%" + param.getNote().toLowerCase() + "%"));
            }
            if (param.getBankAccountNumber() != null) {
                predicates.add(cb.equal(root.get("bankAccountNumber"), "%" + param.getBankAccountNumber() + "%"));
            }
            if (param.getMoney() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("money"), param.getMoney()));
            }
            if (param.getType() != null) {
                predicates.add(cb.equal(root.get("type"),  param.getType()));
            }
            return criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0]))).distinct(true).getRestriction();
        }
        return null;
    }
}
