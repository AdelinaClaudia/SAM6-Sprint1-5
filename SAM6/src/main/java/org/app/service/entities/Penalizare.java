package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


	@Entity
	public class Penalizare implements Serializable{
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@NotNull
		private Integer idPenalizare;
		private Float procentPenalizare;
		/*@Enumerated*/
		private StatusPenalizare status;

		public Integer getIdPenalizare() {
			return idPenalizare;
		}
		
		

		public void setIdPenalizare(Integer idPenalizare) {
			this.idPenalizare = idPenalizare;
		}

		public Float getProcentPenalizare() {
			return procentPenalizare;
		}

		public void setProcentPenalizare(Float procentPenalizare) {
			this.procentPenalizare = procentPenalizare;
		}

		public StatusPenalizare getStatus() {
			return status;
		}

		public void setStatus(StatusPenalizare status) {
			this.status = status;
		}

		public Penalizare() {
			super();
		}

		public Penalizare(Integer idPenalizare, Float procentPenalizare, StatusPenalizare status) {
			super();
			this.idPenalizare = idPenalizare;
			this.procentPenalizare = procentPenalizare;
			this.status = status;
		}
		/*public Penalizare(Integer idPenalizare, Float procentPenalizare) {
			super();
			this.idPenalizare = idPenalizare;
			this.procentPenalizare = procentPenalizare;
		}*/
		
	}



