package main.java.ru.db.dao;

import main.java.ru.db.model.UserObject;

import java.util.List;

/**
 * Интерфейс работы с БД, связанный с объектами
 */
public interface UserObjectDao {

    public List<UserObject> getAll();

    public void update(UserObject entity);

    public UserObject getUserObjectByNameAndType(String name, String type);

    public boolean deleteUserObjectByNameAndType(String name, String type);

    public boolean create(UserObject entity);
}
