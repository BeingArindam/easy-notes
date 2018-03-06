package com.example.learning.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning.model.Note;
import com.example.learning.repository.NoteRepository;

@Service
public class NoteService {
	@Autowired
	NoteRepository noteRepository;

	public List<Note> findAll() {
		return noteRepository.findAll();
	}

	public Note save(@Valid Note note) {
		return noteRepository.save(note);
	}

	public Optional<Note> findById(Long noteId) {
		return noteRepository.findById(noteId);
	}

	public void delete(Note note) {
		noteRepository.delete(note);
	}
}
