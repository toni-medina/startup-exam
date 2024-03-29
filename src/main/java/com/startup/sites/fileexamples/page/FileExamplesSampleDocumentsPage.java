package com.startup.sites.fileexamples.page;

import com.github.webdriverextensions.WebPage;
import com.startup.sites.fileexamples.FileExamplesSite;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.webdriverextensions.Bot.assertCurrentUrlContains;
import static com.github.webdriverextensions.Bot.driver;
import static com.startup.utilities.BotUtils.click;
import static com.startup.utilities.BotUtils.elementExists;

public class FileExamplesSampleDocumentsPage extends WebPage {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(FileExamplesSampleDocumentsPage.class);

    // Url
    public String url = FileExamplesSite.url;

    // Model
    @Getter
    @FindBy(css = "#table-files tbody tr[role='row']")
    protected List<WebElement> rows;

    @Override
    public void open(Object... arguments) {
        open(url);
        assertIsOpen();
    }

    @Override
    public void assertIsOpen(Object... arguments) throws Error {
        assertCurrentUrlContains("file-examples.com");
        assertCurrentUrlContains("sample-documents-download");
    }


    public void clickOnButton(String fileExtension) {
        String xpathExpression = String.format("//td[@class='file-ext' and contains(text(), '%s')]/parent::tr/td[contains(@class, 'file-link')]/a", fileExtension);
        if (elementExists(By.xpath(xpathExpression))) {
            WebElement element = driver().findElement(By.xpath(xpathExpression));
            click(element);
            log.info(String.format("Button '%s' clicked", fileExtension));
        } else {
            log.error(String.format("Button '%s' doesn't exists", fileExtension));
        }

    }
}
