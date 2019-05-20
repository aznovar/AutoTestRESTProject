package main.java.ru.api.dao;

import main.java.ru.api.restassured.APIControl;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;

public class DaoProviderImplementation<T> implements DaoProvider<T> {

    private APIControl apiControl;
    private String serverUrl;
    private Response response;

    public DaoProviderImplementation(String serverUrl) {
        this.serverUrl = serverUrl;
        apiControl = new APIControl();
        apiControl.requestToken(serverUrl);
    }

    @Override
    public T create(T obj, ObjectMapper mapper) {
        String sendObj = serialize(mapper, obj);
        response = requestPost(sendObj, obj.singleEntityUrl());
        if (response.asString().equals("")) {
            return deserialize(mapper, obj, requestGet(obj.entityByIdUrl()).asString());
        }
        return deserialize(mapper, obj, response.asString());
    }

    @Override
    public T get(T obj, ObjectMapper mapper) {
        response = requestGet(obj.entityByIdUrl());
        return deserialize(mapper, obj, response.asString());
    }

    @Override
    public T update(T obj, ObjectMapper mapper) {
        String sendObj = serialize(mapper, obj);
        response = requestPut(sendObj, obj.entityByIdUrl());
        return deserialize(mapper, obj, response.asString());
    }

    @Override
    public Response delete(T obj, ObjectMapper mapper) {
        String sendObj = serialize(mapper, obj);
        response = requestDelete(sendObj, obj.entityByIdUrl());
        return response;
    }

    @Override
    public List<T> list(T obj, ObjectMapper mapper) throws IOException {
        JavaType listType = getListType(mapper, obj);
        response = requestGet(obj.multipleEntitiesUrl());
        return deserializeList(mapper, obj, response.asString(), listType);
    }
}
