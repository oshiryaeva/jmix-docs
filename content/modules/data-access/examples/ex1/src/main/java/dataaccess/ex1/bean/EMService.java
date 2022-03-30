package dataaccess.ex1.bean;

import dataaccess.ex1.entity.Customer;
import dataaccess.ex1.entity.Order;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.Metadata;
import io.jmix.data.PersistenceHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Component("sample_EMService")
public class EMService {

    @Autowired
    private Metadata metadata;

    // tag::em-inject[]
    @PersistenceContext
    private EntityManager entityManager;
    // end::em-inject[]

    // tag::em-persist[]
    @Transactional
    public Customer createCustomer() {
        Customer customer = metadata.create(Customer.class);
        customer.setName("Bob");
        entityManager.persist(customer);
        return customer;
    }
    // end::em-persist[]

    // tag::em-find[]
    @Autowired
    private FetchPlans fetchPlans;

    @Transactional
    public Order findOrder(UUID uuid) {
        FetchPlan fetchPlan = fetchPlans.builder(Order.class)
                .addFetchPlan(FetchPlan.LOCAL)
                .add("customer.name").build();
        return entityManager.find(Order.class, uuid, PersistenceHints.builder()
                .withFetchPlan(fetchPlan)
                .build());
    }
    // end::em-find[]


}