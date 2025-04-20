package com.example.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PurchaseStorageHelper {

    public static List<Purchase> getPurchasesFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sharedPreferences.getString("purchases", "");

        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Purchase>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void savePurchasesToSharedPreferences(Context context, List<Purchase> purchases) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(purchases);
        editor.putString("purchases", json);
        editor.apply();
    }

    public static void clearPurchasesFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().remove("purchases").apply();
    }
}
