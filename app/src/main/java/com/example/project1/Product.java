package com.example.project1;

public class Product {
        private String name;
        private String price;
        private int imageResId;
        private int quantity ;

    public static final Product[] product = {
            new Product("Jacket","30$" ,R.drawable.jacket,3),
            new Product("pants","50$" ,R.drawable.pants1,4),
            new Product("pants","50$" ,R.drawable.pants2,4),
            new Product("bottle","10$" ,R.drawable.bottle,2),
            new Product("cap","15$" ,R.drawable.cap,1),
            new Product("socks","20$" ,R.drawable.soks,1),
            new Product("shirt","30$" ,R.drawable.shirt1,3),
            new Product("sweter","50$" ,R.drawable.sweter,2),
    };
        public Product(String name, String price, int imageResId , int quantity ) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
            this.quantity =quantity ;
        }


        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public int getImageResId() {
            return imageResId;
        }
    public int getQuantity () {
        return quantity ;
    }public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    }


