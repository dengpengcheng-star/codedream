package com.codedream.MLFunction;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    private static final String matrix = "s";

    @Override
    public List recGet(MLRequest mlRequest) {

        String userId = mlRequest.getUserId();
        // URL of python project

        //http://192.168.1.8:8585/test?userId=3380401

        String targetUrl = "http://localhost:8585";
        String url = targetUrl+"/test"+"?"+"userId="+userId;
        try {
            String respondString = get(url);
            JSONObject resultJSON = JSONObject.parseObject(respondString);
            return resultJSON.getJSONArray("course_list");
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



    /**
     * get请求方法
     * @param url  请求路径
     * @return 返回请求的路径返回的数据
     * @throws Exception
     */
    public static String get(String url) throws Exception{

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("DataEncoding", "UTF-8");
        HttpResponse execute = httpClient.execute(httpGet);
        String s = EntityUtils.toString(execute.getEntity());

        return s;
    }


}
