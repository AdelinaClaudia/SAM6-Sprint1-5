package org.app.service.ejb;

import java.util.Collection;


import javax.ejb.Remote;


import org.app.service.entities.*;
@Remote
public interface ContractService {
	
	//create or update
	Contract addContract(Contract contractToAdd);
	
	//delete
	String removeContract(Contract contractToDelete);
	
	//read
	Contract getContractByNumarContract(Integer numarContract);
	Collection<Contract> getContract();
	
	//message
	String getMessage();
	
	//other
	Collection<Contract>toCollection();
	Contract getByNumarContract(Integer numar);
	/*Contract getById(Integer id);*/
}
