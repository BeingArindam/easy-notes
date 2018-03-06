/**
 * 
 */
package com.example.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learning.model.Note;

/**
 * @author AMondal
 *
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
