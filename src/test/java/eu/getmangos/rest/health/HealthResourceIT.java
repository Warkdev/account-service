package eu.getmangos.rest.health;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HealthResourceIT {

    private static String baseUrl;
    private static String port;
    private static final String HEALTH_ENDPOINT = "/health";
    private static final String LIVENESS_ENDPOINT = "/health/live";
    private static final String READINESS_ENDPOINT = "/health/ready";

    private Client client;
    private Response response;

    @BeforeAll
    public static void oneTimeSetup() {
        port = System.getProperty("default.http.port");
        baseUrl = "http://localhost:"+ port +"/";
    }

    @BeforeEach
    public void setup() {
        response = null;
        client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
    }

    @AfterEach
    public void teardown() {
        response.close();
        client.close();
    }

    @Test
    public void testHealthEndpoint() {
        String healthURL = baseUrl + HEALTH_ENDPOINT;
        response = this.getResponse(baseUrl + HEALTH_ENDPOINT);
        this.assertResponse(healthURL, response);

        JsonObject healthJson = response.readEntity(JsonObject.class);
        String expectedOutcome = "UP";
        String actualOutcome = healthJson.getString("status");
        assertEquals(expectedOutcome, actualOutcome, "Application should be healthy");

        JsonObject healthCheck = healthJson.getJsonArray("checks").getJsonObject(0);
        String healthCheckName = healthCheck.getString("name");
        actualOutcome = healthCheck.getString("status");
        assertEquals(expectedOutcome, actualOutcome, healthCheckName + " wasn't healthy");

        healthCheck = healthJson.getJsonArray("checks").getJsonObject(1);
        healthCheckName = healthCheck.getString("name");
        actualOutcome = healthCheck.getString("status");
        assertEquals(expectedOutcome, actualOutcome, healthCheckName + " wasn't healthy");
    }

    @Test
    public void testLivenessEndpoint() {
        String livenessURL = baseUrl + LIVENESS_ENDPOINT;
        response = this.getResponse(baseUrl + LIVENESS_ENDPOINT);
        this.assertResponse(livenessURL, response);

        JsonObject healthJson = response.readEntity(JsonObject.class);
        String expectedOutcome = "UP";
        String actualOutcome = healthJson.getString("status");
        assertEquals(expectedOutcome, actualOutcome, "Applications liveness check passed");
    }

    @Test
    public void testReadinessEndpoint() {
        String readinessURL = baseUrl + READINESS_ENDPOINT;
        response = this.getResponse(baseUrl + READINESS_ENDPOINT);
        this.assertResponse(readinessURL, response);

        JsonObject healthJson = response.readEntity(JsonObject.class);
        String expectedOutcome = "UP";
        String actualOutcome = healthJson.getString("status");
        assertEquals(expectedOutcome, actualOutcome, "Applications readiness check passed");
    }

    /**
     * <p>
     * Returns response information from the specified URL.
     * </p>
     *
     * @param url
     *          - target URL.
     * @return Response object with the response from the specified URL.
     */
    private Response getResponse(String url) {
        return client.target(url).request().get();
    }

    /**
     * <p>
     * Asserts that the given URL has the correct response code of 200.
     * </p>
     *
     * @param url
     *          - target URL.
     * @param response
     *          - response received from the target URL.
     */
    private void assertResponse(String url, Response response) {
        assertEquals(200, response.getStatus(), "Incorrect response code from " + url);
    }

}
