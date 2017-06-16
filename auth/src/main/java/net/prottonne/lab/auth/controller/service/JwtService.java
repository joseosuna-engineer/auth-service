/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.prottonne.lab.auth.controller.LoginController;
import net.prottonne.lab.auth.controller.iservice.IJwt;
import net.prottonne.lab.auth.entity.RequestLogin;
import net.prottonne.lab.auth.entity.ResponseLogin;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Osuna
 */
@Service
public class JwtService implements IJwt {

    private final Long LAP_TIME_IN_MINUTES = 30L;
    private final String CLAIM_NAME = "data";

    @Override
    public ResponseLogin auth(RequestLogin requestLogin) {
        if ("user@email.net".equals(requestLogin.getEmail())
                && "1234".equals(requestLogin.getPassword())) {
            return new ResponseLogin(generateJwtToken(requestLogin));
        }

        throw new RuntimeException("access denied");
    }

    private String generateJwtToken(RequestLogin requestLogin) {

        LocalDateTime now = LocalDateTime.now();
        return Jwts.builder()
                .setId(getJwtId())
                .setIssuedAt(getIssueDate(now))
                .setExpiration(getExpirationDate(now))
                .claim(CLAIM_NAME, getIdUser(requestLogin))
                .signWith(SignatureAlgorithm.HS256, getSecret())
                .compact();

    }

    private String getJwtId() {
        return UUID.randomUUID().toString();
    }

    private Date getIssueDate(LocalDateTime now) {
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date getExpirationDate(LocalDateTime now) {
        return Date.from(now.plusMinutes(LAP_TIME_IN_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
    }

    private String getIdUser(RequestLogin requestLogin) {
        return "8675A79C-6D07-4293-994B-E5510945CB66";
    }

    private boolean validateJwtToken(String token) {
        try {

            Jwts.parser()
                    .setSigningKey("110A7B279F5D".getBytes("UTF-8"))
                    .parseClaimsJws(token);

            return true;

        } catch (SignatureException | UnsupportedEncodingException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);

            return false;
        }
    }

    private byte[] getSecret() {
        try {
            return "110A7B279F5D".getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(JwtService.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
