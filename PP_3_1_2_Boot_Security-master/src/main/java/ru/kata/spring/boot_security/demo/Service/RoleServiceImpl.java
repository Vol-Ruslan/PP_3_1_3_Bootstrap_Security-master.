package ru.kata.spring.boot_security.demo.Service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public List<Role> allRoles() {
        return roleDAO.allRoles();
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleDAO.add(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        roleDAO.delete(role);
    }

    @Override
    @Transactional
    public void edit(Role role) {
        roleDAO.edit(role);
    }

    @Override
    @Transactional
    public Role getById(long id) {
        return roleDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Role> findByName(List<String> name) {
        return roleDAO.findByName(name);
    }
}
