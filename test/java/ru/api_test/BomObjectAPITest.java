package ru.api_test;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import main.java.ru.api.dao.DaoProviderImplementation;
import main.java.ru.api.entity.object.BomObject;
import main.java.ru.api.enums.MetaType;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.List;


@Test(groups = {"smoke"})
public class BomObjectAPITest {

    @Test(description = "Пример создания рандомного объекта типа Событие", timeOut = 60000)
    @AutoTest(testRailId = 409072)
    public void testAPICreateRandomEventBomObject() {
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        BomObject template = BomObject.builder().randomize().metaType(MetaType.event).build();
        BomObject response = provider.create(template, newMapper());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getName()).isEqualTo(template.getName());
        softly.assertThat(response.getTable()).isEqualTo(template.getTable());
        softly.assertThat(response.getCaption()).isEqualTo(template.getCaption());
        softly.assertThat(response.getMetaType()).isEqualTo(template.getMetaType());
        softly.assertThat(response.getDatabaseName()).isEqualTo(template.getDatabaseName());
        softly.assertAll();
    }

    @Test(description = "Пример создания рандомного объекта типа Список", timeOut = 60000)
    @AutoTest(testRailId = 409073)
    public void testAPICreateRandomListBomObject() {
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        BomObject template = BomObject.builder().randomize().metaType(MetaType.list).build();
        BomObject response = provider.create(template, newMapper());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getName()).isEqualTo(template.getName());
        softly.assertThat(response.getTable()).isEqualTo(template.getTable());
        softly.assertThat(response.getCaption()).isEqualTo(template.getCaption());
        softly.assertThat(response.getMetaType()).isEqualTo(template.getMetaType());
        softly.assertThat(response.getDatabaseName()).isEqualTo(template.getDatabaseName());
        softly.assertAll();
    }

    @Test(description = "Пример создания рандомного объекта типа Справочник", timeOut = 60000)
    @AutoTest(testRailId = 409074)
    public void testAPICreateRandomRefDataBomObject() {
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        BomObject template = BomObject.builder().randomize().metaType(MetaType.refdata).build();
        BomObject response = provider.create(template, newMapper());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getName()).isEqualTo(template.getName());
        softly.assertThat(response.getTable()).isEqualTo(template.getTable());
        softly.assertThat(response.getCaption()).isEqualTo(template.getCaption());
        softly.assertThat(response.getMetaType()).isEqualTo(template.getMetaType());
        softly.assertThat(response.getDatabaseName()).isEqualTo(template.getDatabaseName());
        softly.assertAll();
    }

    @Test(description = "Пример создания рандомного объекта типа Обучающие выборки", timeOut = 60000)
    @AutoTest(testRailId = 409075)
    public void testAPICreateRandomTrainDataBomObject() {
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        BomObject template = BomObject.builder().randomize().metaType(MetaType.traindata).build();
        BomObject response = provider.create(template, newMapper());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getName()).isEqualTo(template.getName());
        softly.assertThat(response.getTable()).isEqualTo(template.getTable());
        softly.assertThat(response.getCaption()).isEqualTo(template.getCaption());
        softly.assertThat(response.getMetaType()).isEqualTo(template.getMetaType());
        softly.assertThat(response.getDatabaseName()).isEqualTo(template.getDatabaseName());
        softly.assertAll();
    }

    @Test(description = "Пример создания рандомного объекта типа Уровень приложения", timeOut = 60000)
    @AutoTest(testRailId = 409076)
    public void testAPICreateRandomApplicationBomObject() {
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        BomObject template = BomObject.builder().randomize().metaType(MetaType.application).build();
        BomObject response = provider.create(template, newMapper());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getName()).isEqualTo(template.getName());
        softly.assertThat(response.getTable()).isEqualTo(template.getTable());
        softly.assertThat(response.getCaption()).isEqualTo(template.getCaption());
        softly.assertThat(response.getMetaType()).isEqualTo(template.getMetaType());
        softly.assertThat(response.getDatabaseName()).isEqualTo(template.getDatabaseName());
        softly.assertAll();
    }

    @Test(description = "Пример получения списка всех существующих объектов", timeOut = 60000)
    @AutoTest(testRailId = 410886)
    public void testAPIGetBomObjectList() throws IOException {
        // Создание Bom объектов
        DaoProviderImplementation<BomObject> provider = new DaoProviderImplementation<>(Const.URL);
        BomObject template = BomObject.builder().randomize().metaType(MetaType.event).build();
        BomObject createdEvent = provider.create(template, newMapper());

        // Проверка списка Bom объектов
        List<BomObject> response = provider.list(BomObject.builder().build(), configureMapper());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response).isNotEmpty();
        softly.assertThat(response.size()).isNotNull();
        softly.assertAll();
    }
}
