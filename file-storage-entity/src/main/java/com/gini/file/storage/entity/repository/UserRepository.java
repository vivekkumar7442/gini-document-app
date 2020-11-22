/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gini.file.storage.entity.UserDetails;

/**
 * @author vivek
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>{

}
