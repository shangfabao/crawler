package com.soft.sfb.jsoup;

import com.soft.sfb.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author shangfabao
 * @date 2019/7/31 11:40
 **/
public class JsoupCrawler {
    public static Logger logger = LogManager.getLogger();

    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0";

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://search.jd.com/Search?keyword=SSD&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=SSD&psort=3&click=0").userAgent(userAgent).get();
            Elements elements = doc.select(".gl-item");
            logger.error(elements.size());
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                logger.error("商品标题:{}", element.select("a").attr("title"));
                logger.error("商品地址:{}", element.select("a").attr("href"));
                logger.error("缩略图地址:{}", element.select("a").get(0).select("img").get(0).attr("source-data-lazy-img"));
                logger.error("价格:{}", element.select(".p-price").get(0).select("i").get(0).text());
                logger.error("店铺名称:{}", element.select(".p-shop").get(0).select("a").get(0).text());
                logger.error("评价数量:{}条评价", element.select(".p-commit").get(0).select("a").get(0).text());
                logger.error("第{}个商品结束", i + 1);
            }
            logger.error("网页标题:{}", doc.title());
        } catch (IOException e) {
            logger.error(ExceptionUtil.getMsg(e));
        }
    }
}
