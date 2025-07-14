package com.eventsequor.crud_jpa.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class TokenJwtConfig {
    //public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public static final String base64Key = "kY+yaYOuY9HQH0q+zlxL7LtxXAJB1YeyV36Dw6N1P0Y=";
    public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key));
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE_APP_JSON = "application/json";
}
