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
public class PenalizareServiceEJB extends EntityRepositoryBase<Penalizare> implements PenalizareService{
	private static Logger logger = Logger.getLogger(PenalizareServiceEJB.class.getName());
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	public PenalizareServiceEJB() {
		
	}
	@PostConstruct 
	public void init() {
		logger.info("POSTCONSTRUCT-INIT : "+ this.em);
	}
	@Override
	 public Penalizare addPenalizare(Penalizare penalizareToAdd) {
		em.persist(penalizareToAdd);
		em.flush();
		em.refresh(penalizareToAdd);
		return penalizareToAdd;
	}
	@Override
	public Penalizare getByIdPenalizare (Integer idPenalizare){

		return em.find(Penalizare.class, idPenalizare);
	}
	public Collection<Penalizare>getPenalizare(){
		List<Penalizare> penalizare=em.createQuery("Select p From Penalizare p", Penalizare.class).getResultList();
		return penalizare;
	}
	//custome read 
	public Penalizare getPenalizareByIdPenalizare(Integer idP) {
		return em.createQuery("SELECT p from Penalizare p Where p.idPenalizare=:idP", Penalizare.class).setParameter("idPenalizare", idP).getSingleResult();
	}
	
	public String removePenalizare(Penalizare penalizareToDelete) {
		penalizareToDelete=em.merge(penalizareToDelete);
		em.remove(penalizareToDelete);
		em.flush();
		return "true";
	}
	
	public String getMessage() {
		return "PenalizareServiceEJB is on ... ";
	}
	
}
