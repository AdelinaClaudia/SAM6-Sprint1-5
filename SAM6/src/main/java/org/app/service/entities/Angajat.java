package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class Angajat implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer marcaAngajat;
	private String numePrenumeAngajat;
	private Integer cnpAngajat;
	
	@Temporal(TemporalType.DATE)
	
	private Date dataAngajare;
	private String bancaAngajat;
	private String ibanAngajat;
	
	@OneToMany(mappedBy="angajat", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Contract> contract = new ArrayList<Contract>();
	
	//--get set
	
	public Integer getMarcaAngajat() {
		return marcaAngajat;
	}
	//metoda1
	public void adaugacontract(Contract c) {
		if(!this.contract.contains(c))
		this.contract.add(c);
	}
	public void setMarcaAngajat(Integer marcaAngajat) {
		this.marcaAngajat = marcaAngajat;
	}
	
	public String getNumePrenumeAngajat() {
		return numePrenumeAngajat;
	}
	public void setNumePrenumeAngajat(String numePrenumeAngajat) {
		this.numePrenumeAngajat = numePrenumeAngajat;
	}

	public Integer getCnpAngajat() {
		return cnpAngajat;
	}
	public void setCnpAngajat(Integer cnpAngajat) {
		this.cnpAngajat = cnpAngajat;
	}
	
	public Date getDataAngajare() {
		return dataAngajare;
	}
	public void setDataAngajare(Date dataAngajare) {
		this.dataAngajare = dataAngajare;
	}
	
	public String getBancaAngajat() {
		return bancaAngajat;
	}
	public void setBancaAngajat(String bancaAngajat) {
		this.bancaAngajat = bancaAngajat;
	}

	public String getIbanAngajat() {
		return ibanAngajat;
	}
	public void setIbanAngajat(String ibanAngajat) {
		this.ibanAngajat = ibanAngajat;
	}
	public List<Contract> getContract() {
		return contract;
	}

	public void setContract(List<Contract> contracte) {
		this.contract.addAll(contracte);
	}
	
	public void setContract(Contract contract) {
		this.contract.add(contract);
	}

	
	
	
		public Angajat() {
		super();
	}
		public Angajat(Integer marcaAngajat, String numePrenumeAngajat, Integer cnpAngajat, Date dataAngajare,
				String bancaAngajat, String ibanAngajat) {
			super();
			this.marcaAngajat = marcaAngajat;
			this.numePrenumeAngajat = numePrenumeAngajat;
			this.cnpAngajat = cnpAngajat;
			this.dataAngajare = dataAngajare;
			this.bancaAngajat = bancaAngajat;
			this.ibanAngajat = ibanAngajat;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((bancaAngajat == null) ? 0 : bancaAngajat.hashCode());
			result = prime * result + ((cnpAngajat == null) ? 0 : cnpAngajat.hashCode());
			result = prime * result + ((contract == null) ? 0 : contract.hashCode());
			result = prime * result + ((dataAngajare == null) ? 0 : dataAngajare.hashCode());
			result = prime * result + ((ibanAngajat == null) ? 0 : ibanAngajat.hashCode());
			result = prime * result + ((marcaAngajat == null) ? 0 : marcaAngajat.hashCode());
			result = prime * result + ((numePrenumeAngajat == null) ? 0 : numePrenumeAngajat.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Angajat other = (Angajat) obj;
			if (bancaAngajat == null) {
				if (other.bancaAngajat != null)
					return false;
			} else if (!bancaAngajat.equals(other.bancaAngajat))
				return false;
			if (cnpAngajat == null) {
				if (other.cnpAngajat != null)
					return false;
			} else if (!cnpAngajat.equals(other.cnpAngajat))
				return false;
			if (contract == null) {
				if (other.contract != null)
					return false;
			} else if (!contract.equals(other.contract))
				return false;
			if (dataAngajare == null) {
				if (other.dataAngajare != null)
					return false;
			} else if (!dataAngajare.equals(other.dataAngajare))
				return false;
			if (ibanAngajat == null) {
				if (other.ibanAngajat != null)
					return false;
			} else if (!ibanAngajat.equals(other.ibanAngajat))
				return false;
			if (marcaAngajat == null) {
				if (other.marcaAngajat != null)
					return false;
			} else if (!marcaAngajat.equals(other.marcaAngajat))
				return false;
			if (numePrenumeAngajat == null) {
				if (other.numePrenumeAngajat != null)
					return false;
			} else if (!numePrenumeAngajat.equals(other.numePrenumeAngajat))
				return false;
			return true;
		}
	

	
		
		
	
	
}

