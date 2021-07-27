package com.baraka.foodmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*


class Home : Fragment() {

    private lateinit var mealAdapater: MealRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        addDataSet()
    }

    private fun initRecyclerView(){
        home_page_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(10)
            addItemDecoration(topSpacingItemDecoration)
            mealAdapater = MealRecyclerAdapter()
            adapter = mealAdapater
        }
    }

     private fun addDataSet (){
         var data  = MealDataSource.createDataSet()
         mealAdapater.submitList(data)
     }

}