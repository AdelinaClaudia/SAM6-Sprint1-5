package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.*;

@Remote
public interface AngajatService {
	//create ANGAJAT
	Angajat addAngajat(Angajat angajatToAdd);
	
	//delete
	String removeAngajat(Angajat angajatToDelete);
	
	//read
	Angajat getAngajatByMarcaAngajat(Integer marcaAngajat);
	Collection<Angajat> getAngajati();
	
	//message
	String getMessage();
	
	//other
	Collection<Angajat>toCollection();
	Angajat getByMarcaAngajat(Integer marca);
}
