package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author Adelina
 *
 */
@Entity
@Table(name="Contract")
@Inheritance(strategy = InheritanceType.JOINED)
public class Contract implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer numarContract;
	@Temporal(TemporalType.DATE)
	private Date dataincheieriiContract;
	@Temporal(TemporalType.DATE)
	private Date dataincetareContract;
	private String detaliiContract;
	@OneToMany
	private List<ActAditional> acteaditionale = new ArrayList<ActAditional>();
	public Contract(Integer numarContract, Date dataincheieriiContract, Date dataincetareContract,
			String detaliiContract, Float sumacontract, Client client, Float sumapenalizare) {
		super();
		this.numarContract = numarContract;
		this.dataincheieriiContract = dataincheieriiContract;
		this.dataincetareContract = dataincetareContract;
		this.detaliiContract = detaliiContract;
		this.sumacontract = sumacontract;
		this.client = client;
		Sumapenalizare = sumapenalizare;
	}
	private Float sumacontract;
    @ManyToOne
	private Client client;
    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
	private Angajat angajat;
	@OneToMany 
	private List<Penalizare>penalizari=new ArrayList <Penalizare>();
	private Float Sumapenalizare;
	public Float getSumacontract() {
		return sumacontract;
	}
	public void setSumacontract(Float sumacontract) {
		this.sumacontract = sumacontract;
	}
	
	public Integer getNumarContract() {
		return numarContract;
	}
	public void setNumarContract(Integer numarContract) {
		this.numarContract = numarContract;
	}
	//metoda 2
	public void adaugaActaditional(ActAditional act) {
	if(!this.acteaditionale.contains(act))
	this.acteaditionale.add(act);
	}
	
	public Date getDataincheieriiContract() {
		return dataincheieriiContract;
	}
	public void setDataincheieriiContract(Date dataincheieriiContract) {
		this.dataincheieriiContract = dataincheieriiContract;
	}
	public Date getDataincetareContract() {
		return dataincetareContract;
	}
	public void setDataincetareContract(Date dataincetareContract) {
		this.dataincetareContract = dataincetareContract;
	}
	public String getDetaliiContract() {
		return detaliiContract;
	}
	public void setDetaliiContract(String detaliiContract) {
		this.detaliiContract = detaliiContract;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Angajat getAngajat() {
		return angajat;
	}
	public void setAngajat(Angajat angajat) {
		this.angajat = angajat;
	}
	//metoda 3 calculul penalizarii pentru un contract 
	private Float calculPenalizare()
	{Float valpenalizare=null;
	 for (Penalizare p:this.penalizari) {
		 valpenalizare=p.getProcentPenalizare()* this.getSumacontract();
	 }
	
	return valpenalizare;
	}
	

	public List<ActAditional> getActeaditionale() {
		return acteaditionale;
	}
	public void setActeaditionale(List<ActAditional> acteaditionale) {
		this.acteaditionale = acteaditionale;
	}
	//daca sumapenalizare sa se calculeze penalizarea ca produs intre procent penalizare si valoare contract
	public  Float getSumapenalizare() {
		if(this.Sumapenalizare==null)
			this.Sumapenalizare=calculPenalizare();
		return Sumapenalizare;
	}
	public void setSumapenalizare(Float sumapenalizare) {
		Sumapenalizare = sumapenalizare;
	}
	
	
	public List<Penalizare> getPenalizari() {
		return penalizari;
	}
	public void setPenalizari(List<Penalizare> penalizari) {
		this.penalizari = penalizari;
	}
	public Contract() {
		super();
	}
	public Contract(Integer numarContract, Date dataincheieriiContract, Date dataincetareContract,
			String detaliiContract, Float sumacontract, Float sumapenalizare) {
		super();
		this.numarContract = numarContract;
		this.dataincheieriiContract = dataincheieriiContract;
		this.dataincetareContract = dataincetareContract;
		this.detaliiContract = detaliiContract;
		this.sumacontract = sumacontract;
		Sumapenalizare = sumapenalizare;
	}
	public void setSumapenalizare(String string) {
		// TODO Auto-generated method stub
		
	}
	

}
