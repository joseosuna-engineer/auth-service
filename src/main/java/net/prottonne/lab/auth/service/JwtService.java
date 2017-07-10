/*
 * Copyright 2017 Prottonne
 */
package net.prottonne.lab.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import net.prottonne.lab.auth.iservice.IJwt;
import net.prottonne.lab.auth.entity.RequestLogin;
import net.prottonne.lab.auth.entity.RequestProfile;
import net.prottonne.lab.auth.entity.ResponseLogin;
import net.prottonne.lab.auth.entity.User;
import net.prottonne.lab.common.util.auth.JwtUtil;
import net.prottonne.lab.common.util.exception.ErrorMessage;
import net.prottonne.lab.common.util.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jose Osuna
 */
@Service
public class JwtService implements IJwt {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Long LAP_TIME_IN_MINUTES = 5L;
    private final String CLAIM_NAME = "user";

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${profile.serviceId}")
    private String profileServiceId;

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
                .claim(CLAIM_NAME, getUser(requestLogin))
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

    private User getUser(RequestLogin requestLogin) {

        logger.info("requestLogin {}", requestLogin);

        logger.info("profileServiceId={}", profileServiceId);

        ServiceInstance instance = loadBalancer.choose(profileServiceId);

        URI uri = instance.getUri();

        RequestProfile requestProfile = new RequestProfile();
        requestProfile.setEmail(requestLogin.getEmail());

        logger.info("requestProfile {}", requestProfile);

        ResponseEntity<User> responseEntity = restTemplate.
                postForEntity(
                        uri,
                        requestProfile,
                        User.class
                );

        logger.info("responseEntity getStatusCodeValue {}, getBody {}",
                responseEntity.getStatusCodeValue(),
                responseEntity.getBody());

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            User user = responseEntity.getBody();
            user.setAuth(Boolean.TRUE);
            return user;
        } else {
            logger.error("{} {}", ErrorMessage.CANNOT_BE_PROCESSED.getValue(),
                    requestProfile);
            throw new TechnicalException(ErrorMessage.CANNOT_BE_PROCESSED.getValue());
        }
    }

}
