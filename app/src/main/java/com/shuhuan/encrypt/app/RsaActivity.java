package com.shuhuan.encrypt.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.shuhuan.encrypt.app.cryptUtils.EncryptUtils;

import java.io.File;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


public class RsaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);


    }

    /**
     * 生成 RSA 密钥
     *
     * @param view
     */
    public void btnRsaGen(View view) {

        //  生成 RSA 密钥生成器
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            // 设置生成 Key 的尺寸 、强度 是 1024 bit
            generator.initialize(1024);
            // 生成密钥 包含了 公钥 私钥
            KeyPair keyPair = generator.generateKeyPair();
            // 获取生成的私钥
            PrivateKey privateKey = keyPair.getPrivate();
            // 获取生成的公钥
            PublicKey publicKey = keyPair.getPublic();

///////////////////////////////RSA 密钥的转换，来显示详细的内容//////////////////////////////////////////
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;

            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;

            // 公钥 e1
            BigInteger publicExponent = rsaPublicKey.getPublicExponent();
            Log.d("RSA","publicExponent ="+publicExponent.toString(16));

            // 公钥 n
            BigInteger modulus = rsaPublicKey.getModulus();
            Log.d("RSA","modulus ="+modulus.toString(16));

            // 私钥  e2
            BigInteger privateExponent = rsaPrivateKey.getPrivateExponent();
            Log.d("RSA","privateExponent ="+privateExponent.toString(16));

            //  私钥 n   公钥 和 私钥的 n 相等
            BigInteger rsaPrivateKeyModulus = rsaPrivateKey.getModulus();
            Log.d("RSA","rsaPrivateKeyModulus ="+rsaPrivateKeyModulus.toString(16));

///////////////////////////////////////////////////////////////////////////////////////////////////////////

            //  加 解密 的测试
            byte[] data = EncryptUtils.rsaEncrypt("I love android".getBytes(), privateKey);
            if (data != null) {
                String s = Base64.encodeToString(data, Base64.NO_WRAP);
                Log.d("RSA"," encrypt = "+s);

                //  使用公钥解密

                byte[] bytes = EncryptUtils.rsaDecrpt(data, publicKey);
                s = new String(bytes);
                Log.d("RSA","decrypt= " +s);

            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
