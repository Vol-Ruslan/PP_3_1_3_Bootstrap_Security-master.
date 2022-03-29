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

//        User userDB = getById(user.getId());
//
//        if (userDB != null) {
//
//            return false;
//
//        }
        entityManager.persist(user);

//        return true;
    }

    public void delete(User user) {
        User delete = entityManager.find(User.class, user.getId());
        entityManager.remove(delete);
    }

    public void edit(User user) {
        entityManager.merge(user);
    }

    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        TypedQuery<User> query = entityManager
                .createQuery("SELECT user FROM User user where user.name = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();

    }
}
