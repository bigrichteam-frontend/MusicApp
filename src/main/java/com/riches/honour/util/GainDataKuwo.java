package com.riches.honour.util;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * 从酷我中爬取信息
 * @author whg
 * @date 2019/7/10 15:35
 **/
public class GainDataKuwo {

    /**
     * 通过rid获取歌曲的路径
     * @param rid
     * @return
     */
    public static String getUrlByRid(int rid) {
        Document doc2= null;
        String musicPosion = "";
        try {
            doc2 = Jsoup.connect("http://www.kuwo.cn/url?format=mp3&rid="+ rid +"&response=url&type=convert_url3&br=128kmp3&from=web&t=1561517488040").ignoreContentType(true).get();
            //System.out.println("doc2:" + doc2.select("body").html());
            JSONObject j2 = new JSONObject(doc2.select("body").html());
            musicPosion = j2.get("url").toString();
            //System.out.println("musicPosion:" + musicPosion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicPosion;
    }
}
