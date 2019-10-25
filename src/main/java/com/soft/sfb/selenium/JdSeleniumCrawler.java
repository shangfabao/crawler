package com.soft.sfb.selenium;

import com.soft.sfb.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shangfabao
 * @date 2019/7/31 13:14
 **/
public class JdSeleniumCrawler {

    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        try {
            System.getProperties().setProperty("webdriver.chrome.driver", new File("").getCanonicalPath() + File.separator + "chromedriver.exe");
            logger.error("打开浏览器");
            WebDriver webDriver = new ChromeDriver();
            logger.error("页面最大化");
            webDriver.manage().window().maximize();
            logger.error("全局隐式等待，等待");
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            logger.error("打开矿运通老平台");
            webDriver.get("https://search.jd.com/Search?keyword=SSD&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=SSD&psort=3&click=0");
            logger.error("显示等待控制对象");
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
            List<WebElement> elements = webDriver.findElements(By.className("gl-item"));

            logger.error(elements.size());

            for (int i = 0; i < elements.size(); i++) {
                WebElement element = elements.get(i);
                logger.error(element.findElement(By.className("p-commit")).findElement(By.tagName("a")).getText());
            }
            webDriver.close();
        } catch (Exception e) {
            logger.error(ExceptionUtil.getMsg(e));
        }

    }
}
