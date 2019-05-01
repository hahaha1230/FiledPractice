package com.example.fieldpractice.ui.activity;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fieldpractice.utils.StreamUtil;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by JG on 2019/5/1.
 */

public class MyAsyncTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            //URL url = new URL(objects[0].toString());
            URL url = new URL("http://192.168.1.12:3001/Ischecklogin");
            HttpURLConnection openConnection = (HttpURLConnection) url
                    .openConnection();
            openConnection.setRequestMethod("POST");
            openConnection.setDoInput(true);//发送POST请求必须设置允许输出
            openConnection.setDoOutput(true);//发送POST请求必须设置允许输入
            openConnection.setConnectTimeout(5000);
            openConnection.setReadTimeout(5000);

            //openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            openConnection.setRequestProperty("Charset", "utf-8");

          //  String data = "username=" + URLEncoder.encode("name", "UTF-8")+ "&password=" + URLEncoder.encode("123456", "UTF-8");//传递的数据
            String data="{\"username\": \"\",\"password\": \"\"}";
            openConnection.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
            int responseCode = openConnection.getResponseCode();
            //JSONObject.quote()
            if (responseCode == 200) {
                InputStream inputStream = openConnection.getInputStream();
                StreamUtil streamUtils = new StreamUtil();
                String parseStream = streamUtils.parseSteam(inputStream);
                System.out.println("-----------parseStream------------"
                        + parseStream);
                return parseStream;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d("hhh","IOException message:"+e.getMessage());
            e.printStackTrace();
        }
        return null;

    }
   /* public static synchronized JSONObject getJsonObject(String JsonString)
    {
        JSONObject jsonObject = null;
        try {
            JsonString = getJsonStrFromNetData(JsonString);
            JSONArray  entries = new JSONArray(JsonString);
            if(entries.length() > 0 )
            {
                jsonObject = entries.getJSONObject(0);
            }
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }*/

}
