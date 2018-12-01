package org.app.service.ejb;
import org.app.service.entities.*;

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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepositoryBase;

@Path("contracte")

@Stateless @LocalBean 
public class ContractServiceEJB extends EntityRepositoryBase<Contract> implements ContractService{
	
		// /MSD-S4/data/contracte Contract Collection GET toCollection()
		// /MSD-S4/data/contracte Contract Collection POST addIntoCollection(Contract)
		//	/MSD-S4/data/contracte Task Collection DELETE removeFromCollection(Contract)
		//	/MSD-S4/data/contracte/{idClient} Client GET getById(Integer)
		//	/MSD-S4/data/contracte/{idClient} Client PUT add(Contract)
		//	/MSD-S4/data/contracte/{idClient} Client DELETE remove(Integer)

	@Override
	@GET 					/* MSD-S2/rest/contracte		REST-resource: contracte-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Contract> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}
	/*@Override
	@GET @Path("/{idClient}") 	 MSD-S2/rest/contracte/data/{idClient} 	REST-resource: contract-entity
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Contract getById(@PathParam("idClient") Integer idClient) {
		Contract contract = super.getById(idClient);
		logger.info("**** DEBUG REST getById(" + idClient +") = " + contract);
		return contract;
	}*/
	
	@POST 					/* MSD-S2/rest/contracte 		REST-resource: contracte-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Contract> addIntoCollection(Contract contract) {
		// save aggregate
		super.add(contract);
		logger.info("**** DEBUG REST save aggregate POST");
		// return updated collection
		return super.toCollection();
	}
	
	@DELETE 				/* MSD-S2/rest/tasks 		REST-resource: tasks-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Collection<Contract> removeFromCollection(Contract contract) {
		logger.info("DEBUG: called REMOVE - project: " + contract);
		super.remove(contract);
		return super.toCollection();
	}

	@PUT @Path("/{idClient}") 	/* MSD-S2/rest/tasks/{id} 	REST-resource: task-entity*/	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Contract add(Contract contract) {
		// restore aggregation-relation
		
		//logger.info("**** DEBUG REST restore aggregation-relation PUT");
		// save aggregate
		//logger.info("**** DEBUG REST save aggregate PUT");
		contract = super.add(contract);
		// return updated collection	
		return contract;
	}


//-------------------------------------------------------------------
	
	
	private static Logger logger = Logger.getLogger(ContractServiceEJB.class.getName());
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public ContractServiceEJB() {
		
	}
	@PostConstruct 
	public void init() {
		logger.info("POSTCONSTRUCT-INIT : "+ this.em);
	}
	@Override
	 public Contract addContract(Contract contractToAdd) {
		em.persist(contractToAdd);
		em.flush();
		em.refresh(contractToAdd);
		return contractToAdd;
	}
	@Override
	public Contract getByNumarContract(Integer numarContract){

		return em.find(Contract.class, numarContract);
	}
	public Collection<Contract>getContract(){
		List<Contract> contract=em.createQuery("Select co From Contract co", Contract.class).getResultList();
		return contract;
	}
	//custome read 
	public Contract getContractByNumarContract(Integer numar) {
		return em.createQuery("SELECT co from Contract co Where co.numarContract=:numar", Contract.class).setParameter("numarContract", numar).getSingleResult();
	}
	
	public String removeContract(Contract contractToDelete) {
		contractToDelete=em.merge(contractToDelete);
		em.remove(contractToDelete);
		em.flush();
		return "true";
	}
	
	public String getMessage() {
		return "ContractServiceEJB is on ... ";
	}
	
}
