package com.example.recycleviewballlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewballlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
    private lateinit var binding: ActivityMainBinding
    private val ballList = ArrayList<Balls>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
        ViewBinding writing

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        **/

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        initBallList()  //set up the data source
        val layoutManager = LinearLayoutManager(this)
        //val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recycleView.layoutManager = layoutManager
        val adapter = BallAdapter(ballList)  //customized your own adapter
        binding.recycleView.adapter = adapter
        binding.recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        //binding.recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))


        //configure the searchView
        binding.searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
    }

    private fun initBallList() {
        repeat(6) {
            ballList.add(Balls("Baseball",R.drawable.baseball))
            ballList.add(Balls("Basketball",R.drawable.basketball))
            ballList.add(Balls("Football",R.drawable.football))
            ballList.add(Balls("Other",R.drawable.other))
        }
    }
}
