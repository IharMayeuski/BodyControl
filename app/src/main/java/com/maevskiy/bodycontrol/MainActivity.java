package com.maevskiy.bodycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> productForChoice = new ArrayList();
    ArrayList<String> products = new ArrayList();
    ArrayList<String> weights = new ArrayList();
    ArrayAdapter<String> adapterProducts;
    ArrayAdapter<String> adapterForChoice;
    ArrayAdapter<String> adapterForWeights;
    ArrayList<String> selectedProducts = new ArrayList();
    ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productForChoice.add("-");
        productForChoice.add("Bread");
        productForChoice.add("Brockolly");

        weights.add("-");
        weights.add("50");
        weights.add("100");

        Spinner spinner = (Spinner) findViewById(R.id.products);
        adapterForChoice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productForChoice);
        adapterForChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterForChoice);

        Spinner spinnerChoiceWeight = (Spinner) findViewById(R.id.productweights);
        adapterForWeights = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weights);
        adapterForWeights.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoiceWeight.setAdapter(adapterForWeights);

        productList = (ListView) findViewById(R.id.productList);
        adapterProducts = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, products);
        productList.setAdapter(adapterProducts);
        // обработка установки и снятия отметки в списке
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String product = adapterForChoice.getItem(position);
                if (productList.isItemChecked(position)) {
                    selectedProducts.add(product);
                } else {
                    selectedProducts.remove(product);
                }
            }
        });
    }

    public void add(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.products);
        String product = spinner.getSelectedItem().toString();
        if (!product.isEmpty() && !products.contains(product)) {
            adapterProducts.add(product);
            adapterProducts.notifyDataSetChanged();
        }
    }

    public void remove(View view) {
        for (int i = 0; i < selectedProducts.size(); i++) {
            adapterProducts.remove(selectedProducts.get(i));
        }
        productList.clearChoices();
        selectedProducts.clear();
        adapterProducts.notifyDataSetChanged();
    }
}
