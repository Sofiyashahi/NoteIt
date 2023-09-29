package com.sofiyashahi.noteit

import androidx.lifecycle.LiveData

class NotesRepository(private val dao: NotesDao) {

    val allNotes: LiveData<List<Notes>> = dao.getAllNotes()

    suspend fun insert(note: Notes) {
        dao.insert(note)
    }

    suspend fun update(note: Notes) {
        dao.update(note)
    }

    suspend fun delete(note: Notes) {
        dao.delete(note)
    }
}