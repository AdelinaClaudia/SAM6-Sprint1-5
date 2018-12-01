package org.app.service.ejb;

import java.util.Collection;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Client;

@Path("clienti")

@Stateless @LocalBean 
public class ClientServiceEJB extends EntityRepositoryBase<Client> implements ClientService {
	
	private static Logger logger = Logger.getLogger(ClientServiceEJB.class.getName());
	
	//DataService initialization
	//Injection 
	
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	//Constructor
	public ClientServiceEJB() {
	}
	//Initialization after Constuctor 

	@PostConstruct 
	public void init() {
		logger.info("POSTCONSTRUCT-INIT : "+ this.em);
	}
    //CRUD operation implemntation 
		
		//1.CREATE or UPDATE 
	@Override
	 public Client addClient(Client clientToAdd) {
		em.persist(clientToAdd);
		em.flush();
		em.refresh(clientToAdd);
		return clientToAdd;
	}
	
	//READ 
	@Override
	public Client getClientByIdClient(Integer idClient) {
		return em.find(Client.class, idClient);
	}
	public Collection<Client>getClient(){
		List<Client> clienti=em.createQuery("Select c From Client c", Client.class).getResultList();
		return clienti;
	}
	//REMOVE
	public String removeClient(Client clientToDelete) {
		clientToDelete=em.merge(clientToDelete);
		em.remove(clientToDelete);
		em.flush();
		return "Operatiunea de stergere realizata cu succes";
	}
	
	//Custom READ 
	@Override
	public Client getClientByNumePrenume(String numePrenumeClient) {
		return em.createQuery("SELECT c from Client c Where c.numePrenumeClient=:numePrenumeClient", Client.class)
				                           .setParameter("numePrenumeClient", numePrenumeClient)
				                           .getSingleResult();
		}
	
   //other-message
	public String getMessage() {
		return "ClientServiceEJB is on ... ";
	}
	//---------------------------------------------------------------------------------------------
	
	@Override
	@GET @Path("/{idClient") 	/* MSD-S2/rest/clienti/data/{id} 	REST-resource: clienti-entity*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Client getByIdClient(@PathParam("idClient") Integer idClient) {
		Client  client = super.getById(idClient);
		logger.info("**** DEBUG REST getById(" + idClient +") = " + client);
		return client;}	
	
	
	
	@Override
	@GET 					/* MSD-S2/rest/clienti 		REST-resource: clienti-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Client> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}

	
	
	
	
	
	
	
	@POST 					/* MSD-S2/rest/clienti 		REST-resource: clienti-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Client> addIntoCollection(Client client) {
		// save aggregate
		super.add(client);
		logger.info("**** DEBUG REST save aggregate POST");
		// return updated collection
		return super.toCollection();
	}
	
	@DELETE 				/* MSD-S2/rest/clienti 		REST-resource: clienti-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Collection<Client> removeFromCollection(Client client) {
		logger.info("DEBUG: called REMOVE - project: " + client);
		super.remove(client);
		return super.toCollection();
	}
	@DELETE @Path("/{idClient}") 	/* MSD-S2/rest/clienti/{id} 	REST-resource: client-entity*/	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public void remove(@PathParam("idClient")Integer idClient) {
		logger.info("DEBUG: called REMOVE - ByIdClient() for client >>>>>>>>>>>>>> simplified ! + idClient");
		Client client = super.getById(idClient);
		super.remove(client);
	}
	@PUT @Path("/{idClient}") 	/* MSD-S2/rest/employees/{idClient} 	REST-resource: employee-entity*/	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Client add(Client client) {
		// restore aggregation-relation
		
		//logger.info("**** DEBUG REST restore aggregation-relation PUT");
		// save aggregate
		//logger.info("**** DEBUG REST save aggregate PUT");
		client = super.add(client);
		// return updated collection	
		return client;
	}
	
}
