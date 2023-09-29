package com.sofiyashahi.noteit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sofiyashahi.noteit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteOnClickInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        recyclerView = binding.rvNotes
        recyclerView.layoutManager = LinearLayoutManager(this)
        val noteAdapter = NotesAdapter(this, this)
        recyclerView.adapter = noteAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteAdapter.updateList(it)
            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddEditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDeleteIconClick(note: Notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.title} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(notes: Notes) {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", notes.title)
        intent.putExtra("noteDescription", notes.description)
        intent.putExtra("noteId", notes.id)
        startActivity(intent)
    }
}