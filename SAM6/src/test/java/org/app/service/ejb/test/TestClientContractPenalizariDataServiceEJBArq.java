package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import org.jboss.arquillian.junit.Arquillian;

import java.util.List;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.AngajatService;
import org.app.service.ejb.AngajatiServiceEJB;
import org.app.service.ejb.ClientContractPenalizariDataService;
import org.app.service.ejb.ClientContractPenalizariDataServiceEJB;
import org.app.service.ejb.ClientContractPenalizariDataService;
import org.app.service.ejb.ClientContractPenalizariDataServiceEJB;
import org.app.service.ejb.ClientService;
import org.app.service.ejb.ClientServiceEJB;
import org.app.service.ejb.ContractService;
import org.app.service.ejb.ContractServiceEJB;
import org.app.service.ejb.PenalizareService;
import org.app.service.ejb.PenalizareServiceEJB;
import org.app.service.entities.Angajat;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestClientContractPenalizariDataServiceEJBArq {
	
	private static Logger logger=Logger.getLogger(TestClientContractPenalizariDataServiceEJBArq.class.getName());
	

	

	@Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class,"scrum-test-ejb1.war")
                
                .addPackage(EntityRepository.class.getPackage())   
                
                .addPackage(Client.class.getPackage())
                
                .addPackage(ClientContractPenalizariDataService.class.getPackage())
                
                .addPackage(ClientContractPenalizariDataServiceEJB.class.getPackage())
                .addPackage(PenalizareService.class.getPackage())
                
                .addPackage(PenalizareServiceEJB.class.getPackage())
                
                .addClass(PenalizareService.class).addClass(PenalizareServiceEJB.class)
                
                .addClass(ClientContractPenalizariDataService.class)
                .addClass(ClientContractPenalizariDataServiceEJB.class)
                
                .addAsResource("META-INF/persistence.xml")
                
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@EJB
	private static ClientContractPenalizariDataService service;
	
	@Test

	public void test4_GetClient() {
		logger.info("Debug:Junit TESTING: testGetClient 147 ...");
		Client client=service.getById(147);
		assertNotNull("Fail to get Client 147!",client);
	}
	
	
	
	
	
	
	@Test
	public void test3_CreateNewClient() {
		
		Client client=service.createNewClient(147);
		assertNotNull("Fail to create new client in repository", client);
		//update client 
		client.setIbanClient(client.getIbanClient()+"-changed by test client");
		List<Contract> contracte=client.getContracte();
		for(Contract c:contracte)
			c.setSumapenalizare(c.getSumapenalizare()+"changed by test client");
		client=service.add(client);
		assertNotNull("Fail to save new client in repository", client);
		logger.info("DEBUG createNewClient: queried Client"+client);
		//check read
		client=service.getById(147);
		assertNotNull("Fail to find changed client in repository", client);
		logger.info("DEBUG createNewClient: queried Client"+client);
		
	}

	
	
	
	
	@Test
	public void test2_DeleteClient() {
		logger.info("DEBUG: Junit TESTING: testDeleteClient4...");
		Client client=service.getById(147);
		if (client!=null)
		service.remove(client);
		/*client=service.getById(105);
	    assertNotNull("Fail to delete  client  85", client);*/
	}
	
	/*
	

//Junit testMethod


//test 2 create aggregate

@Test
public void test3_CreateNewClient() {
	
	Client client=service.createNewClient(1002);
	assertNotNull("Fail to create new client in repository", client);
	//update client 
	client.setIbanClient(client.getIbanClient()+"-changed by test client");
	List<Contract> contracte=client.getContracte();
	for(Contract c:contracte)
		c.setSumapenalizare(c.getSumapenalizare()+"changed by test client");
	client=service.add(client);
	assertNotNull("Fail to save new client in repository", client);
	logger.info("DEBUG createNewClient: queried Client"+client);
	//check read
	client=service.getById(1002);
	assertNotNull("Fail to find changed client in repository", client);
	logger.info("DEBUG createNewClient: queried Client"+client);
	
}




*/

}
