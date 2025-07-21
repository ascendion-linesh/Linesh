package talonone;

import com.app.dto.ProfileDTO;
import com.app.dto.SessionDTO;
import com.app.dto.RewardsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * TalonOneClient is a reusable and centralized client for interacting with the Talon.One Integration API.
 * It provides methods to update customer profiles, evaluate sessions for rewards, and confirm loyalty points.
 * <p>
 * Configuration properties (base URL and API key) must be set in application.properties:
 * <pre>
 * talonone.base-url=https://YOUR_TALONONE_DOMAIN.talon.one
 * talonone.api-key=YOUR_TALONONE_API_KEY
 * </pre>
 * <p>
 * Usage example:
 * <pre>
 * {@code
 * @Autowired
 * private TalonOneClient talonOneClient;
 *
 * talonOneClient.updateProfile("user123", profileDto);
 * RewardsResponse rewards = talonOneClient.evaluateSession(sessionDto);
 * talonOneClient.confirmLoyalty("user123", 100.0);
 * }
 * </pre>
 */
@Component
public class TalonOneClient {

    @Value("${talonone.base-url}")
    private String baseUrl;

    @Value("${talonone.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    /**
     * Constructs a TalonOneClient with the provided RestTemplate.
     *
     * @param restTemplate the RestTemplate to use for HTTP requests
     */
    public TalonOneClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Updates a user profile in Talon.One.
     *
     * @param userId the user ID
     * @param dto    the profile data transfer object
     * @throws TalonOneClientException if the API call fails
     */
    public void updateProfile(String userId, ProfileDTO dto) {
        String url = String.format("%s/v1/profiles/%s", baseUrl, userId);
        HttpEntity<ProfileDTO> entity = new HttpEntity<>(dto, createHeaders());
        try {
            restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
        } catch (HttpStatusCodeException ex) {
            throw new TalonOneClientException("Failed to update profile: " + ex.getResponseBodyAsString(), ex);
        } catch (Exception ex) {
            throw new TalonOneClientException("Failed to update profile: " + ex.getMessage(), ex);
        }
    }

    /**
     * Evaluates a session for rewards and discounts in Talon.One.
     *
     * @param dto the session data transfer object
     * @return RewardsResponse containing applicable rewards and discounts
     * @throws TalonOneClientException if the API call fails
     */
    public RewardsResponse evaluateSession(SessionDTO dto) {
        String url = String.format("%s/v1/sessions", baseUrl);
        HttpEntity<SessionDTO> entity = new HttpEntity<>(dto, createHeaders());
        try {
            ResponseEntity<RewardsResponse> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, RewardsResponse.class);
            return response.getBody();
        } catch (HttpStatusCodeException ex) {
            throw new TalonOneClientException("Failed to evaluate session: " + ex.getResponseBodyAsString(), ex);
        } catch (Exception ex) {
            throw new TalonOneClientException("Failed to evaluate session: " + ex.getMessage(), ex);
        }
    }

    /**
     * Confirms loyalty point usage for a user in Talon.One.
     *
     * @param userId      the user ID
     * @param totalAmount the total amount to confirm
     * @throws TalonOneClientException if the API call fails
     */
    public void confirmLoyalty(String userId, double totalAmount) {
        String url = String.format("%s/v1/loyalty/%s/confirm", baseUrl, userId);
        HttpEntity<ConfirmLoyaltyRequest> entity = new HttpEntity<>(new ConfirmLoyaltyRequest(totalAmount), createHeaders());
        try {
            restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
        } catch (HttpStatusCodeException ex) {
            throw new TalonOneClientException("Failed to confirm loyalty: " + ex.getResponseBodyAsString(), ex);
        } catch (Exception ex) {
            throw new TalonOneClientException("Failed to confirm loyalty: " + ex.getMessage(), ex);
        }
    }

    /**
     * Creates HTTP headers with the Authorization header for Talon.One API.
     *
     * @return HttpHeaders with Authorization set
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "ApiKey-v1 " + apiKey);
        return headers;
    }

    /**
     * DTO for loyalty confirmation request body.
     */
    private static class ConfirmLoyaltyRequest {
        private final double total;

        public ConfirmLoyaltyRequest(double total) {
            this.total = total;
        }

        public double getTotal() {
            return total;
        }
    }

    /**
     * Exception class for TalonOneClient errors.
     */
    public static class TalonOneClientException extends RuntimeException {
        public TalonOneClientException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
