package net.diegolemos.bankapp.account;

import net.diegolemos.bankapp.client.Client;
import net.diegolemos.bankapp.client.ClientRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("account")
@Consumes("application/json;charset=utf-8")
@Produces("application/json;charset=utf-8")
public class AccountResource {

    private final AccountRepository accounts;
    private final ClientRepository clients;

    @Inject
    public AccountResource(AccountRepository accounts, ClientRepository clients) {
        this.accounts = accounts;
        this.clients = clients;
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username") String username) {
        Client client = clients.withUsername(username);
        Account account = accounts.forHolder(client);
        return ok(account).build();
    }
}
