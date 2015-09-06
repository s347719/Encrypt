package com.shuhuan.encrypt.app.cryptUtils;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import com.shuhuan.encrypt.app.R;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

public class TestActivity extends ActionBarActivity {

    private String content;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        content = "i love you";
        password="asdfhkjahk";
//=================================  AES 加密   =======================================

        //1,加密



        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec(password.getBytes(),"AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(content.getBytes());

            String s = Base64.encodeToString(bytes, Base64.NO_WRAP);



        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }



        // 解密
        byte[] decode = Base64.decode(content, Base64.NO_WRAP);
        Cipher aes = null;
        try {
            aes = Cipher.getInstance("AES");
            SecretKeySpec key1 = new SecretKeySpec(password.getBytes(),"AES");

            aes.init(Cipher.DECRYPT_MODE, key1);

            byte[] bytes = aes.doFinal(decode);
            String s = new String(bytes);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        //==============================================


    }
    public void testDes(View view ){

        if (content.length()>0){
            if (password.length()==8)
            {
                try {
                    Cipher cipher = Cipher.getInstance("DES");

                    DESKeySpec keySpec = new DESKeySpec(password.getBytes());
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = factory.generateSecret(keySpec);

                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] bytes = cipher.doFinal(content.getBytes());

                    String s = Base64.encodeToString(bytes,Base64.NO_WRAP);


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


    public void testDesDecrypt(View view ){

        if (content.length()>0){
            if (password.length()==8)
            {

                byte[] bytes1 = Base64.decode(content, Base64.NO_WRAP);

                try {
                    Cipher cipher = Cipher.getInstance("DES");

                    DESKeySpec keySpec = new DESKeySpec(password.getBytes());
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = factory.generateSecret(keySpec);

                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] bytes = cipher.doFinal(bytes1);

                    String str = new String(bytes1);

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

    //================================ RSA 密钥对

    public void btnRsaGen(View view){

        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);

            KeyPair keyPair = generator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();


            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            BigInteger publicExponent = rsaPublicKey.getPublicExponent();
            BigInteger modulus = rsaPublicKey.getModulus();

            byte[] data = EncryptUtils.rsaEncrypt("I love you".getBytes(), publicKey);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}
