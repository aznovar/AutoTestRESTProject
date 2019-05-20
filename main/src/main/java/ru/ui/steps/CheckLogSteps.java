package main.java.ru.ui.steps;

import io.qameta.allure.Step;
import org.testng.Assert;
import ru.jd.sup.excpt.CoreProblem;
import ru.jd.sup.single.Const;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static ru.jd.sup.single.Const.JD_URL;

public class CheckLogSteps {

    private String logFilePath;

    public CheckLogSteps() {
        String fileName = "some.log";
        logFilePath = "/some/field/logs/" + fileName;
    }

    @Step("Проверить срабатывание правила с объектом: {objectId} и правилом: {ruleId} в логе")
    public void checkPolicyIsRun(Integer ruleId, Integer objectId) throws CoreProblem {
        String command;
        Process proc;
        Integer commandStatus;
        String line;
        String searchString = "Sending FIRED_OBJECT_ROW FiredObjectRow." +
                "super=ActionMessageBase.messageType=FIRED_OBJECT_ROW., " +
                "ObjectRowId="+ matrixId + ", firedRules=.FiredRule.ruleId=" + ruleId;
        try {
            command = "ssh -p 3322 -i /some_server_address@" + new URL(JD_URL).getHost()
                    + " -T grep '" + searchString +"' " + logFilePath;

            proc = Runtime.getRuntime().exec(command);
            BufferedReader is = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                while ((line = is.readLine())!=null){
                    System.out.println(line);
                }
            commandStatus = proc.waitFor();
            if (commandStatus != 0)
                Assert.fail("Операция '" + command + "' поиска строки в файле лога завершилась с ошибкой. Код ошибки - "
                        + commandStatus);

        } catch (MalformedURLException e) {
            throw Const.rethrow("Не удалось получить константу JD_URL", e);
        } catch (IOException e) {
            throw Const.rethrow("Не удалось выполнить exec команду копирования", e);
        } catch (InterruptedException e) {
            throw Const.rethrow("Не удалось получить exitValue команды копирования", e);
        }
    }

    @Step("Проверить срабатывание правила с объектом: {objectId} и правилом: {ruleId} в логе")
    public void checkPolicyIsRun2(Integer ruleId, Integer objectId) throws CoreProblem {
        String command;
        Process proc;
        Integer commandStatus;
        String line;
        String errorOutput = "";
        String searchString = "Sending FIRED_OBJECT_ROW FiredMatrixRow.";
        try {
            command = "ssh -p 3322 -i /some_server_address@" + new URL(JD_URL).getHost()
                    + " -T grep '" + searchString +"' " + logFilePath;

            proc = Runtime.getRuntime().exec(command);
            BufferedReader is = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = is.readLine())!=null){
                System.out.println(line);
            }
            commandStatus = proc.waitFor();
            if (commandStatus != 0)
                Assert.fail("Операция '" + command + "' поиска строки в файле лога завершилась с ошибкой. Код ошибки - "
                        + commandStatus + "\nErrOut: " + errorOutput);
        } catch (MalformedURLException e) {
            throw Const.rethrow("Не удалось получить константу JD_URL", e);
        } catch (IOException e) {
            throw Const.rethrow("Не удалось выполнить exec команду копирования", e);
        } catch (InterruptedException e) {
            throw Const.rethrow("Не удалось получить exitValue команды копирования", e);
        }
    }
}
