package com.robin.nass.common.securityUtils;

import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/21 23:49
 */
public class JwtUtil {
    public static String signature = "Robin";
    public static int expirationTime = 3600 * 60 * 24;

    /**
     * 生成token
     * @param username
     * @param password
     * @return
     */
    public static String LoginToToken(String username,String password){
        JwtBuilder jwtBuilder = Jwts.builder();
        String loginToken = jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", username)
                .claim("password", password)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,signature)
                .setSubject("loginToken")
                .compact();
        return loginToken;
    }

    /**
     * token校验
     * @param request
     * @return
     */
    public static boolean verifyToken(HttpServletRequest request){
        String token = request.getHeader("loginToken");
        if (!token.equals(null)){
            JwtParser parser = Jwts.parser();
            Jws<Claims> claimsJws = parser.setSigningKey(signature)
                    .parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Date expiration = claims.getExpiration();
            if (tokenIsExpired(expiration)){    //token过期
                return false;
            }
            return true;
        }
        return false;
    }

    public static Boolean tokenIsExpired(Date tokenExpired) {
        return !new Date().before(tokenExpired);
    }
}
