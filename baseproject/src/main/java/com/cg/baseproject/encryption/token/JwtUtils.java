package com.cg.baseproject.encryption.token;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * https://blog.csdn.net/qq_37636695/article/details/79265711
 *
    JWT在一定程度上，保护了API的安全。但是其本身还是存在一定的缺陷的。比如说，一定JWT的加密密钥一旦被泄露，那么黑客就可以生成JWT字符串了，
 因此保护好JWT加密密钥非常重要。
    在下面的例子当中，介绍了获取用户信息API需要加入userJwt的例子。userJwt其实就是在JWT字符串中加入了userId字段，继而保证一个userJwt只
 能访问一个用户的信息。对于其他的API，比如说PUT和POST操作，需要新增和修改数据的API。可以将请求参数一并放入jwt中，以此来确保数据的安全
 性。否则黑客还可以在JWT字符串还没有过期的时间段内，修改请求中的参数，达到攻击的目的。
    另外还要防止重复式攻击，黑客还可以在JWT字符串还没有过期的时间段内，重复提交请求，达到攻击的目的。比如说新增订单的API，如果被黑客采用重
 复式攻击的方式，就会生成多个订单。
 * @author sam
 * @version 1.0
 * @date 2018/4/10
 */
public class JwtUtils {
    private String jianshu;
    /**
     * jwt *
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTEwOSwiaWF0IjoxNTE3ODM1MDQ5LCJqdGkiOiJqd3QifQ";
    public static final int JWT_TTL = 60 * 60 * 1000;
    //millisecond public static final int JWT_REFRESH_INTERVAL = 55*60*1000; 
    // millisecond public static final int JWT_REFRESH_TTL = 12*60*60*1000; 
    // millisecond


    public static void main(String[] args) throws Exception {
        JwtUtils util=   new JwtUtils();
        String ab=util.createJWT("jwt", "{id:100,name:xiaohong}", 60000);
        System.out.println(ab);
        //eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTE0NiwiaWF0IjoxNTE3ODM1MDg2LCJqdGkiOiJqd3QifQ.ncVrqdXeiCfrB9v6BulDRWUDDdROB7f-_Hg5N0po980
        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTEwOSwiaWF0IjoxNTE3ODM1MDQ5LCJqdGkiOiJqd3QifQ.G_ovXAVTlB4WcyD693VxRRjOxa4W5Z-fklOp_iHj3Fg";
        Claims c=util.parseJWT(ab);//注意：如果jwt已经过期了，这里会抛出jwt过期异常。
        System.out.println(c.getId());//jwt
        System.out.println(c.getIssuedAt());//Mon Feb 05 20:50:49 CST 2018
        System.out.println(c.getSubject());//{id:100,name:xiaohong}
        System.out.println(c.getIssuer());//null
        System.out.println(c.get("uid", String.class));//DSSFAWDWADAS...
    }
    
    /**
     * 由字符串生成加密key
     * @return
     */
    public SecretKey generalKey(){
        String stringKey = JWT_SECRET;//本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
        byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码[B@152f6e2
        System.out.println(encodedKey);//[B@152f6e2
        System.out.println(Base64.encodeBase64URLSafeString(encodedKey));//7786df7fc3a34e26a61c034d5ec8245d
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return key;
    }

    /**
     * 创建jwt
     * @param id
     * @param subject
     * @param ttlMillis 过期的时间长度
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String subject, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        Map<String,Object> claims = new HashMap<String,Object>();//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        claims.put("uid", "DSSFAWDWADAS...");
        claims.put("user_name", "admin");
        claims.put("nick_name","DASDA121");
        SecretKey key = generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
//                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(id)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .setSubject(subject)        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();           //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
        //打印了一哈哈确实是下面的这个样子
        //eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiIiLCJ1c2VyX25hbWUiOiJhZG1pbiIsIm5pY2tfbmFtZSI6IkRBU0RBMTIxIiwiZXhwIjoxNTE3ODI4MDE4LCJpYXQiOjE1MTc4Mjc5NTgsImp0aSI6Imp3dCJ9.xjIvBbdPbEMBMurmwW6IzBkS3MPwicbqQa2Y5hjHSyo
    }
    
    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
        return claims;
    }

    public boolean isJwtValid(String jwt) {
        try {
            //解析JWT字符串中的数据，并进行最基础的验证
            Claims claims = Jwts.parser()
                    .setSigningKey(generalKey())//SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
                    .parseClaimsJws(jwt)//jwt是JWT字符串
                    .getBody();
            String vaule = claims.get("key", String.class);//获取自定义字段key
            //判断自定义字段是否正确
            if ("vaule".equals(vaule)) {
                return true;
            } else {
                return false;
            }
        }
        //在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
        //在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
        catch (SignatureException |ExpiredJwtException e) {
            return false;
        }
    }
    
    /**
     * 生成subject信息 * @param user * @return
     */
    public static String generalSubject(String user) {
//        JSONObject jo = new JSONObject();
//        jo.put("userId", user.getId());
//        jo.put("mobile", user.getMobile());
        return "";
    }

    /**
     * Client端
     * Client端需要做的就是，根据API的需求将JWT字符串放入http请求中。我的做法是对于所有的API，在Client端生成JWT字段，
     * 然后将其添加到http请求的header中，确保所有的API都获得保护。对于一些比较敏感的信息，再用加一层JWT验证。比如说用
     * 户信息，在调用登录API后，API Server将会返回一个特定的JWT字符串，该JWT字段总将会包含该用户的userId。如果要获取
     * 用户信息，除了要将Client端生成的JWT字段放入请求，还需要将该JWT字符串放入请求。接下来展示一下利用OKHttp在http请
     * 求的header中加入JWT字段的代码：
     */
    //该方法将会在所有请求的header中加入jwt
    /*
    public Response call(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = request.newBuilder()
                .addHeader("commonJwt", jwtService.makeJwt());//加入Client本地生成的JWT字符串
        //加入登录成功后获取到的JWT字符串
        String userJwt = jwtService.getUserJwt();
        if (!StringUtils.isSpace(userJwt))
            requestBuilder.addHeader("userJwt", userJwt);
        request = requestBuilder.build();
        return client.newCall(request).execute();
    }
    */

}
