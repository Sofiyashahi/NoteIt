package com.sofiyashahi.noteit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sofiyashahi.noteit.databinding.NoteItemBinding

class NotesAdapter(val context: Context, val noteClickInterface: NoteOnClickInterface)
    : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    private val allNotes = ArrayList<Notes>()
    inner class NoteViewHolder(binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root){
        val noteTitle = binding.noteTitle
        val timeTxt = binding.timestamp
        val deleteNote = binding.noteDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteTitle.text = allNotes[position].title
        holder.timeTxt.text = allNotes[position].timestamp
        holder.deleteNote.setOnClickListener {
            noteClickInterface.onDeleteIconClick(allNotes[position])
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    fun updateList(newList: List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }


}

interface NoteOnClickInterface {
    fun onDeleteIconClick(note: Notes)
    fun onNoteClick(notes: Notes)
}