package main.java.ru.ui.steps.preconditions;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import io.qameta.allure.Step;
import main.java.ru.kafka.KafkaProducer;
import org.testng.Assert;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PreConditionSteps {

    @Step("Копирование файла {testFileName} на сервер")
    private void copyFile(String pathFrom, String testFileName) throws CoreProblem {
        String command;
        Integer commandStatus;

        try {
            command = "scp -i /some_address/ansible.key -P 3322 " + pathFrom + " root@" + new URL(URL).getHost()
                    + ":" + testFileName;

            commandStatus = Runtime.getRuntime().exec(command).waitFor();
            if (commandStatus != 0)
                Assert.fail("Операция '" + command + "' копирования файла завершилась с ошибкой. Код ошибки - "
                        + commandStatus);

        } catch (MalformedURLException e) {
            throw Const.rethrow("Не удалось получить константу JD_URL", e);
        } catch (IOException e) {
            throw Const.rethrow("Не удалось выполнить exec команду копирования", e);
        } catch (InterruptedException e) {
            throw Const.rethrow("Не удалось получить exitValue команды копирования", e);
        }
    }

    public String copyFile(String name) throws CoreProblem {
        String testDirPath = TestData.CEP_COOR_SCRIPTS_DIR;
        String testFileName;
        String fileNameToCopy = TestData.GROOVY_FILE;

        String pathFrom = Const.getPathToFileInResourcesDirectory(fileNameToCopy);
        testFileName = testDirPath + "test_" + name + ".groovy";
        copyFile(pathFrom, testFileName);
        return testFileName;
    }

    @Step("Изменение типа объекта с '{objectTypeOld}' на '{objectTypeNew}' в object.properties")
    public void changeString(String objectTypeOld, String objectTypeNew) throws CoreProblem {
        String pathToFile = "some_address/object.properties";
        String command;
        Integer commandStatus;

        try {
            command = "ssh -p 3322 -i /some_address/ansible.key root@" + new URL(JD_URL).getHost()
                    + " sed -i -e 's/" + objectTypeOld + "/"
                    + objectTypeNew + "/g' " + pathToFile;

            commandStatus = Runtime.getRuntime().exec(command).waitFor();
            if (commandStatus != 0)
                Assert.fail("Операция '" + command + "' замены текста в object.properties завершилась с ошибкой. Код ошибки - "
                        + commandStatus);
        } catch (MalformedURLException e) {
            throw Const.rethrow("Не удалось получить константу URL. ", e);
        } catch (IOException e) {
            throw Const.rethrow("Не удалось изменить строку. ", e);
        } catch (InterruptedException e) {
            throw Const.rethrow("Не удалось получить exitValue команды изменения строки. ", e);
        }
    }

    @Step("Отправка сообщения '{message}' с topic'ом '{topic}' в kafka")
    public void sendMessage(String topic, String message) {
        KafkaProducer producer = new KafkaProducer(topic);
        producer.sendMessage(message);
    }

    @Step("Перезагрузка модуля object")
    public void reloadEnrichment() throws CoreProblem {
        String command;
        Process proc;
        Integer commandStatus;
        String path = "/object";
        try {
            command = "curl --user user:q1 http://some_address:9595" + path;

            proc = Runtime.getRuntime().exec(command);
            commandStatus = proc.waitFor();
            if (commandStatus != 0)
                Assert.fail("Операция '" + command + "'перезагрузки модуля object завершилась с ошибкой. Код ошибки - "
                        + commandStatus + "\nStdIn: " + "\nErrOut: ");
        } catch (IOException e) {
            throw Const.rethrow("Не удалось выполнить exec команду перезагрузки", e);
        } catch (InterruptedException e) {
            throw Const.rethrow("Не удалось получить exitValue команды перезагрузки", e);
        }

    }
}
