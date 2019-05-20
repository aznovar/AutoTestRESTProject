package main.java.ru.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.jd.api.entity.rule.subentity.FilterExpr;
import ru.jd.api.mapper.custom.rule.FilterExprDeserializer;
import ru.jd.sup.single.Const;

import java.io.IOException;

public class JsonMapperFactory {

    private JsonMapperFactory() {
    }

    public static ObjectMapper newMapper(final SimpleModule module) {
        return newMapper(newMapper(), module);
    }

    public static ObjectMapper newMapper(final ObjectMapper mapper, final SimpleModule module) {
        return mapper.registerModule(module);
    }

    public static ObjectMapper newMapper() {
        return new ObjectMapper().registerModule(
                new SimpleModule()
                        .addDeserializer(FilterExpr.class, new FilterExprDeserializer()));
    }

    public static ObjectMapper mapperConfigure(){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    public static ObjectMapper configureMapper(){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
    }

    public static String getResponseAttr(String response, String attr) {
        JsonNode rootNode = null;
        try {
            rootNode = newMapper().readTree(response);
        } catch (IOException e) {
            Const.rethrow("Не удалось прочитать response.", e);
        }
        JsonNode jsonNode = rootNode.get(attr);
        return jsonNode.asText();
    }
}