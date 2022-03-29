package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        TypedQuery<Role> result = entityManager.createQuery("select role from Role role", Role.class);
        return result.getResultList();

    }

    public void add(Role role) {

//        User userDB = getById(user.getId());
//
//        if (userDB != null) {
//
//            return false;
//
//        }
        entityManager.persist(role);

//        return true;
    }

    public void delete(Role role) {
        Role delete = entityManager.find(Role.class, role.getId());
        entityManager.remove(delete);
    }

    public void edit(Role role) {
        entityManager.merge(role);
    }

    public Role getById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> findByName(List<String> name) {
        TypedQuery<Role> query = entityManager
                .createQuery("SELECT role FROM Role role where role.name in (:name)", Role.class);
        query.setParameter("name", name);
        return query.getResultList();

    }
}
