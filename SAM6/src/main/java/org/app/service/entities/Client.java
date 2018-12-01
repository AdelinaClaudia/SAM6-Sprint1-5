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
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer idClient;
	private String numePrenumeClient;
	private Integer cnpClient;
	@Temporal(TemporalType.DATE)
	private Date dataNasterii;
	private String bancaClient;
	private String ibanClient;
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Contract> contracte = new ArrayList<Contract>();

	

	public List<Contract> getContracte() {
		return contracte;
	}

	public void setContracte(List<Contract> contracte) {
		this.contracte = contracte;
	}

	
	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	
	public String getNumePrenumeClient() {
		return numePrenumeClient;
	}

	public void setNumePrenumeClient(String numePrenumeClient) {
		this.numePrenumeClient = numePrenumeClient;
	}

	public Integer getCnpClient() {
		return cnpClient;
	}

	public void setCnpClient(Integer cnpClient) {
		this.cnpClient = cnpClient;
	}

	public Date getDataNasterii() {
		return dataNasterii;
	}

	public void setDataNasterii(Date dataNasterii) {
		this.dataNasterii = dataNasterii;
	}

	public String getBancaClient() {
		return bancaClient;
	}

	public void setBancaClient(String bancaClient) {
		this.bancaClient = bancaClient;
	}

	public String getIbanClient() {
		return ibanClient;
	}

	public void setIbanClient(String ibanClient) {
		this.ibanClient = ibanClient;
	}

	// Constructori
	public Client() {
		super();
	}

	


	public Client(Integer idClient, String numePrenumeClient, Integer cnpClient, Date dataNasterii, String bancaClient,
			String ibanClient) {
		super();
		this.idClient = idClient;
		this.numePrenumeClient = numePrenumeClient;
		this.cnpClient = cnpClient;
		this.dataNasterii = dataNasterii;
		this.bancaClient = bancaClient;
		this.ibanClient = ibanClient;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void setContracte(String string) {
		// TODO Auto-generated method stub
		
	}

	
}
