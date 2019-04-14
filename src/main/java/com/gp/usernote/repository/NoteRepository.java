package com.gp.usernote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gp.usernote.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("SELECT note FROM Note note INNER JOIN FETCH note.user As user WHERE user.email = :email")
	List<Note> getUserNotes(@Param("email") String email);

	@Query("SELECT note FROM Note note INNER JOIN FETCH note.user As user WHERE note.title = :title and user.email = :email")
	Note getUserNote(@Param("title") String title, @Param("email") String email);
}
