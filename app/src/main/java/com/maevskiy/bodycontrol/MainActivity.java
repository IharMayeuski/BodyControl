package com.maevskiy.bodycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> productForChoice = new ArrayList();
    List<Product> products = new ArrayList();
    ArrayAdapter<Product> adapterProducts;
    ArrayAdapter<String> adapterForChoice;
    ArrayAdapter<String> adapterForWeights;
    List<Product> selectedProducts = new ArrayList();
    ListView productList;
    int calories = 0;
    String DEFAULT_PRODUCT = "Choose";
    String DEFAULT_WEIGHT = "0";
    List<String> weights = Arrays.asList(DEFAULT_WEIGHT, "50", "75", "100");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productForChoice.add(DEFAULT_PRODUCT);
        for (Food food : Food.values()) {
            productForChoice.add(food.getName());
        }
        Spinner spinnerProductForChoice = (Spinner) findViewById(R.id.products);
        adapterForChoice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productForChoice);
        adapterForChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProductForChoice.setAdapter(adapterForChoice);

        Spinner spinnerChoiceWeight = (Spinner) findViewById(R.id.productweights);
        adapterForWeights = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weights);
        adapterForWeights.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoiceWeight.setAdapter(adapterForWeights);

        productList = (ListView) findViewById(R.id.productList);
        adapterProducts = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, products);
        productList.setAdapter(adapterProducts);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Product product = adapterProducts.getItem(position);
                if (productList.isItemChecked(position)) {
                    selectedProducts.add(product);
                } else {
                    selectedProducts.remove(product);
                }
            }
        });
    }

    public void add(View view) {
        Spinner spinnerChoiceProduct = (Spinner) findViewById(R.id.products);
        Spinner spinnerChoiceWeight = (Spinner) findViewById(R.id.productweights);
        String productName = spinnerChoiceProduct.getSelectedItem().toString();
        int weight = !spinnerChoiceWeight.getSelectedItem().toString().equals(DEFAULT_WEIGHT)
                ? Integer.parseInt(spinnerChoiceWeight.getSelectedItem().toString())
                : 0;
        if (
                !productName.isEmpty()
                        && !productName.equals(DEFAULT_PRODUCT)
                        && getProductByName(products, productName) == null
                        && weight > 0
        ) {
            int productCalorie = Food.valueOf(productName.toUpperCase()).getCalories() * weight / 100;
            adapterProducts.add(new Product(productName, weight, productCalorie));
            adapterProducts.notifyDataSetChanged();
            calories += productCalorie;
            TextView textView = (TextView) findViewById(R.id.textViewCalories);
            textView.setText(String.valueOf(calories) + " cal.");
            spinnerChoiceProduct.setSelection(0);
            spinnerChoiceWeight.setSelection(0);
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            CharSequence text;
            if (productName.isEmpty() || productName.equals(DEFAULT_PRODUCT)) {
                text = "Please to choose your product!";
            } else {
                text = "Your weight is EMPTY!";
            }
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void remove(View view) {
        for (int i = 0; i < selectedProducts.size(); i++) {
            adapterProducts.remove(selectedProducts.get(i));
            calories -= selectedProducts.get(i).getCalories();
        }
        TextView textView = (TextView) findViewById(R.id.textViewCalories);
        textView.setText(String.valueOf(calories));
        productList.clearChoices();
        selectedProducts.clear();
        adapterProducts.notifyDataSetChanged();
    }

    public void burn(View view) {
        Intent goToSecond = new Intent();
        goToSecond.setClass(this, RunActivity.class);
        goToSecond.putExtra("calories", calories);
        startActivity(goToSecond);
    }

    private Product getProductByName(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }


}
