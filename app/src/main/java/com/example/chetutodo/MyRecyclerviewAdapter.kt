package com.example.chetutodo

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.chetutodo.databinding.LayoutCustomNotesViewBinding

class MyRecyclerviewAdapter(val listNotes: List<Notes>,private val onSetOnClickListner: OnSetOnClickListner) :
    RecyclerView.Adapter<MyRecyclerviewAdapter.notesViewHolder>() {
    class notesViewHolder( var layoutCustomNotesViewBinding: LayoutCustomNotesViewBinding) :
        RecyclerView.ViewHolder(layoutCustomNotesViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        val layoutCustomNotesViewBinding: LayoutCustomNotesViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.layout_custom_notes_view, parent, false
        )
        return notesViewHolder(layoutCustomNotesViewBinding)

    }

    override fun getItemCount(): Int {
        return listNotes.size

    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        val list = listNotes[position]

        holder.layoutCustomNotesViewBinding.tvTitle.text = list.Title
        holder.layoutCustomNotesViewBinding.tvDiscription.text= list.Driscription

        holder.itemView.setOnClickListener{
            onSetOnClickListner.onItemClick(position,list)
        }




    }
}