package org.app.service.ejb.test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;

import org.app.service.ejb.ClientService;
import org.app.service.ejb.ClientServiceEJB;

import org.app.service.entities.Client;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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

public class TestClientDataServiceEJBArq {
	private static Logger logger= Logger.getLogger(TestClientDataServiceEJBArq.class.getName());
	
//EJB DataService REF
	@EJB
	private static ClientService service ;
	
	 @Deployment

     public static Archive<?> createDeployment(){

            return ShrinkWrap
            			.create(WebArchive.class,"msd-test.war")

                         .addPackage(Client.class.getPackage())

                         .addPackage(EntityRepository.class.getPackage())

                         .addPackage(ClientService.class.getPackage())

                         .addPackage(ClientServiceEJB.class.getPackage())

                         .addAsResource("META-INF/persistence.xml")

                         .addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
                       
     }
////////////////-----teste
	@Test
	public void test1_getMessage() {
		logger.info("DEBUG:Junit TESTING: getMessage ...");
		String response=service.getMessage();
		assertNotNull("Data Service failed!",response);
		logger.info("DEBUG EJB Response ..."+response);
		
	}
	
	
	@Test
	public void test4_getClient() {
		logger.info("DEBUG:Junit TESTING: TestGetClient...");
		
		Collection<Client> client=service.getClient();
	
		assertTrue("Fail to read angajat!",client.size() >0);		
	}
	
	
	@Test
	public void test3_addClient() {
		logger.info("DEBUG:Junit TESTING: TestAddClient...");
		
		Integer clientiToAdd=3;
		for(int i=1;i<=clientiToAdd;i++)
		{
			service.addClient(new Client(null,"GeogescuClaudia",2343,new Date(1997/01/12),"BNR","RO11AAAA2B31007593840000"));
		}
		Collection<Client> client=service.getClient();
	
		assertTrue("fail to add client!",client.size()==clientiToAdd);		
	}
	
	@Test
	public void test2_DeleteClient() {
		logger.info("DEBUG:Junit TESTING: TestDeleteClient...");
		
		Collection<Client> clienti=service.getClient();
		for(Client a:clienti)
		service.removeClient(a);
		
		Collection<Client> clientAfterDelete=service.getClient();
		assertTrue("fail to read client!",clientAfterDelete.size()==0);		
	}
	
	
	
	
}
