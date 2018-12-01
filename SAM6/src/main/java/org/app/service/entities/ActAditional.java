package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ActAditional")
public class ActAditional extends Contract {
	
	private String detaliiActAditional;
	@ManyToOne
	private Contract contract;

	
	public String getDetaliiActAditional() {
		return detaliiActAditional;
	}

	public void setDetaliiActAditional(String detaliiActAditional) {
		this.detaliiActAditional = detaliiActAditional;
	}

	public ActAditional() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActAditional(Integer numarContract, Date dataincheieriiContract, Date dataincetareContract,
			String detaliiContract, Float procentPenalizare, Client client, Angajat angajat, Penalizare penalizare) {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
