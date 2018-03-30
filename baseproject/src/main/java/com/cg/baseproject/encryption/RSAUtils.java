package com.cg.baseproject.encryption;

import com.cg.baseproject.utils.RadixCoversion;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/**
 * @author
 * @version 1.0
 * @date 2018/3/12
 * 加密算法可以分为对称加密、不对称加密和不可逆加密
 * http://desert3.iteye.com/blog/743713
 *
    不对称加密算法:不对称加密算法使用两把完全不同但又是完全匹配的一对钥匙—公钥和私钥。在使用不对称加密算法加密文件时，只有使用匹配的一对公钥和私钥，
    才能完成对明文的加密和解密过程。加密明文时采用公钥加密，解密密文时使用私钥才能完成，而且发信方（加密者）知道收信方的公钥，只有收信方（解密者）
    才是唯一知道自己私钥的人。不对称加密算法的基本原理是，如果发信方想发送只有收信方才能解读的加密信息，发信方必须首先知道收信方的公钥，然后利用
    收信方的公钥来加密原文；收信方收到加密密文后，使用自己的私钥才能解密密文。显然，采用不对称加密算法，收发信双方在通信之前，收信方必须将自己早
    已随机生成的公钥送给发信方，而自己保留私钥。由于不对称算法拥有两个密钥，因而特别适用于分布式系统中的数据加密。广泛应用的不对称加密算法有RSA
    算法和美国国家标准局提出的DSA(Digital Signature Algorithm)。以不对称加密算法为基础的加密技术应用非常广泛。 
 */

public class RSAUtils {
    public static String data="hello world";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub  

        KeyPair keyPair=genKeyPair(1024);

        //获取公钥，并以base64格式打印出来  
        PublicKey publicKey=keyPair.getPublic();
        System.out.println("公钥："+new String(RadixCoversion.binaryToHexString((publicKey.getEncoded()))));

        //获取私钥，并以base64格式打印出来  
        PrivateKey privateKey=keyPair.getPrivate();
        System.out.println("私钥："+new String(RadixCoversion.binaryToHexString((privateKey.getEncoded()))));

        //公钥加密  
        byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);
        System.out.println("加密后："+new String(RadixCoversion.binaryToHexString((encryptedBytes))));

        //私钥解密  
        byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);
        System.out.println("解密后："+new String(decryptedBytes));
    }
    
    //生成密钥对  
    public static KeyPair genKeyPair(int keyLength) throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    //公钥加密  
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    //私钥解密  
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }
}
