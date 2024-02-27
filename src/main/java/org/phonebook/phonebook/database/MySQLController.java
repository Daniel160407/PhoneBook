package org.phonebook.phonebook.database;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.phonebook.phonebook.model.Contact;

import java.util.List;

public class MySQLController implements JDBCController {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("phonebook");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private final EntityTransaction entityTransaction = entityManager.getTransaction();
    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    private final CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
    private final Root<Contact> contactRoot = criteriaQuery.from(Contact.class);


    @Override
    public List<Contact> getContacts() {
        CriteriaQuery<Contact> select = criteriaQuery.select(contactRoot);
        TypedQuery<Contact> contactTypedQuery = entityManager.createQuery(select);

        return contactTypedQuery.getResultList();
    }

    @Override
    public void addNewContact(String name, String number) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setNumber(number);

        try {
            entityTransaction.begin();

            entityManager.merge(contact);

            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        }
    }
}
