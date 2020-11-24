package eu.getmangos.rest.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import eu.getmangos.rest.AccountResource;

@Readiness
@ApplicationScoped
public class ServiceReadinessCheck implements HealthCheck {

    private static final String readinessCheck = AccountResource.class.getSimpleName() + " Readiness Check";

    @Inject
    @ConfigProperty(name = "eu_getmangos_account_service_inMaintenance")
    Provider<String> inMaintenance;

    @Override
    public HealthCheckResponse call() {
        if (inMaintenance != null && inMaintenance.get().equalsIgnoreCase("true")) {
            return HealthCheckResponse.down(readinessCheck);
        }
        return HealthCheckResponse.up(readinessCheck);
    }
}
