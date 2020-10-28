package com.vladislav.crm.services.operations.companies.impl;

import com.vladislav.crm.entities.Company;
import com.vladislav.crm.services.operations.DeleteOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteCompanyOperationImpl implements DeleteOperation<Company> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void execute(Long id) {
        entityManager.flush();
        entityManager.clear();

        final Query query = entityManager.createQuery(
                "update Contact contact set contact.company = null where contact.company.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        final Query deleteQuery = entityManager.createQuery("delete from Company c where c.id = :id");
        deleteQuery.setParameter("id", id);
        deleteQuery.executeUpdate();
    }
}
