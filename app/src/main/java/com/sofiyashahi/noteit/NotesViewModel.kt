package com.sofiyashahi.noteit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Notes>>
    val repo: NotesRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repo = NotesRepository(dao)
        allNotes = repo.allNotes
    }

    fun deleteNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }

    fun updateNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(note)
    }

    fun addNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }

}