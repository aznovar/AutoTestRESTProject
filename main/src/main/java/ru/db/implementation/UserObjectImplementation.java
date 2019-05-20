package main.java.ru.db.implementation;

import main.java.ru.db.dao.UserObjectDao;
import main.java.ru.db.model.UserObject;
import main.java.ru.db.model.UserObjectPK;

import java.util.List;

public class UserObjectImplementation implements UserObjectDao {

    @Override
    public List<UserObject> getAll() {
        try (Session session = HibernateClient.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("from UserObject").list();
        }
    }

    @Override
    public void update(UserObject entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserObject getUserObjectByNameAndType(String name, String type) {
        try (Session session = HibernateClient.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            UserObjectPK key = new UserObjectPK(name, type);
            return session.load(UserObject.class, key);
        }
    }

    @Override
    public boolean deleteUserObjectByNameAndType(String name, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(UserObject entity) {
        throw new UnsupportedOperationException();
    }
}
