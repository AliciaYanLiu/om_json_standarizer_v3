package es.upm.etsisi.persistence;

import org.springframework.stereotype.Repository;

import com.microsoft.azure.spring.data.documentdb.repository.DocumentDbRepository;

import es.upm.etsisi.entities.mota.MotaMeasure;
import es.upm.etsisi.entities.mota.MotaMeasureTraza;

/**
 * @author Guillermo, Yan Liu
 * @version 1.0
 */
//@Sofia2Repository
public interface DAOMotaMeasures 
extends DocumentDbRepository<MotaMeasureTraza, String> {
	//@Sofia2Query
	public void findByMotaMeasure(MotaMeasure mota);
}
