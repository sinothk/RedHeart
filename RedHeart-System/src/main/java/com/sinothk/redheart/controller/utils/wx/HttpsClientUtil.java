package com.sinothk.redheart.controller.utils.wx;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.util.Map;

public class HttpsClientUtil {
//    public String doPost(String url, Map<String,String> map) throws Exception{
//        String result = null;
//        HttpClient httpClient = new SSLClient();
//        HttpPost httpPost = new HttpPost(url);
//        //设置参数
//        List<NameValuePair> list = new ArrayList<NameValuePair>();
//        Iterator iterator = map.entrySet().iterator();
//        while(iterator.hasNext()){
//            Entry<String,String> elem = (Entry<String, String>) iterator.next();
//            list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
//        }
//        if(list.size() > 0){
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
//            httpPost.setEntity(entity);
//        }
//        HttpResponse response = httpClient.execute(httpPost);
//        if(response != null){
//            HttpEntity resEntity = response.getEntity();
//            if(resEntity != null){
//                result = EntityUtils.toString(resEntity, "UTF-8");
//            }
//        }
//        return result;
//    }

    public String doGet(String url, Map<String,String> map) throws Exception{
        String result = null;
        HttpClient httpClient = new SSLClient();
        String param="";
        for(String nameKey:map.keySet()){
            param += nameKey+"="+map.get(nameKey)+"&";
        }
        param = param.substring(0,param.length()-1);
        String urlNameString = url + "?" + param;
        HttpGet httpGet = new HttpGet(urlNameString);
        HttpResponse response = httpClient.execute(httpGet);
        if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result = EntityUtils.toString(resEntity, "UTF-8");
            }
        }
        return result;
    }

//    public String doPostXml(String url, String xml) throws Exception{
//        String result = null;
//        HttpClient httpClient = new SSLClient();
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader("Content-Type","text/xml;charset=UTF-8");
//        StringEntity stringEntity = new StringEntity(xml, "UTF-8");
//        stringEntity.setContentEncoding("UTF-8");
//
//        httpPost.setEntity(stringEntity);
//        HttpResponse response = httpClient.execute(httpPost);
//        if(response != null){
//            HttpEntity resEntity = response.getEntity();
//            if(resEntity != null){
//                result = EntityUtils.toString(resEntity, "UTF-8");
//            }
//        }
//        return result;
//    }
}
