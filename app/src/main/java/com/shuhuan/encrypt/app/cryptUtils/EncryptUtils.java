package com.shuhuan.encrypt.app.cryptUtils;

/**
 * Created with Intellij IDEA.
 * Project:Encrypt
 * user:MIKE Shu
 * Email:578076417@qq.com
 * Created on 2015/8/11.
 * Time 16:10
 */


import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * 加密 工具类，至此DES DESede AES RSA
 *
 * 支持密钥的生成
 *
 * 支持 数字签名
 */
public class EncryptUtils {
    private EncryptUtils(){


    }

//=====================================RSA  算法 ===================================================================

    /**
     *
     * RSA  的加密算法，
     * @param data  数据
     * @param key  必须为 RSA 的 PublicKey  或者PrivateKey
     * @return   bute[]  加密后的的数据
     */
    public static byte[] rsaEncrypt(byte[] data, Key key){
        byte[] ret = null;

        if (data!=null && key !=null)
        {

            try {
                Cipher cipher = Cipher.getInstance("RSA");

                cipher.init(Cipher.ENCRYPT_MODE,key);

                ret = cipher.doFinal(data);

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


        }

        return ret;


    }

    /**
     *
     *    解密算法
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] rsaDecrpt(byte[] data, Key key){
        byte[] ret = null;

        if (data!=null && key !=null)
        {

            try {
                Cipher cipher = Cipher.getInstance("RSA");

                cipher.init(Cipher.DECRYPT_MODE,key);

                ret = cipher.doFinal(data);

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


        }

        return ret;


    }







//======================================  DES  =====================================================================
    /**
     *
     * DES 加密算法
     * @param data  数据
     * @param password  密钥
     * @return byte[]
     */

    public static byte[] desEncrypt(byte[] data,byte[] password){

        byte[] ret = null;

        if (data !=null && data.length>0)
        {

            if (password!=null && password.length==8){

                try {
                    Cipher cipher = Cipher.getInstance("DES");

                    DESKeySpec desKeySpec = new DESKeySpec(password);
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = factory.generateSecret(desKeySpec);

                    cipher.init(Cipher.ENCRYPT_MODE, key);

                    byte[] bytes = cipher.doFinal(data);
                    ret = bytes;

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


        return ret;

    }

    public static byte[] desDecrypt(byte[] data,byte[] password){

        byte[] ret = null;

        if (data !=null && data.length>0)
        {

            if (password!=null && password.length==8){

                try {
                    Cipher cipher = Cipher.getInstance("DES");

                    DESKeySpec desKeySpec = new DESKeySpec(password);
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = factory.generateSecret(desKeySpec);

                    cipher.init(Cipher.DECRYPT_MODE, key);

                    byte[] bytes = cipher.doFinal(data);
                    ret = bytes;

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


        return ret;

    }
//=================================================DES 结束==========================================================


//================================================ DESede ==========================================================
    /**
     *
     * DES 加密算法
     * @param data  数据
     * @param password  密钥
     * @return byte[]
     */

    public static byte[] desedeEncrypt(byte[] data,byte[] password){

        byte[] ret = null;

        if (data !=null && data.length>0)
        {

            if (password!=null && password.length==24){

                try {
                    Cipher cipher = Cipher.getInstance("DESede");

                    DESedeKeySpec desKeySpec = new DESedeKeySpec(password);
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
                    SecretKey key = factory.generateSecret(desKeySpec);

                    cipher.init(Cipher.ENCRYPT_MODE, key);

                    byte[] bytes = cipher.doFinal(data);
                    ret = bytes;

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


        return ret;

    }

    public static byte[] desedeDecrypt(byte[] data,byte[] password){




        byte[] ret = null;

        if (data !=null && data.length>0)
        {

            if (password!=null && password.length==24){

                try {
                    Cipher cipher = Cipher.getInstance("DESede");

                    DESedeKeySpec desKeySpec = new DESedeKeySpec(password);
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
                    SecretKey key = factory.generateSecret(desKeySpec);

                    cipher.init(Cipher.DECRYPT_MODE, key);

                    byte[] bytes = cipher.doFinal(data);
                    ret = bytes;

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


        return ret;

    }
//=====================   AES  加密方式1 处理====================================================================

    public static byte[] aesEncrypt(byte[] data,byte[] password){

        byte[] ret = null;
        if (data!=null && data.length>0)
        {


            if (password!=null && password.length==16)
            {

                try {
                    Cipher cipher = Cipher.getInstance("AES");

                    SecretKeySpec key = new SecretKeySpec(password,"AES");
                    cipher.init(Cipher.ENCRYPT_MODE,key);

                    ret = cipher.doFinal(data);

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

            }
        }

        return ret;

    }



//==============================  AES 方式1 解密 ===================================================================

    public static byte[] aesDecrypt(byte[] data,byte[] password){

        byte[] ret = null;
        if (data!=null && data.length>0)
        {


            if (password!=null && password.length==16)
            {

                try {
                    Cipher cipher = Cipher.getInstance("AES");

                    SecretKeySpec key = new SecretKeySpec(password,"AES");
                    cipher.init(Cipher.DECRYPT_MODE,key);

                    ret = cipher.doFinal(data);

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

            }
        }

        return ret;

    }

//====================================================================================================================

// ===============================   AES  加密方式2 带有 ============================================================

    //  !!!!!!!!!!!!!  Cipher 的创建 支持多套 AES 模式，一定要和服务器一致  加密解密一定要一致

    public static byte[] aesPaddingEncrypt(byte[] data,byte[] password,byte[] ivPassword){


            byte[] ret = null;

            if (data!=null && data.length>0){
                if (password !=null && password.length==16)
                {
                    if (ivPassword!=null && ivPassword.length==16){

                        try {
                            // 制定AES 的加密模式和填充
                            // 可选的内容 ： AES/CBC/PKCS5Padding
                            //              AES/ECB/PKCS5Padding
                            // 指定上述模式之后，init 方法必须添加 Iv 参数
                            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PPadding");
                            SecretKeySpec key = new SecretKeySpec(password,"AES");

                            // 向量密码
                            IvParameterSpec params = new IvParameterSpec(ivPassword);
                            // 初始化
                            cipher.init(Cipher.ENCRYPT_MODE,key, params);

                            //  处理
                            ret = cipher.doFinal(data);

                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (NoSuchPaddingException e) {
                            e.printStackTrace();
                        } catch (InvalidAlgorithmParameterException e) {
                            e.printStackTrace();
                        } catch (InvalidKeyException e) {
                            e.printStackTrace();
                        } catch (BadPaddingException e) {
                            e.printStackTrace();
                        } catch (IllegalBlockSizeException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            return ret;
    }


// ================================================解密=================================================

    public static byte[] aesPaddingDecrypt(byte[] data,byte[] password,byte[] ivPassword){


        byte[] ret = null;

        if (data!=null && data.length>0){
            if (password !=null && password.length==16)
            {
                if (ivPassword!=null && ivPassword.length==16){

                    try {
                        // 制定AES 的加密模式和填充
                        // 可选的内容 ： AES/CBC/PKCS5Padding
                        //              AES/ECB/PKCS5Padding
                        // 指定上述模式之后，init 方法必须添加 Iv 参数
                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PPadding");
                        SecretKeySpec key = new SecretKeySpec(password,"AES");

                        // 向量密码
                        IvParameterSpec params = new IvParameterSpec(ivPassword);
                        // 初始化
                        cipher.init(Cipher.DECRYPT_MODE,key, params);

                        //  处理
                        ret = cipher.doFinal(data);

                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return ret;
    }


//=================================================================================================================
}
