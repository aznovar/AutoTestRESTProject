package main.java.ru.api.dao;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;

public interface DaoProvider<T> {

    T create(T obj, ObjectMapper mapper) throws IOException;

    T get(T obj, ObjectMapper mapper) throws IOException;

    T update(T obj, ObjectMapper mapper) throws IOException;

    Response delete(T obj, ObjectMapper mapper) throws JsonProcessingException;

    List<T> list(T obj, ObjectMapper mapper) throws IOException, CoreProblem;
}