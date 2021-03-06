package com.example.store.storeApi.domain.service;

import com.example.store.storeApi.persistence.entity.*;
import com.example.store.storeApi.persistence.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;


    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void saveNoteUser(int noteId, int userId) {
        noteRepository.saveNoteUser(noteId, userId);
    }

    public List<Note> findByUserId(int userId) {
        return noteRepository.findByUserId(userId);
    }

    public int getNotesId(){
        return noteRepository.getNotesId();
    }

}
