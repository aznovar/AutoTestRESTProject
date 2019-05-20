package main.java.ru.ui.steps.preconditions;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import io.qameta.allure.Step;
import main.java.ru.api.dao.DaoProviderImplementation;
import main.java.ru.api.entity.object.Attribute;
import main.java.ru.api.entity.object.BomObject;
import main.java.ru.api.entity.object.Field;
import org.apache.commons.lang3.RandomStringUtils;
import ru.api.dao.DaoProviderImplementation;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.jd.api.mapper.JsonMapperFactory.newMapper;

public class BomObjectPreconditionSteps {

    /**
     * Общий билдер для Bom объекта
     * @param name имя объекта
     * @param type тип объекта (MetaType list, refdata, event etc)
     * @param isScore объект со скорингом или нет
     * @param fields список полей объекта
     * @param attrs список атрибутов объекта
     * @return BomObject
     */
    public BomObject build(String name, MetaType type, Boolean isScore, List<Field> fields, List<Attribute> attrs){
        return BomObject.builder()
                .name(name)
                .caption(name.toLowerCase())
                .table(name.toUpperCase())
                .metaType(type)
                .databaseName(DBName.ORACLE.getName())
                .autoId(true)
                .autoScoringAttr(isScore)
                .attrs(attrs)
                .fields(fields)
                .build();
    }

    /**
     * Билдер сущности Field для Bom объекта
     * @param name имя поля
     * @param type тип поля (пока используются типы VARCHAR и NUMBER)
     * @return Field
     */
    public Field build(String name, String type){
        Field.FieldBuilder fieldBuilder = Field.builder().name(name).type(type).precision(0);
        switch (type){
            case "VARCHAR":
                fieldBuilder.size(255);
                break;
            case "NUMBER":
                fieldBuilder.size(38);
        }
        return fieldBuilder.build();
    }

    /**
     * Билдер сущности Attribute для Bom объекта
     * @param name имя атрибута
     * @param field поле, с которым связан атрибут
     * @return Attribute
     */
    public Attribute build(String name, Field field){
        return Attribute.builder()
                .name(name)
                .caption(name)
                .type("VALUE")
                .persist(true)
                .fields(Arrays.asList(field.getName()))
                .build();
    }

    /**
     * Билдер сущности Attribute для Bom объекта c ref атрибутом на справочник
     * @param name имя атрибута
     * @return Attribute
     */
    public Attribute build(String name, String type, String objName){
        return Attribute.builder()
                .name(name)
                .caption(name)
                .type(type)
                .persist(true)
                .refDict(objName)
                .build();
    }

    /**
     * Общий метод создания Bom объекта со списком полей и атрибутов
     * @param isScore со скором
     * @param metaType тип объекта (event, list, refdata и тд)
     * @param fields список полей
     * @param attrs список атрибутов
     * @return BomObject
     */
    public BomObject createBomObject(Boolean isScore, MetaType metaType, List<Field> fields, List<Attribute> attrs) {
        String objectName = new Param.ParamBuilder()
                .prefix(metaType.name())
                .maxLen(7)
                .generate()
                .build()
                .getName();
        BomObject object = build(objectName, metaType, isScore, fields, attrs);
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        return provider.create(object, newMapper());
    }

    /**
     * Метод создания Bom объекта c атрибутом идентификатором не по умолчанию
     * @param metaType тип объекта (event, list, refdata и тд)
     * @param fields список полей
     * @param attrs список атрибутов
     * @return BomObject
     */
    public BomObject createBomObject(MetaType metaType, List<Field> fields, List<Attribute> attrs, boolean autoId,
                                     String identAttr ) {
        String objectName = new Param.ParamBuilder()
                .prefix(metaType.name())
                .maxLen(7)
                .generate()
                .build()
                .getName();
        BomObject object = BomObject.builder()
                .name(objectName)
                .caption(objectName.toLowerCase())
                .table(objectName.toUpperCase())
                .metaType(metaType)
                .databaseName(DBName.ORACLE.getName())
                .autoId(autoId)
                .idAttr(identAttr)
                .autoScoringAttr(false)
                .attrs(attrs)
                .fields(fields)
                .build();
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        return provider.create(object, newMapper());
    }

    public BomObject createBomObject(MetaType metaType, List<Field> fields, List<Attribute> attrs, boolean autoId) {
        String objectName = new Param.ParamBuilder()
                .prefix(metaType.name())
                .maxLen(7)
                .generate()
                .build()
                .getName();
        BomObject object = BomObject.builder()
                .name(objectName)
                .caption(objectName.toLowerCase())
                .table(objectName.toUpperCase())
                .metaType(metaType)
                .databaseName(DBName.ORACLE.getName())
                .autoId(autoId)
                .autoScoringAttr(false)
                .attrs(attrs)
                .fields(fields)
                .build();
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        return provider.create(object, newMapper());
    }

