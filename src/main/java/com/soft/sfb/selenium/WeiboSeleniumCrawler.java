package com.soft.sfb.selenium;

import cn.hutool.core.util.StrUtil;
import com.soft.sfb.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author shangfabao
 * @date 2019/7/31 13:14
 **/
public class WeiboSeleniumCrawler {

    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        try {
            System.getProperties().setProperty("webdriver.chrome.driver", new File("").getCanonicalPath() + File.separator + "chromedriver.exe");
            logger.error("打开浏览器");
            WebDriver webDriver = new ChromeDriver();
            logger.error("页面最大化");
            webDriver.manage().window().maximize();
            logger.error("全局隐式等待，等待");
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            logger.error("打开矿运通老平台");
            webDriver.get("https://weibo.com/zhengzhouditie?profile_ftype=1&is_ori=1#_0");

            //下拉到页面底部

            int count = 1;
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            while (count > 0) {
                String js = StrUtil.format("document.documentElement.scrollTop={}", count * 200 + 800);
//                String js = StrUtil.format("var q=document.documentElement.scrollTop={}", count * 200 + 800);
                ((JavascriptExecutor) webDriver).executeScript(js);
                int size = webDriver.findElements(By.className("WB_cardwrap")).size();
                logger.error(size);
                //翻到翻页结束
                if (webDriver.findElements(By.className("W_pages")).size() > 0) {
                    count = -1;
                } else {
                    count++;
                }
            }

            logger.error("翻页结束");
            webDriver.close();
        } catch (Exception e) {
            logger.error(ExceptionUtil.getMsg(e));
        }

    }
}
