package com.shuhuan.encrypt.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class AESActivity extends ActionBarActivity {


    private EditText txtcontext;
    private EditText txtpass;
    private EditText txtresult;
    private EditText txtorigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes);

        txtcontext = (EditText) findViewById(R.id.aes_content);
        txtpass = (EditText) findViewById(R.id.aes_pass);
        txtresult = (EditText) findViewById(R.id.aes_result);
        txtorigin = (EditText) findViewById(R.id.aes_oriengin);


    }


    /**
     *   AES   第一种加密
     * @param view
     */
    public void btnAES1Encrypt(View view) {

        String content = txtcontext.getText().toString();
        String pass = txtpass.getText().toString();

        if (pass.length()==16){

            if (content.length()>0){
                try {
                    Cipher cipher = Cipher.getInstance("AES");
                    SecretKeySpec key = new SecretKeySpec(pass.getBytes(),"AES");
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] bytes = cipher.doFinal(content.getBytes());
                    String s = Base64.encodeToString(bytes, Base64.NO_WRAP);
                    txtresult.setText(s);

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     *
     *
     *  AES  第一种解密
     * @param view
     */

    public void btnAES1DeEncrypt(View view) {



        String resultContent = txtresult.getText().toString();
        String pass = txtpass.getText().toString();

        if (resultContent.length()>0){

            if (pass.length()==16){
                byte[] data = Base64.decode(resultContent, Base64.NO_WRAP);
                try {
                    Cipher cipher = Cipher.getInstance("AES");

                    SecretKeySpec key = new SecretKeySpec(pass.getBytes(),"AES");
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] bytes = cipher.doFinal(data);
                    String s = new String(bytes);
                    txtorigin.setText(s);

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
