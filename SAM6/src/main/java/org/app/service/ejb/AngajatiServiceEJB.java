package org.app.service.ejb;
import org.app.service.entities.*;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.patterns.EntityRepositoryBase;

@Stateless @LocalBean 
public class AngajatiServiceEJB extends EntityRepositoryBase<Angajat> implements AngajatService {
	private static Logger logger = Logger.getLogger(AngajatiServiceEJB.class.getName());
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public AngajatiServiceEJB() {
		
	}
	@PostConstruct 
	public void init() {
		logger.info("POSTCONSTRUCT-INIT : "+ this.em);
	}
	@Override
	 public Angajat addAngajat(Angajat angajatToAdd) {
		em.persist(angajatToAdd);
		em.flush();
		em.refresh(angajatToAdd);
		return angajatToAdd;
	}
	@Override
	public Angajat getByMarcaAngajat(Integer marcaAngajat){

		return em.find(Angajat.class, marcaAngajat);
	}
	
	public Collection<Angajat>getAngajati(){

        List<Angajat> angajati=em.createQuery("Select a From Angajat a", Angajat.class).getResultList();

        return angajati;

 }
	

	//custome read 
	public Angajat getAngajatByMarcaAngajat(Integer marca) {
		return em.createQuery("SELECT a from Angajat a Where a.marcaAngajat=:marca", Angajat.class).setParameter("marcaAngajat", marca).getSingleResult();
	}
	
	public String removeAngajat(Angajat angajatToDelete) {
		angajatToDelete=em.merge(angajatToDelete);
		em.remove(angajatToDelete);
		em.flush();
		return "true";
	}
	
	public String getMessage() {
		return "AngajatiServiceEJB is on ... ";
	}
	
	
	
	
	
	
	
	
	
}
