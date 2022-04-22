package com.codedream.peripheral.MLFunction;

import com.alibaba.fastjson.JSONObject;
import com.codedream.controller.LoginController;
import com.codedream.intf.entity.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    private static final String matrix = "s";
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Override
    public int[] recGet(int id) {


        // URL of python project

        //http://192.168.1.8:8585/test?userId=3380401

        String targetUrl = "http://localhost:8585";
        String url = targetUrl+"/test"+"?"+"userId="+id;
        try {
            String respondString =get(url);
            logger.info(String.valueOf(respondString));
            String a=respondString.substring(1,respondString.length()-2);
            logger.info(a);
            String[] result=a.split(",");
            logger.info(Arrays.toString(result));
            int[] r2=new int[result.length];
            for (int i=0;i<result.length;i++){
                r2[i]=Integer.parseInt(result[result.length-i-1]);
            }
            logger.info(String.valueOf(respondString));

            return r2;
//            return resultJSON.getJSONArray("course_list");
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
