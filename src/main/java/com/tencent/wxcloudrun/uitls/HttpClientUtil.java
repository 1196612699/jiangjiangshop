package com.tencent.wxcloudrun.uitls;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpClientUtil {
    public static String doGet(String url, Map<String, String> map) {
        String json = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        int i=0;
        StringBuilder urlBuilder = new StringBuilder(url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 处理每个键值对的逻辑
            if(i==0){
                urlBuilder.append("?");
            }else{
                urlBuilder.append("&");
            }
            urlBuilder.append(key).append("=").append(value);
            i++;
        }
        url = urlBuilder.toString();
        // 创建请求对象
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            //发送请求
            response = httpClient.execute(httpGet);

            //获取服务端返回的状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("服务端返回的状态码为：" + statusCode);

            //解析返回结果
            HttpEntity entity = response.getEntity();
            json = EntityUtils.toString(entity);
        } catch (IOException ignored) {

        } finally {
            try {
                // 关闭资源
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException ignored) {

            }

        }


        return json;
    }
}
