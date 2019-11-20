package org.synapse.oauth.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("oauth.properties")
public class OauthProperties {
    private String secretKey;
    private int defaultAccessTokenTimeout = 3600;
    private int defaultRefreshTokenTimeout = 84600;

}
