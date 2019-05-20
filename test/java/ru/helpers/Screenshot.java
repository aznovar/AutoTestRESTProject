package ru.helpers;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.jd.sup.enums.project.Way.ALLURE_SCREENSHOTS;

public class Screenshot {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] take(WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = Const.getPath(ALLURE_SCREENSHOTS, scrFile.getName()).toString();
        try {
            FileUtils.copyFile(scrFile, new File(path));
            return toByteArray(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }
}
