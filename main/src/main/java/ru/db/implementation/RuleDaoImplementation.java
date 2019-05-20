package main.java.ru.db.implementation;

import main.java.ru.db.dao.RuleDao;
import main.java.ru.db.model.Rule;

import java.util.List;
import java.util.Optional;

public class RuleDaoImplementation implements RuleDao {

    @Override
    public List<Rule> getAll() {
        try (Session session = HibernateClient.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("from Rule").list();
        }
    }

    @Override
    public Optional<Rule> getRuleByName(String name) {
        List<Rule> groups = getAll();
        return groups.stream().filter(rule -> rule.getName().equals(name)).findFirst();
    }

    @Override
    public void update(Rule entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteRuleByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Rule entity) {
        throw new UnsupportedOperationException();
    }
}
