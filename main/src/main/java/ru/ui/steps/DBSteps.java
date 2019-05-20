package main.java.ru.ui.steps;

import io.qameta.allure.Step;
import main.java.ru.db.DaoFactory;
import main.java.ru.db.model.Rule;
import ru.jd.api.enums.StatusEnum;
import ru.jd.db.hibernate.DaoFactory;
import ru.jd.db.hibernate.model.UserObject;
import ru.jd.db.hibernate.model.WorkflowModel;
import ru.jd.db.hibernate.model.aggregate.AggregateSnapshot;
import ru.jd.db.hibernate.model.aggregate.ManageAggregate;
import ru.jd.db.hibernate.model.cep_coordinator.*;
import ru.jd.db.hibernate.model.dict_service.Action;
import ru.jd.db.hibernate.model.dict_service.Engine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Класс шагов взаимодействия с БД
 */
public class DBSteps {

    //  Базовые шаги работы с БД
    @Step("Check. Таблица с именем:  {tableName} есть в БД")
    public void checkTableExistsInDB(String tableName, boolean expected) {
        boolean flag = DaoFactory.getInstance().getBaseDao().tableExistence(tableName);
        assertThat(String.format("Таблица с именем '%s' отсутствует в БД.", tableName), flag, equalTo(expected));
    }

    //  Шаги работы с Объектами в БД (таблица metadata_head)

    @Step("Check. Проверка созданного Объекта в БД по имени: {name} и типу: {type}")
    public void checkNewObjectInDBByNameAndType(String name, String type) {
        UserObject object = DaoFactory.getInstance().geUserObjectDao().getUserObjectByNameAndType(name, type);
        assertThat("Искомый объект отсутствует в БД.", object.getUserObjectPK().getName(), equalTo(name));
    }

    @Step("Step. Получить количество объектов")
    public Integer getObjectsCountInDB() {
        return DaoFactory.getInstance().geUserObjectDao().getAll().size();
    }


    // Шаги по работе с Правилами в БД (таблица cep.rule)
    @Step("Check.Проверка созданного правила в БД по имени: {ruleName}")
    public void checkRuleInDB(String ruleName) {
        Optional<Rule> rule =
                DaoFactory.getInstance().getRuleDao().getRuleByName(ruleName);
        assertThat("Искомое правило отсутствует в БД.", rule.isPresent(), is(true));
        assertThat("Правило имеет неверное имя.", rule.get().getName(), equalTo(ruleName));
    }

    @Step("Step. Получить количество правил")
    public Integer getRulesCountInDB() {
        return DaoFactory.getInstance().getRuleDao().getAll().size();
    }


}
