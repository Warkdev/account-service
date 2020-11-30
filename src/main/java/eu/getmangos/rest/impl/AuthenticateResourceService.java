package eu.getmangos.rest.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import eu.getmangos.dao.AccountDAO;
import eu.getmangos.dto.MessageDTO;
import eu.getmangos.dto.srp.ServerCredentialsDTO;
import eu.getmangos.entities.Account;
import eu.getmangos.rest.AuthenticateResource;
import eu.getmangos.security.SRPServer;
import eu.getmangos.utils.BigNumber;

@ApplicationScoped
@Path("")
public class AuthenticateResourceService implements AuthenticateResource {

    @Inject private Logger logger;

    @Inject private SRPServer server;

    @Inject private AccountDAO controller;

    @Override
    public Response challenge(String username) {
        logger.debug("challenge() entry.");

        ServerCredentialsDTO credentials = new ServerCredentialsDTO();

        if(username == null || username.isBlank()) {
            logger.debug("Username is null or empty");
            return Response.status(400).entity(new MessageDTO("The username is null or empty")).build();
        }

        Account account = controller.search(username);
        if (account == null) {
            logger.debug("Account does not exist");
            return Response.status(404).entity(new MessageDTO("No account matching this username")).build();
        }

        server.setI(username.getBytes());
        server.setS(new BigNumber(account.getS()));
        server.setV(new BigNumber(account.getV()));
        server.step1();

        credentials.setSalt(account.getS());
        credentials.setB(server.getB().toHexString());

        logger.debug("challenge() exit.");
        return Response.status(200).entity(credentials).build();
    }
}
