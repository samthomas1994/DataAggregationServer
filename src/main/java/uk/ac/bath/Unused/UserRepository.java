package uk.ac.bath.Unused;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sam on 03/02/2016.
 */
public interface UserRepository<UserDetails, Long extends Serializable> extends CrudRepository<UserDetails, Long> {

//    @Query("SELECT t.title FROM Todo t where t.id = :id")
//    String findTitleById(@Param("id") Long id);

}
