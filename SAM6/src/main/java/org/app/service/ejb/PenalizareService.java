package org.app.service.ejb;
import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.*;

@Remote
public interface PenalizareService {
	//create
		Penalizare addPenalizare(Penalizare penalizareToAdd);
	   
	//delete
	String removePenalizare(Penalizare penalizareToDelete);
	
	//read
	Penalizare getPenalizareByIdPenalizare(Integer idPenalizare);
	Collection<Penalizare> getPenalizare();
	//message
	String getMessage();
	//others
	Collection<Penalizare>toCollection();
	Penalizare getByIdPenalizare(Integer idP);
}
