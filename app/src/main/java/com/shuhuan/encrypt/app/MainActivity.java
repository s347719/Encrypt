package com.shuhuan.encrypt.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class MainActivity extends ActionBarActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //  Base64 解码
        String str = "5oiR5q+U6L6D5Zac5qyiQW5kcm9pZOW8gOWPkQ==";

        //  NO_WRAP  没有换行
        byte[] data = Base64.decode(str, Base64.NO_WRAP);
        try {
            str = new String(data,"UTF-8");

            Log.d("Base64","str="+str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ////////////////////////////////////////////////

        //  Base64编码

        try {

            byte[] bytes = "I love Android!".getBytes("UTF-8");

            String s = Base64.encodeToString(bytes, Base64.NO_WRAP);
            Log.d("经过Base64编码","======"+s);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {

    String u = "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%AE%89%E5%8D%93&step_word=&pn=5&spn=0&di=32565722640&pi=&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1803784821%2C1637394734&os=261213892%2C1480020100&adpicid=0&ln=1000&fr=&fmq=1439264111858_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&objurl=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20131223%2F20131223234001-51580525.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fkwthj_z%26e3Bf5f5_z%26e3Bv54AzdH3Fil99a8ma_z%26e3Bip4%3Ffr%3DSgjxp%26fr%3Dsm9dnc089&gsm=0";

        try {
            // 对A安卓 2字进行URLEncoding 处理，同时制定编码
            String s = URLEncoder.encode("安卓", "UTF-8");
            Log.d("URLEncoding"," 安卓+"+s);

            String decode = URLDecoder.decode(s, "UTF-8");
            Log.d("URLDecoder","====="+decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        processZipFile();
    }


    private void processZipFile(){

        // 1, 将Aassets 目录中的文件，保存到应用程序内部存储区
        AssetManager assets = getAssets();
        try {
            InputStream inputStream = assets.open("test.zip");

            FileOutputStream outputStream = openFileOutput("test.zip", Context.MODE_PRIVATE);

            readStream(inputStream, outputStream);

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2, 获取文件 File 对象，利用FIle 对象来创建 ZipFile 对象
        File filesDir = getFilesDir();
        File tatgetFile = new File(filesDir,"test.zip");
        if (tatgetFile.exists()) {
            try {
                // 创建ZipFile 对象，手动解析zip包
                ZipFile zipFile = new ZipFile(tatgetFile);

                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements())
                {

                    ZipEntry zipEntry = entries.nextElement();
                    String name = zipEntry.getName();
                    //显示每一个文件的名称
                    Log.d("Compress","zip Entry = "+name);

                    //  TODO  解压缩文件
                    if (zipEntry.isDirectory())
                    {
                        File folder = new File(filesDir,name);
                        if (!folder.exists()){

                            folder.mkdirs();

                        }
                    }else {

                        File nFile = new File(filesDir,name);
                        //  解压缩
                        // 通过Entry 来获取 压缩的数据
                        // 自动加载压缩包中的文件，直接读流就可以
                        InputStream stream = zipFile.getInputStream(zipEntry);
                        FileOutputStream fout = new FileOutputStream(nFile);

                        readStream(stream,fout);

                        stream.close();
                        fout.close();
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private void readStream(InputStream inputStream, FileOutputStream outputStream) throws IOException {
        byte[] bytes = new byte[128];
        int len ;
        while (true)
        {
            len = inputStream.read(bytes);
            if (len==-1)
            {
                break;
            }
            outputStream.write(bytes,0,len);
        }
    }

}
