package main.java.ru.db;

import main.java.ru.db.dao.RuleDao;
import main.java.ru.db.dao.RulesGroupDao;
import main.java.ru.db.dao.UserObjectDao;
import main.java.ru.db.implementation.RuleDaoImplementation;
import main.java.ru.db.implementation.RulesGroupDaoImplementation;
import main.java.ru.db.implementation.UserObjectImplementation;

/**
 * Класс геттеров к имплементациям DAO сущностей
 */
public class DaoFactory {

    private static DaoFactory instance = null;
    private static UserObjectDao userObjectDao = null;
    private static RuleDao ruleDao = null;
    private static RulesGroupDao rulesGroupDao = null;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public RulesGroupDao getRulesGroupDao() {
        if (rulesGroupDao == null) {
            rulesGroupDao = new RulesGroupDaoImplementation();
        }
        return rulesGroupDao;
    }

    public UserObjectDao geUserObjectDao() {
        if (userObjectDao == null) {
            userObjectDao = new UserObjectImplementation();
        }
        return userObjectDao;
    }

    public RuleDao getRuleDao() {
        if (ruleDao == null) {
            ruleDao = new RuleDaoImplementation();
        }
        return ruleDao;
    }

}
