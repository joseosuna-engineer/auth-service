/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.prottonne.lab.auth.entity.RequestLogin;
import net.prottonne.lab.auth.entity.ResponseLogin;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Osuna
 */
@RestController
public class LoginController {

    @RequestMapping(value = "lab/login", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseLogin auth(@RequestBody RequestLogin requestLogin) {
        if ("user@email.net".equals(requestLogin.getEmail())
                && "1234".equals(requestLogin.getPassword())) {
            return new ResponseLogin(generateJwtToken());
        }

        throw new RuntimeException("access denied");
    }

    private String generateJwtToken() {
        try {
            Date issue = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            Date expiration = Date.from(LocalDateTime.now().plusHours(1L).atZone(ZoneId.systemDefault()).toInstant());
            return Jwts.builder()
                    .setId("ccbe3aa4-4278-4761-860e-805caca3fb64")
                    .setIssuedAt(issue)
                    .setExpiration(expiration)
                    .claim("data", "8675A79C-6D07-4293-994B-E5510945CB66")
                    .signWith(SignatureAlgorithm.HS256, "110A7B279F5D".getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
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
}
