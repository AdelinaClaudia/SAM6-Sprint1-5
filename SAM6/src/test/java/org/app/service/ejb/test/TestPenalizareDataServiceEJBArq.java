package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.AngajatService;
import org.app.service.ejb.AngajatiServiceEJB;
import org.app.service.ejb.PenalizareService;
import org.app.service.ejb.PenalizareServiceEJB;
import org.app.service.entities.Angajat;
import org.app.service.entities.Penalizare;
import org.app.service.entities.StatusPenalizare;
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
public class TestPenalizareDataServiceEJBArq {
	private static Logger logger= Logger.getLogger(TestPenalizareDataServiceEJBArq.class.getName());
	@EJB
	private static PenalizareService service ;
	
	@Deployment

    public static Archive<?> createDeployment(){

           return ShrinkWrap.create(WebArchive.class,"msd-test.war")



                        .addPackage(Penalizare.class.getPackage())

                        .addPackage(EntityRepository.class.getPackage())

                        .addPackage(PenalizareService.class.getPackage())

                        .addPackage(PenalizareServiceEJB.class.getPackage())

                        .addAsResource("META-INF/persistence.xml")

                        .addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");

                       

    }
	
	@Test
	public void test1_getMessage() {
		logger.info("DEBUG:Junit TESTING: getMessage ...");
		String response=service.getMessage();
		assertNotNull("Data Service failed!",response);
		logger.info("DEBUG EJB Response ..."+response);
		
	}
	
	@Test
	public void test4_getPenalizare() {
		logger.info("DEBUG:Junit TESTING: TestGetPenalizare...");
		Collection<Penalizare> penalizare=service.getPenalizare();
	
		assertTrue("fail to read penalizare!",penalizare.size()>0);		
	}
	
	/*this.idPenalizare = idPenalizare;
	this.procentPenalizare = procentPenalizare;
	this.status = status;*/
	@Test
	public void test3_addPenalizare() {
		logger.info("DEBUG:Junit TESTING: TestAddPenalizare...");
		Integer penalizareToAdd=3;
		for(int i=1;i<=penalizareToAdd;i++)
		{
			service.addPenalizare(new Penalizare(null,0.3f,StatusPenalizare.neachitat));
		}
		Collection<Penalizare> penalizare=service.getPenalizare();
	
		assertTrue("fail to add penalizare!",penalizare.size()==penalizareToAdd);		
	}
	
	@Test
	public void test2_DeletePenalizare() {
		logger.info("DEBUG:Junit TESTING: TestDeletePenalizare...");
		Collection<Penalizare> penalizare=service.getPenalizare();
		
		for(Penalizare p:penalizare)
		service.removePenalizare(p);
		Collection<Penalizare> penalizareAfterDelete=service.getPenalizare();
		
	
		assertTrue("fail to read penalizare!",penalizareAfterDelete.size()==0);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
