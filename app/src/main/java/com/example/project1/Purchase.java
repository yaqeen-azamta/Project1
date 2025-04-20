package com.example.project1;
import android.os.Parcel;
import android.os.Parcelable;

public class Purchase implements Parcelable {
    private String name;
    private String price;
    private int imageResId;
    private String size;
    private String color;
    private int quantitys;

    public Purchase(String name, String price, int imageResId, String size, String color, int quantitys) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.size = size;
        this.color = color;
        this.quantitys = quantitys;
    }

    // Getters
    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    public int getQuantitys() { return quantitys; }

    // Setter
    public void setQuantity(int quantity) {
        this.quantitys = quantity;
    }

    // Parcelable implementation
    protected Purchase(Parcel in) {
        name = in.readString();
        price = in.readString();
        imageResId = in.readInt();
        size = in.readString();
        color = in.readString();
        quantitys = in.readInt();
    }

    public static final Creator<Purchase> CREATOR = new Creator<Purchase>() {
        @Override
        public Purchase createFromParcel(Parcel in) {
            return new Purchase(in);
        }

        @Override
        public Purchase[] newArray(int size) {
            return new Purchase[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeInt(imageResId);
        parcel.writeString(size);
        parcel.writeString(color);
        parcel.writeInt(quantitys);
    }
}
