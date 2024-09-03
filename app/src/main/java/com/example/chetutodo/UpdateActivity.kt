package com.example.chetutodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chetutodo.databinding.ActivityUpdateBinding
import com.google.gson.Gson

class UpdateActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var factory: NotsFactory
    private lateinit var viewModel: NotsViewModel
    lateinit var notes: Notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)

        val nodesDao = NotesDataBase.getInstance(this).nodesDao
        factory = NotsFactory(NotsRepository(nodesDao))
        viewModel = ViewModelProvider(this, factory)[NotsViewModel::class.java]

        notes = Gson().fromJson(intent.getStringExtra("NOTES_DATA"), Notes::class.java)
        binding.editTitleUpdate.setText(notes.Title)
        binding.editDiscriptionUpdate.setText(notes.Driscription)

        binding.btnUpdate.setOnClickListener (this)



    }

    override fun onClick(p0: View?) {
       viewModel.updateData(Notes(notes.Id,binding.editTitleUpdate.text.toString(),binding.editDiscriptionUpdate.text.toString()))
        finish()


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.editable -> {

                binding.editTitleUpdate.isFocusableInTouchMode=true
                binding.editDiscriptionUpdate.isFocusableInTouchMode=true
                binding.btnUpdate.visibility = View.VISIBLE


            }
            R.id.delete ->{
                viewModel.deleteUser(notes)
                finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }

}