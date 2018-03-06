package com.example.learning.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning.model.Note;
import com.example.learning.repository.NoteRepository;
import com.example.learning.service.NoteService;

@RestController
@RequestMapping("/api")
public class NoteController {
	@Autowired	NoteService noteService;
	
	@GetMapping(value="/notes", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Note>> getAllNotes(){
		ThreadLocal<String> local = new ThreadLocal<>();
		String txId = UUID.randomUUID().toString();
		System.out.println(" txId -> "+txId);
		local.set(txId);
		List<Note> notes = noteService.findAll();
		return new ResponseEntity<List<Note>>(notes, HttpStatus.OK);
	}
	
	@PostMapping("/notes")
	public ResponseEntity<Note> createNote(@Valid @RequestBody Note note){
		Note savedNote = noteService.save(note);
		return new ResponseEntity<Note>(savedNote, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/notes/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Note> getNote(@PathVariable(value="id") Long noteId){
		Optional<Note> note = noteService.findById(noteId);
		if(!note.isPresent()){
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(note.get());
		}
		
	}
	
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId, 
	                                       @Valid @RequestBody Note noteDetails) {
	     Optional<Note> note = noteService.findById(noteId);
	    if(!note.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }
	    Note nt = note.get();
	    nt.setTitle(noteDetails.getTitle());
	    nt.setContent(noteDetails.getContent());

	    Note updatedNote = noteService.save(nt);
	    return ResponseEntity.ok(updatedNote);
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Optional<Note> note = noteService.findById(noteId);
	    if(!note.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    noteService.delete(note.get());
	    return ResponseEntity.ok().build();
	}
	
}
