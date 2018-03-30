package com.cg.baseproject.encryption;


import android.os.Build;
import android.support.annotation.RequiresApi;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * http://desert3.iteye.com/blog/743713
 * https://blog.csdn.net/elim168/article/details/73456866
 * 对称加密算法:对称加密算法是应用较早的加密算法，技术成熟。在对称加密算法中，数据发信方将明文（原始数据）和加密密钥一起经过特殊加密算法处理后，
 * 使其变成复杂的加密密文发送出去。收信方收到密文后，若想解读原文，则需要使用加密用过的密钥及相同算法的逆算法对密文进行解密，才能使其恢复成可
 * 读明文。在对称加密算法中，使用的密钥只有一个，发收信双方都使用这个密钥对数据进行加密和解密，这就要求解密方事先必须知道加密密钥。对称加密算
 * 法的特点是算法公开、计算量小、加密速度快、加密效率高。不足之处是，交易双方都使用同样钥匙，安全性得不到保证。此外，每对用户每次使用对称加密
 * 算法时，都需要使用其他人不知道的惟一钥匙，这会使得发收信双方所拥有的钥匙数量成几何级数增长，密钥管理成为用户的负担。对称加密算法在分布式网
 * 络系统上使用较为困难，主要是因为密钥管理困难，使用成本较高。在计算机专网系统中广泛使用的对称加密算法有DES(Data Encryption Standard)和
 * IDEA等。美国国家标准局倡导的AES即将作为新标准取代DES。 
 * 进行 128 位 AES 加密解密的工具类 指定 provider 为 BC
 * @Description:主要功能:AES对称加密（Advanced Encryption Standard，高级数据加密标准，AES算法可以有效抵制针对DES的攻击算法，对称加密算法）
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil.encryption
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 15:54
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class AESUtils {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub  
//        System.out.println("加密后："+encode("哇嘎嘎嘎嘎嘎"));
//        System.out.println("解密后："+decode(encode("哇嘎嘎嘎嘎嘎")));
    }
    // 密钥
    private final static String secretKey = "com.huisuoping.v4.uV252QkRe05ehplS";
    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    private AESUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private SecretKey generateKey() throws Exception {
        //获取一个密钥生成器实例  
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();
        random.setSeed("123456".getBytes());//设置加密用的种子，密钥  
        keyGenerator.init(random);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    /**
     * 上述生成密钥的过程中指定了固定的种子，每次生成出来的密钥都是一样的。还有一种形式，我们可以通过不指定SecureRandom对象的种子，
     * 即不调用其setSeed方法，这样每次生成出来的密钥都可能是不一样的。
     * @return
     * @throws Exception
     */
    private SecretKey generateRadomKey() throws Exception {
        //获取一个密钥生成器实例  
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();
        keyGenerator.init(random);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    /**
     *
     通过KeyGenerator的init(keySize)方法进行初始化，而不是通过传递SecureRandom对象进行初始化也可以达到上面的效果，每次生成的密钥都
     可能是不一样的。但是对应的keySize的指定一定要正确，AES算法的keySize是128。

     但是这种每次生成出来的密钥都是不同的情况下，我们需要把加密用的密钥存储起来，以供解密的时候使用，不然就没法进行解密了。
     * @return
     * @throws Exception
     */
    private SecretKey geneKey() throws Exception {
        //获取一个密钥生成器实例  
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveKey(String path){
        //把上面的密钥存起来  
        Path keyPath = Paths.get("D:/aes.key");
//        Files.write(keyPath, geneKey());
    }
    /*
     * AES 加密
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }

    /*
     * AES 解密
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }

}