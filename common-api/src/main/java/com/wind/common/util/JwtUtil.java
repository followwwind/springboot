package com.wind.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: JwtUtil
 * @Package com.wind.common.util
 * @Description: Json Web Token工具类
 * @author wind
 * @date 2018/10/17 8:39
 * @version V1.0
 */
public class JwtUtil {

    /**
     * 生成密钥
     * @param base64Key
     * @return
     */
    private static SecretKey generalKey(String base64Key) {
        byte[] secretBytes = Base64.decodeBase64(base64Key);
        return new SecretKeySpec(secretBytes, SignatureAlgorithm.RS256.getJcaName());
    }

    /**
     * 解析Json web token
     * @param token
     * @return
     */
    public static Claims parseJWT(String token, String issuer, String secret) {
        return Jwts.parser()
                .requireIssuer(issuer)
                .setSigningKey(generalKey(secret))
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 生成Json web token
     * @param issuedAt 签发时间
     * @param subject 主体
     * @param claims
     * @return
     */
    public static String createJWT(long issuedAt, String subject, Map<String, Object> claims, String issuer, String secret) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;

        Claims cal = new DefaultClaims();
        cal.setSubject(subject).setIssuer(issuer)
                .setIssuedAt(new Date(issuedAt)).putAll(claims);
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", SignatureAlgorithm.RS256.getValue())
                .setClaims(cal)
                .compressWith(CompressionCodecs.GZIP)
                .signWith(algorithm, generalKey(secret));

        return builder.compact();
    }

    public static void main(String[] args) {
        String issuer = "http://www.lovecici.com";
        String secret = "N2QyZXYqeVdndkt3ak5eXks3UTZYbTBPZFRwV2ZxUjQ";
        Map<String, Object> map = new HashMap<>(2);
        map.put("schoolId", "a0e148f4d209413d9343a9e2c97dd32e");
        String token = JwtUtil.createJWT(System.currentTimeMillis(),
                "123" , map, issuer, secret);
        System.out.println(token);
        Claims claims = JwtUtil.parseJWT(token, issuer, secret);
        System.out.println(claims);
    }
}
