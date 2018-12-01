package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;


@Remote
public interface ClientContractPenalizariDataService extends EntityRepository<Client>{

//entity agregat
	Client createNewClient(Integer idClient);
	
	Contract getContractByNumarContract(Integer numarContract);
	
	String getMessage();
	
}
