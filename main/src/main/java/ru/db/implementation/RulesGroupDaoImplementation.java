package main.java.ru.db.implementation;

import main.java.ru.api.entity.rules.RulesGroup;
import main.java.ru.db.dao.RulesGroupDao;
import org.hibernate.Session;


import java.util.List;
import java.util.Optional;

public class RulesGroupDaoImplementation implements RulesGroupDao {

    @Override
    public List<RulesGroup> getAll() {
        try (Session session = HibernateClient.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("from RulesGroup").list();
        }
    }

    @Override
    public Optional<RulesGroup> getRulesGroupByName(String name) {
        List<RulesGroup> groups = getAll();
        return groups.stream().filter(group -> group.getName().equals(name)).findFirst();
    }

    @Override
    public void update(RulesGroup entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteGroupOfRuleByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(RulesGroup entity) {
        throw new UnsupportedOperationException();
    }
}
