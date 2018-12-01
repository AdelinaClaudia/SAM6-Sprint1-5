package org.app.service.ejb;
import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.*;
@Remote
public interface ClientService {
	//create or update client 
	Client addClient(Client clientToAdd);
	
	//delete
	String removeClient(Client clientToDelete);
	
	//read client already existent
	Client getClientByIdClient(Integer idClient);
	Collection<Client> getClient();
	
	//custom READ: by NumePrenume -CLIENT 
	Client getClientByNumePrenume(String numePrenume);
	
	//MESSAGE
	String getMessage();
	
	//OTHER SPRINT3PLUS
	Collection<Client>toCollection();
	Client getByIdClient(Integer id);
}