    /**
     * Метод создания Bom объекта общего типа (без дополнительных полей и аттрибутов) с дополнительным значением в имени
     * @param name дополнтельная часть для имени объекта
     * @param isScore объект со скором
     * @param metaType тип объекта (event, list, refdata и тд)
     * @return BomObject
     */
    public BomObject createBomObject(String name, Boolean isScore, MetaType metaType) {
        BomObject object = build(metaType + "_" + name + RandomStringUtils.randomAlphanumeric(7),
                metaType, isScore, Collections.emptyList(), Collections.emptyList());
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        return provider.create(object, newMapper());
    }

    /**
     * Метод создания Bom объекта общего типа (без дополнительных полей и аттрибутов)
     * @param isScore объект со скором
     * @param metaType тип объекта (event, list, refdata и тд)
     * @return BomObject
     */
    public BomObject createBomObject(Boolean isScore, MetaType metaType) {
        String objectName = new Param.ParamBuilder()
                .prefix(metaType + "_")
                .maxLen(7)
                .generate()
                .build()
                .getName();
        BomObject object = build(objectName, metaType, isScore, Collections.emptyList(), Collections.emptyList());
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        return provider.create(object, newMapper());
    }

    /**
     * Метод создания Bom объекта для предусловий (с 1 полем и/или 1 атрибутом)
     * @param name имя объекта
     * @param type тип объекта (event, list, refdata и тд)
     * @param isAddField с дополнительным полем
     * @param isAddAttr с дополнительным атрибутом
     * @param isPersist с опцией 'Сохранять' у атрибута
     * @param isWorkflow с опцией 'Включить в документооборот'
     * @return BomObject
     */
    public BomObject createPretestBomObject(String name, MetaType type, Boolean isAddField, Boolean isAddAttr,
                                            Boolean isPersist, Boolean isWorkflow) {
        BomObject object;
        Field field = null;
        Attribute.AttributeBuilder attrBuilder = null;
        Attribute attr;
        String objectName = new Param.ParamBuilder()
                .prefix(type + "_" + name + "_")
                .maxLen(7)
                .generate()
                .build()
                .getName();
        BomObject.BomObjectBuilder objectBuilder = BomObject.builder()
                .name(objectName)
                .caption(objectName.toLowerCase())
                .table(objectName.toUpperCase())
                .metaType(type)
                .databaseName(DBName.ORACLE.getName())
                .autoId(true)
                .autoScoringAttr(false)
                .fields(Collections.emptyList())
                .attrs(Collections.emptyList());
        if(isAddField){
            field = Field.builder().name("Auto_Field_1").type("NUMBER").size(19).build();
            objectBuilder.fields(Arrays.asList(field));
        }
        if(isAddAttr){
            attrBuilder = Attribute.builder()
                    .name(TestData.BOM_OBJECT_ATTRIBUTE_NAME)
                    .caption(TestData.BOM_OBJECT_ATTRIBUTE_NAME)
                    .type("VALUE")
                    .fields(Arrays.asList(field.getName()));
            if(isPersist){
                attr = attrBuilder.persist(isPersist).build();
            }else {
                attr = attrBuilder.build();
            }
            objectBuilder.attrs(Arrays.asList(attr));
        }
        if(isWorkflow){
            object = objectBuilder.labels(Arrays.asList("document")).tableObjectAttrs(Arrays.asList("doc_state")).build();
        }else {
            object = objectBuilder.build();
        }
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        return provider.create(object, newMapper());
    }

    @Step("Step. Применить Bom объект: {obj}")
    public void applyBomObject(BomObject obj) {
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        provider.requestPost("[\"" + obj.getName() + "\"]", "/jd-bom/backend/bom/editor/apply");
    }

    @Step("Step. Создать запись в таблице Bom объекта: {obj}")
    public void createRecordInBomObjectTable(BomObject obj) {
        String  today = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
        String attrName = obj.getAttrs().stream().filter(attr -> attr.getName()
                .equals(TestData.BOM_OBJECT_ATTRIBUTE_NAME)).findFirst().get().getName();
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        String sendObj = String.format("{\"data\":{\"attrs\":{\"event_date\":{\"values\":[\"%s\"]}," +
                        "\"fraud_type_id\":{\"values\":[\"5\"]},\"%s\":{\"values\":[\"7\"]}}}, \"dictName\":\"%s\"}",
                today, attrName, obj.getName());
        provider.requestPost(sendObj, "/jd-bom/backend/dict/save");
    }

    @Step("Step. Создать запись в таблице Bom объекта: {obj}")
    public void createRecordInBomObjectTable(BomObject obj, List<String> values, String... attrs) {
        String attrName;
        String startStr = "{\"data\":{\"attrs\":{";
        String endStr = String.format("}}, \"dictName\":\"%s\"}", obj.getName());
        String finalStr = startStr;
        int i = 0;
        for (String attribute : attrs) {
            attrName = obj.getAttrs().stream().filter(attr -> attr.getName()
                    .equals(attribute)).findFirst().get().getName();
            finalStr = finalStr + String.format("\"%s\":{\"values\":[\"%s\"]}", attrName, values.get(i)) + ",";
            i++;
        }
        String sendObj = finalStr.substring(0, finalStr.length() - 1) + endStr;
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        provider.requestPost(sendObj, "/jd-bom/backend/dict/save");
    }

}
