package main.java.ru.db.dao;

import ru.jd.db.hibernate.model.cep_coordinator.RulesGroup;

import java.util.List;
import java.util.Optional;

public interface RulesGroupDao {

    public List<RulesGroup> getAll();

    public void update(RulesGroup entity);

    public Optional<RulesGroup> getRulesGroupByName(String name);

    public boolean deleteGroupOfRuleByName(String name);

    public boolean create(RulesGroup entity);
}


