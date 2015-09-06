package com.shuhuan.encrypt.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.w3c.dom.Text;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class DesActivity extends ActionBarActivity {

    private EditText txtContent;

    private EditText txtPassWord;

    private EditText txtResult;


    private EditText txtOrigenal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);


        txtContent = (EditText) findViewById(R.id.des_content);
        txtPassWord = (EditText) findViewById(R.id.des_pass);
        txtResult = (EditText) findViewById(R.id.des_result);
        txtOrigenal = (EditText) findViewById(R.id.des_origenal);

    }

    public void btnDesEncrypt(View view) {

        String content = txtContent.getText().toString();

        String strPass = txtPassWord.getText().toString();

        if (strPass.length()==8)
        {
            if (content.length()>0)
            {
                //TODO  加密
                try {
                    Cipher cipher = Cipher.getInstance("DES");
                    // 2。指定 加密 或者解密，并且指定密码
                    // DES 的加密或者解密的密钥需要使用一个特定的类，来描述
                    DESKeySpec keySpec = new DESKeySpec(strPass.getBytes());

                    //2,1 DESKeySpec 需要通过 SecretFactory 来生成实际的 keySpec
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey secretKey = factory.generateSecret(keySpec);

                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

                    byte[] data = cipher.doFinal(content.getBytes());
                    // 因为经过加密的是不能用new Sring 只能用Base64进行编码
                    String s = Base64.encodeToString(data, Base64.NO_WRAP);
                    txtResult.setText(s);


                } catch (NoSuchAlgorithmException e) {
                    // 为找到算法异常
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    // 未找到指定的填充
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }

            }

        }else {

            Toast.makeText(this,"密码为8个字节",Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDesDecrypt(View view) {

        String s = txtResult.getText().toString();
        if (s.length()>0)
        {
            String passWord = txtPassWord.getText().toString();
            if (passWord.length()==8){

                byte[] data = Base64.decode(s, Base64.NO_WRAP);

                try {
                    // 创建DES 解密引擎
                    Cipher cipher = Cipher.getInstance("DES");

                    DESKeySpec desKeySpec = new DESKeySpec(passWord.getBytes());
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = factory.generateSecret(desKeySpec);


                    //2, 设置解密
                    cipher.init(Cipher.DECRYPT_MODE,key);
                    // 3, 将结果的Base64 还原成字节数组

                    byte[] originData = cipher.doFinal(data);

                    String str = new String(originData);

                    txtOrigenal.setText(str);

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
