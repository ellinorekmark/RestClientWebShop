package com.example.restclientwebshop.ShopObjects;

public enum Category {
    FRUIT{
        public String toString() {
            return "Fruit";
        }
    }

    ,
    VEGETABLE{
        public String toString() {
            return "Vegetable";
        }
    },
    OTHER{
        public String toString() {
            return "Other";
        }
    }
}
