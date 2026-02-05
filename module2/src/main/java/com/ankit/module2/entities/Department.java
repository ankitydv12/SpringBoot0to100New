package com.ankit.module2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Department {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;
   private String tittle;
   @JsonProperty("Active")
   @JsonIgnore
   private boolean Active;
   private LocalDate createdAt;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTittle() {
      return tittle;
   }

   public void setTittle(String tittle) {
      this.tittle = tittle;
   }

   public boolean isActive() {
      return Active;
   }

   public void setActive(boolean active) {
      Active = active;
   }

   public LocalDate getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDate createdAt) {
      this.createdAt = createdAt;
   }
}
