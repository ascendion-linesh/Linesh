package com.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * Configuration class for RestTemplate used to communicate with Talon.One's Integration API.
 * <p>
 * This configuration ensures:
 * <ul>
 *     <li>Singleton, thread-safe RestTemplate bean for TalonOneClient usage.</li>
 *     <li>Secure injection of the Talon.One API key via application properties.</li>
 *     <li>Attaching necessary authentication headers to each request.</li>
 *     <li>Concise, non-sensitive logging of HTTP method and URI for outbound requests.</li>
 * </ul>
 * <p>
 * The API key should be set in <code>application.properties</code> as:
 * <pre>
 * talonone.api-key=YOUR_TALONONE_API_KEY
 * </pre>
 */
@Configuration
public class RestTemplateConfig {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

    /**
     * Talon.One API key injected from application properties.
     */
    @Value("${talonone.api-key}")
    private String talonOneApiKey;

    /**
     * Defines a singleton, thread-safe RestTemplate bean configured for Talon.One API integration.
     *
     * @return configured RestTemplate instance
     */
    @Bean
    public RestTemplate talonOneRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory())
        );
        restTemplate.setInterceptors(Collections.singletonList(talonOneApiInterceptor()));
        return restTemplate;
    }

    /**
     * Interceptor that attaches the Talon.One API key to the Authorization header
     * and logs essential request details (HTTP method and URI).
     *
     * @return ClientHttpRequestInterceptor instance
     */
    private ClientHttpRequestInterceptor talonOneApiInterceptor() {
        return new ClientHttpRequestInterceptor() {
            @Override
            public org.springframework.http.client.ClientHttpResponse intercept(
                    HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

                // Attach Authorization header
                request.getHeaders().set("Authorization", "ApiKey-v1 " + talonOneApiKey);

                // Log method and URI (no sensitive info)
                logger.info("[Talon.One] Outbound Request: {} {}", request.getMethod(), request.getURI());

                return execution.execute(request, body);
            }
        };
    }
}
