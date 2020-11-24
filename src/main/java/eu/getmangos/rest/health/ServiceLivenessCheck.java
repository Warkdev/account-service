package eu.getmangos.rest.health;

import javax.enterprise.context.ApplicationScoped;

import java.lang.management.MemoryMXBean;
import java.lang.management.ManagementFactory;

import org.eclipse.microprofile.health.Liveness;

import eu.getmangos.rest.AccountResource;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Liveness
@ApplicationScoped
public class ServiceLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        long memUsed = memBean.getHeapMemoryUsage().getUsed();
        long memMax = memBean.getHeapMemoryUsage().getMax();

        return HealthCheckResponse.named(
            AccountResource.class.getSimpleName() + " Liveness Check")
                                  .withData("memory used", memUsed)
                                  .withData("memory max", memMax)
                                  .state(memUsed < memMax * 0.9).build();
    }

}
