package com.sofiyashahi.noteit

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sofiyashahi.noteit.databinding.ActivityAddEditNoteBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding
    private lateinit var viewModel: NotesViewModel
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        val toolbar = binding.toolbar
        toolbar.navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")) {
            toolbar.title = getString(R.string.edit_note)
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteId = intent.getIntExtra("noteId", -1)
            binding.titleEdt.setText(noteTitle)
            binding.descripEdt.setText(noteDesc)

        } else {
            toolbar.title = getString(R.string.add_note)
        }

        binding.saveBtn.setOnClickListener {
            val noteTitle = binding.titleEdt.text.toString()
            val noteDescription = binding.descripEdt.text.toString()

            if(noteType.equals("Edit")) {
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd/MM/yyyy - HH:mm")
                    val currentDate = sdf.format(Date())
                    val updatedNote = Notes(noteTitle, noteDescription, currentDate, noteId)
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated...", Toast.LENGTH_LONG).show()
                }
            } else {
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd/MM/yyyy - HH:mm")
                    val currentDate = sdf.format(Date())
                    viewModel.addNote(Notes(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}