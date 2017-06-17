/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.controller.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import net.prottonne.lab.auth.constant.ErrorMessage;
import net.prottonne.lab.auth.controller.iservice.IJwt;
import net.prottonne.lab.auth.entity.RequestLogin;
import net.prottonne.lab.auth.entity.ResponseLogin;
import net.prottonne.lab.common.util.auth.JwtUtil;
import net.prottonne.lab.common.util.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Osuna
 */
@Service
public class JwtService implements IJwt {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Long LAP_TIME_IN_MINUTES = 30L;
    private final String CLAIM_NAME = "data";

    @Override
    public ResponseLogin auth(RequestLogin requestLogin) {
        try {
            if ("user@email.net".equals(requestLogin.getEmail())
                    && "1234".equals(requestLogin.getPassword())) {
                return new ResponseLogin(generateJwtToken(requestLogin));
            }
        } catch (Exception e) {

            logger.error("{} {} {}", ErrorMessage.CANNOT_BE_PROCESSED.getValue(),
                    requestLogin, e);
            throw new TechnicalException(ErrorMessage.CANNOT_BE_PROCESSED.getValue());

        }

        logger.error("{} {}", ErrorMessage.ACCESS_DENIED.getValue(),
                requestLogin);
        throw new TechnicalException(ErrorMessage.CANNOT_BE_PROCESSED.getValue());
    }

    private String generateJwtToken(RequestLogin requestLogin) {

        LocalDateTime now = LocalDateTime.now();
        return Jwts.builder()
                .setId(getJwtId())
                .setIssuedAt(getIssueDate(now))
                .setExpiration(getExpirationDate(now))
                .claim(CLAIM_NAME, getIdUser(requestLogin))
                .signWith(SignatureAlgorithm.HS512, JwtUtil.getSecret())
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
        return requestLogin.getEmail();
    }

}
