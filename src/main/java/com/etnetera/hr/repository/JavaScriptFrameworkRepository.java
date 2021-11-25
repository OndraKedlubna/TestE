package com.etnetera.hr.repository;

import org.springframework.data.repository.CrudRepository;

import com.etnetera.hr.data.JavaScriptFramework;
import org.springframework.data.repository.query.Param;

/**
 * Spring data repository interface used for accessing the data in database.
 *
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {

    JavaScriptFramework findByName(@Param("name") String name);

    void deleteByName(@Param("name") String name);

}
