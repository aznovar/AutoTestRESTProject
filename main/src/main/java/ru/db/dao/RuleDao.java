package main.java.ru.db.dao;

import main.java.ru.db.model.Rule;

import java.util.List;
import java.util.Optional;

public interface RuleDao {

    public List<Rule> getAll();

    public void update(Rule entity);

    public Optional<Rule> getRuleByName(String name);

    public boolean deleteRuleByName(String name);

    public boolean create(Rule entity);
}
