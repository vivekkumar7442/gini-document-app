/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gini.file.storage.entity.Documents;

/**
 * @author vivek
 *
 */
@Repository
public interface DocumentRepository extends JpaRepository<Documents, Integer> {

	Documents findByIdAndUserDetails_id(Integer documentId, Integer userId);

}
