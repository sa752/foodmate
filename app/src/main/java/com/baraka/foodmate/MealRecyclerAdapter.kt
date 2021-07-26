package com.baraka.foodmate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baraka.foodmate.modals.MealItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MealRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private  var items:  List<MealItem> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return   MealViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.layout_food_list_item, parent, false)
                )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MealViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
       return   items.size
    }


    //build a custom view Holder class that describes
    //what the views are going to be looking like in your recycler view

    class  MealViewHolder constructor(
        itemView:View
    ): RecyclerView.ViewHolder(itemView){

        private val mealImage: ImageView = itemView.findViewById(R.id.meal_image)
        private val mealTitle:TextView = itemView.findViewById(R.id.meal_title)
        private val  mealPrice:TextView = itemView.findViewById(R.id.meal_price)


        fun bind (mealItem: MealItem){
            mealTitle.text = mealItem.title
            mealPrice.text = "\$\$ ${mealItem.price.toString()}"

            //create a req options object
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_place_holder)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(mealItem.image)
                .into(mealImage)

        }
    }

    //submit List
    fun submitList(mealList: List<MealItem>){
        items = mealList
    }

}