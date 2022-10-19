package com.example.cloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JWT生产Token工具类
 */
@Slf4j
@Component
public class JwtTokenUtil {
    private static JwtTokenUtil jwtTokenUtil;

    @PostConstruct
    public void init() {
        jwtTokenUtil = this;
    }

    /**
     * 创建token
     *
     * @param userId
     * @param username
     * @param tenantId 租户ID
     * @param admin 用户类型 0：普通用户 2平台管理员
     * @param clientGroup
     * @return
     */
    public static String createToken(String userId, String username, Integer tenantId, Integer admin, String clientGroup) {
        String token = Jwts.builder()// 设置JWT
                .setId(userId) // 用户Id
                .setSubject(username) // 主题
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer("cloud-example") // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE * 1000)) // 过期时间
                .signWith(generateKey()) // 签名算法、密钥
                // .claim("authorities", authorities)// 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
                .claim(CLAIM_CLIENT_GROUP, (Strings.hasText(clientGroup) ? clientGroup : CLIENT_GROUP))
                .claim("tenantId", tenantId)
                .claim("admin", admin)
                .compact();
        return token;
    }

    /**
     * 创建token
     *
     * @param userId
     * @param username
     * @param tenantId 租户ID
     * @param admin 用户类型 0：普通用户 2平台管理员
     * @param clientGroup
     * @return
     */
    public static String createToken(String userId, String username, Integer tenantId, Integer admin, String clientGroup,Integer myExpire) {
        String token = Jwts.builder()// 设置JWT
                .setId(userId) // 用户Id
                .setSubject(username) // 主题
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer("cloud-example") // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + myExpire * 1000)) // 过期时间
                .signWith(generateKey()) // 签名算法、密钥
                // .claim("authorities", authorities)// 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
                .claim(CLAIM_CLIENT_GROUP, (Strings.hasText(clientGroup) ? clientGroup : CLIENT_GROUP))
                .claim("tenantId", tenantId)
                .claim("admin", admin)
                .compact();
        return token;
    }

    /**
     * 解析Token
     *
     * @param token Token信息
     * @return
     */
    public static Claims parseToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
        }
        return null;
    }

    public static SecretKey generateKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    /**
     * 是否过期
     *
     * @param token 口令
     * @return 过期返回True，未过期返回false
     */
    public static boolean isExpired(String token) {
        Instant instant = JwtTokenUtil.parseToken(token).getExpiration().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime expirationTime = instant.atZone(zoneId).toLocalDateTime();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.compareTo(expirationTime) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户名生成Redis存储键名
     *
     * @param username
     * @return
     */
    public static String getRedisTokenKey(String username) {
        return "AUTH_SERVER_TOKEN_".concat(username);
    }

    /**
     * 密匙Key
     */
    public static String SECRET;

    /**
     * HeaderKey
     */
    public static String TOKEN_HEADER = "Authorization";

    /**
     * Token前缀
     */
    public static String TOKEN_PREFIX = "Bearer ";

    /**
     * 默认客户端分组
     */
    public static String CLIENT_GROUP = "default";

    /**
     * token中claim自定义权限键名
     */
    public static String CLAIM_AUTHORITIES = "authorities";

    /**
     * token中claim自定义客户端分组键名
     */
    public static String CLAIM_CLIENT_GROUP = "client_group";

    /**
     * 过期时间
     */
    public static Integer EXPIRE;

    @Value("${uaa.jwt.secret:none}")
    public void setSecret(String secret) {
        JwtTokenUtil.SECRET = secret;
    }

    @Value("${uaa.jwt.expire:3600}")
    public void setExpire(Integer expire) {
        JwtTokenUtil.EXPIRE = expire;
    }
}
