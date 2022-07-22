package com.example.store.storeApi.persistence.repository;

import com.example.store.storeApi.persistence.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query(value = "SELECT\n" +
            "  notes.*\n" +
            "FROM note_user\n" +
            "  INNER JOIN notes\n" +
            "    ON note_user.note_id = notes.id\n" +
            "  INNER JOIN users\n" +
            "    ON note_user.user_id = users.id\n" +
            "WHERE note_user.user_id = :userId", nativeQuery = true)
    List<Note> findByUserId(@Param("userId") int userId);


    Note save(Note note);


    @Query(value = "INSERT INTO notes.note_user (note_id, user_id) VALUES ('?', '?')", nativeQuery = true)
    void saveNoteUser(int noteId, int userId);

    @Query(value = "select notes.notes.id from notes.notes ORDER BY notes.id DESC LIMIT 1", nativeQuery = true)
    int getNotesId();
}
