package spn.getlistkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import spn.getlistkotlin.adapter.ListAdapter
import spn.getlistkotlin.databinding.ActivityMainBinding
import spn.getlistkotlin.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: ListViewModel
    private lateinit var listadapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listadapter = ListAdapter(applicationContext)

        binding.listRV.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = listadapter
        }

        viewmodel = ViewModelProvider(this)[ListViewModel::class.java]
        viewmodel.getList()
        viewmodel.observelistLiveData().observe(this, Observer { list ->
            listadapter.setList(list)
        })

        binding.searchEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                viewmodel.filters(s.toString())?.let {
                    listadapter.setList(it.value!!)
                }
            }
        })
    }
}