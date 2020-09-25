package com.vladislav.crm.services.operations;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateContactOperationImpl implements CreateContactOperation {

    private final ContactRepository contactRepository;

    @Override
    public Contact execute(Contact contact) {
        contact.setId(null);  // вопрос: правилен ли такой подход? не лучше ли проверять наличие записи с таким id? по идее при создании нам не важно какой id приходит снаружи поэтому его можно спокойно занулить
        return contactRepository.save(contact);
    }
}
