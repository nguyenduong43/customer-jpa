package org.example.customermanagementjpa.repository;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.example.customermanagementjpa.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Repository
@Service
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query=entityManager.createQuery("select c from Customer as c",Customer.class);
         List<Customer> customers=query.getResultList();
         return customers;
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query=entityManager.createQuery("select c from Customer as c where c.id=:id",Customer.class);
        query.setParameter("id",id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        if(customer.getId()!=null){
            entityManager.merge(customer);
        }else{
            entityManager.persist(customer);
        }
    }

    @Override
    public void remove(Long id) {
       Customer customer=findById(id);
       if(customer!=null){
           entityManager.remove(customer);
       }
    }

    @Override
    public List<Customer> search(String name) {
        String sql="CALL search(:name)";
        Query query=entityManager.createNativeQuery(sql,Customer.class);
        query.setParameter("name",name);
        List<Customer> customers=query.getResultList();
        return customers;
    }


}
