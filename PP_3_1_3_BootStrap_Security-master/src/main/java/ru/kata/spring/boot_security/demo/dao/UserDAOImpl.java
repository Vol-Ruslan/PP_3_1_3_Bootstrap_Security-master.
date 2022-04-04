package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        TypedQuery<User> result = entityManager.createQuery("select user from User user", User.class);
        return result.getResultList();

    }

    public void add(User user) {
        entityManager.persist(user);
    }

    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    public void edit(User user) {
        entityManager.merge(user);
    }

    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager
                .createQuery("SELECT user FROM User user where user.email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();

    }
}
