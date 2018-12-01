package org.app.service.entities;

import java.util.Date;

public class Rulare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//--populare Angajati
		
		Angajat BarbuIon      = new Angajat(1,"BarbuIon",1972,new Date(2000/12/12),"BRD","RO00AAAA1B31007593840000");
		Angajat PopescuFlorin = new Angajat(2,"PopescuFlorin",1914,new Date(1990/12/12),"BCR","RO11AAAA1B31007593840000");
		Angajat PlescaLivia   = new Angajat(3,"PLesca Livia",1992,new Date(1997/01/12),"BNR","RO11AAAA2B31007593840000");
		
		
//--populare penalizare 
		Penalizare despasirescadenta= new Penalizare (1, 0.3f,StatusPenalizare.neachitat);
		Penalizare nerespectareclauze = new Penalizare (2,0.08f,StatusPenalizare.achitat);
		Penalizare rmfd = new Penalizare (2,0.5f,StatusPenalizare.neachitat);
		
		
	}
	
	

}
