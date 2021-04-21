package cn.tinybee.ke.common.util.security;

import cn.hutool.crypto.SecureUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@Slf4j
public class JJWTUtils {

    /**
     * pc token前缀
     */
    public static final String PC_TOKEN_PRE = "token:pc:";
    public static final String APP_TOKEN_PRE = "token：app:";
    public static final String H5_TOKEN_PRE = "token:h5:";
    public static final String TOKEN_SECRET = SecureUtil.md5("tinybee_portal_scret");

    private static byte[] apiKeySecretBytes() {
//        System.out.println("===========================================================>\t"+TOKEN_SECRET);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(TOKEN_SECRET);
        return apiKeySecretBytes;
    }

    public static String generateToken(String id, String username, Map<String, Object> claims) {
        // 设置签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //设置密钥
        Key signingKey = new SecretKeySpec(apiKeySecretBytes(), signatureAlgorithm.getJcaName());

        //设置JWT claims
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setId(id)
//                .setIssuedAt(now)
                .setAudience("iot")
                .setIssuer("huanghao")  //设置发行者，自定义
                .claim("username", username)
                .addClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //设置超时时间
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }

        //生成JWT
        return builder.compact();
//        return Jwts.builder().claim("username", username).
//                signWith(SignatureAlgorithm.HS256, TOKEN_SECRET.getBytes())
//                .addClaims(claims)
//                .compact();
    }

    public static boolean verify(String token) {
        if (StringUtils.isBlank(token)) {
            log.info("Token不能为空");
            return false;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(apiKeySecretBytes())
                    .parseClaimsJws(token).getBody();
            //将超时时间格式化为时间戳time
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            String timeFormat = sdf.format(claims.getExpiration());

//            Date date = sdf.parse(timeFormat);
//            long time = date.getTime();
//            long currentTime = System.currentTimeMillis();
            return "huanghao".equals(claims.getIssuer()) &&
                    "iot".equals(claims.getAudience()) &&
//                    (time > currentTime) &&
                    claims.get("username") != null;

        }catch (Exception e){
            e.printStackTrace();
            log.error("Token格式有误");
            return false;
        }
    }

//    public static boolean verify(String token) {
//        try {
//            return Jwts.parser().isSigned(token);
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public static String getUsernameFromToken(String token) {
//        try {
//            Map<String, Object> body = (Map<String, Object>) parse(token);
//            return (String) body.get("username");
//        } catch (Exception e) {
//            return null;
//        }
        Claims claims = Jwts.parser()
                .setSigningKey(apiKeySecretBytes())
                .parseClaimsJws(token).getBody();
        return claims.get("username").toString();

    }

    public static Object parse(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET.getBytes()).parse(token).getBody();
    }


    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("test", "Test1");
        claims.put("test2", "Test3");
        claims.put("test4", "Test5");
        claims.put("test6", "Test6");
        claims.put("test7", "Test7");
//		String str = generateToken("1570356753@qq.com", "123", claims);
//		System.out.println(str);
//		System.out.println(verify(str));
//		parse(str, "123");
        System.out.println(SecureUtil.md5("tinybee_portal_scret"));
        System.out.println(SecureUtil.md5("tinybee_portal_scret"));
    }

}
