package es.upm.etsisi.persistence;

import org.springframework.stereotype.Repository;

import com.microsoft.azure.spring.data.documentdb.repository.DocumentDbRepository;

import es.upm.etsisi.entities.omjson.ObservationCollecionTraza;

/**
 * @author Guillermo, Yan Liu
 * @version 1.0
 */
//@Sofia2Repository
public interface DAOObservationCollections 
extends DocumentDbRepository<ObservationCollecionTraza, String> {}
