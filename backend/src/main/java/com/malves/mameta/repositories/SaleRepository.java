package com.malves.mameta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malves.mameta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
