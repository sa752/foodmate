package com.baraka.foodmate

import com.baraka.foodmate.models.MealItem

class MealDataSource {
    companion object {

        fun createDataSet():ArrayList<MealItem>{
            val list = ArrayList<MealItem>()

            list.add(
                MealItem(
                    "Classic Burger",
                    "Our classic burger is made with 100% pure angus beef, served with lettuce, tomatoes, onions, pickles, and cheese of your choice. " +
                            "\n" +
                            "Veggie burger available upon request. Served with French fries, fresh fruit, or a side salad",
                    "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg",
                    9.99
                )
            )
            list.add(
                MealItem(
                    "Quinoa Salmon Salad",
                    "Our quinoa salad is served with quinoa, tomatoes, cucumber, scallions, and smoked salmon.\r" +
                            "\n" + "\r" +
                            "  Served with your choice of dressing",
                    "https://cdn.pixabay.com/photo/2016/11/18/13/48/food-1834645_960_720.jpg",
                    8.67
                )
            )
            list.add(
                MealItem(
                    "Tomato Bruschetta Tortellini",
                    "This classic cheese tortellini is cooked in a sundried tomato sauce. Served with bruschetta topped with a tomato and basil marinar",
                    "https://cdn.pixabay.com/photo/2018/04/26/16/56/bruschetta-3352412_960_720.jpg",
                    8.5
                )
            )
            list.add(
                MealItem(
                    "Handcrafted Pizza",
                    "Our thin crust pizzas are made fresh daily and topped with your choices of fresh meats, veggies, cheese, and sauce.  Price includes two toppings. Add \$1 for each additional topping.",
                    "https://cdn.pixabay.com/photo/2017/12/10/14/47/pizza-3010062_960_720.jpg",
                    6.90
                )
            )

            return  list
        }
    }
}