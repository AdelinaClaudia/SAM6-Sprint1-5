package org.app.service.ejb;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.utils.DateUtils;
import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;
import org.jboss.logging.Logger;

@Stateless @LocalBean 
public class ClientContractPenalizariDataServiceEJB extends EntityRepositoryBase<Client>
 implements ClientContractPenalizariDataService, Serializable{
 
private static Logger logger=Logger.getLogger(ClientContractPenalizariDataServiceEJB.class.getName());

@PersistenceContext(unitName="MSD")
private EntityManager em;
public ClientContractPenalizariDataServiceEJB() {}
@EJB
 private PenalizareService penalizareService;
 private EntityRepository <Contract> contractRepository;

 @PostConstruct
 public void init() {
	 contractRepository=new EntityRepositoryBase<Contract>(this.em,Contract.class);
	 logger.info("POSTCONSTRUCT-INIT penalizareService:"+this.contractRepository);
	 logger.info("POSTCONSTRUCT-INIT penalizareService:"+this.penalizareService);
 }
 
/*@POST
@Path("/new/{idClient}")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
 public Client createNewClient(@PathParam("idClient")Integer idClient) {
	
	 Client client=new Client(idClient,"IliescuElena",22233,new Date(2000/12/12),"brd","3223ro2323");
	 List<Contract>contractClient=new ArrayList<>();
	 Date dataIncheieriiContract = new Date();
	 Long Interval=30l*24*60*60*1000;
	 Integer numarContracte=3;
	 for (int i=0; i<=numarContracte-1;i++) {
		contractClient.add(new Contract(null,dataIncheieriiContract,new Date(dataIncheieriiContract.getTime()+i*Interval),"furnizare obiecte casnice"+client.getNumePrenumeClient(),230.90f,client,0.0f)); 
	    
	 }
	 client.setContracte(contractClient);
	 this.add(client);
	 return client;

 }*/
 

//CREAE AGREGAT
 
public Client createNewClient(Integer idClient) {
	
	 Client client=new Client(idClient,"IliescuElena",22233,new Date(2000/12/12),"brd","3223ro2323");
	 List<Contract>contractClient=new ArrayList<>();
	 Date dataIncheieriiContract = new Date();
	 Long Interval=30l*24*60*60*1000;
	 Integer numarContracte=3;
	 for (int i=0; i<=numarContracte-1;i++) {
		contractClient.add(new Contract(null,dataIncheieriiContract,new Date(dataIncheieriiContract.getTime()+i*Interval),"furnizare obiecte casnice"+client.getNumePrenumeClient(),230.90f,client,0.0f)); 
	    
	 }
	 client.setContracte(contractClient);
	 this.add(client);
	 return client;

}


 public Contract  getContractByNumarContract(Integer numarContract) {
	 return contractRepository.getById(numarContract);
 }
 public String getMessage(){
	 return "ProjectSprint DataService is working....";
 }
}
