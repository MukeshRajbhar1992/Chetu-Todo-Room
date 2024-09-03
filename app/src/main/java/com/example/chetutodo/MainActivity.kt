package com.example.chetutodo

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chetutodo.databinding.ActivityMainBinding
import com.example.chetutodo.databinding.LayoutCustomDialogViewBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var factory: NotsFactory
    private lateinit var viewModel: NotsViewModel
    private lateinit var adapter: MyRecyclerviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val nodesDao = NotesDataBase.getInstance(this).nodesDao
        factory = NotsFactory(NotsRepository(nodesDao))
        viewModel= ViewModelProvider(this,factory)[NotsViewModel::class.java]

        binding.recycerview.layoutManager = LinearLayoutManager(this)

        viewModel.notes.observe(this, Observer {
            adapter = MyRecyclerviewAdapter(it,object: OnSetOnClickListner{
                override fun onItemClick(position: Int, notes: Notes) {

                    val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                    intent.putExtra("NOTES_DATA", Gson().toJson(notes))
                    startActivity(intent)
                }
            })
            binding.recycerview.adapter = adapter
        })

        binding.fab.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
       val dialogBinding:LayoutCustomDialogViewBinding = DataBindingUtil.inflate(
           LayoutInflater.from(this),R.layout.layout_custom_dialog_view,null,false)
        val dialog = Dialog(this)
        dialog.setContentView(dialogBinding.root)
        val manager = WindowManager.LayoutParams()
        manager.width = WindowManager.LayoutParams.MATCH_PARENT
        manager.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = manager

        val editTitle = dialog.findViewById<EditText>(R.id.edit_Title)
        binding
        val editDiscription = dialog.findViewById<EditText>(R.id.edit_discription)
        val image_view = dialog.findViewById<ImageView>(R.id.image_View)
        val btnSave = dialog.findViewById<Button>(R.id.btn_save)

        btnSave.setOnClickListener {
            dialog.dismiss()
            val notes = Notes(0,editTitle.text.toString(),editDiscription.text.toString())
            viewModel.saveData(notes)
        }
        image_view.setOnClickListener {
            val intent = Intent(    this@MainActivity,MainActivity::class.java)
            startActivity(intent)

        }
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.deleteall_option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all_item->{
                viewModel.deleteAllUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}