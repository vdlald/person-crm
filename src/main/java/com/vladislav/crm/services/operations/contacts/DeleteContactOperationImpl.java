package com.vladislav.crm.services.operations.contacts;

import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteContactOperationImpl implements DeleteContactOperation {

    private final ContactRepository contactRepository;

    @Override
    public void execute(Long id) {
        final Contact contact = contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        contact.getLeads().forEach(contact::removeLead);
        contact.setUser(null);
        if (contact.getCompany().getContacts().isEmpty()) {
            // todo: delete company
        }
        contactRepository.delete(contact);
    }
}
