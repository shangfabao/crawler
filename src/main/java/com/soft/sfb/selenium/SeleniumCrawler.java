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
import java.util.concurrent.TimeUnit;

/**
 * @author shangfabao
 * @date 2019/7/31 13:14
 **/
public class SeleniumCrawler {

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
            webDriver.get("http://218.29.81.226:8980/Default.aspx?tdsourcetag=s_pctim_aiomsg");
            logger.error("显示等待控制对象");
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
            logger.error("输入用户名密码");
            WebElement nameElement = webDriver.findElement(By.id("txtUserName"));
            nameElement.clear();
            nameElement.sendKeys("superadmin");

            WebElement pwdElement = webDriver.findElement(By.id("txtPwd"));
            pwdElement.clear();
            pwdElement.sendKeys("123456");

            webDriver.findElement(By.id("btnLogin")).click();
            logger.error(webDriver.getPageSource());
            //页面中有iframe,必须先切换到iframe
            logger.error(webDriver.switchTo().frame("header").findElement(By.tagName("p")).getText());
            webDriver.close();
        } catch (Exception e) {
            logger.error(ExceptionUtil.getMsg(e));
        }

    }
}
