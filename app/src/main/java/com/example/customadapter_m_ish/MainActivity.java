package com.example.customadapter_m_ish;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView lvProduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;
    private Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProduct = findViewById(R.id.listview_product);
        btnAddProduct = findViewById(R.id.btn_add_product);

        mProductList = new ArrayList<>();
        mProductList.add(new Product(1, "Iphone 15", 899, "Apple Iphone 15 128GB"));
        mProductList.add(new Product(2, "Iphone 15 Plus", 999, "Apple Iphone 15 Plus 128GB"));
        mProductList.add(new Product(3, "Iphone 15 Pro", 1099, "Apple Iphone 15 Pro 128GB"));
        mProductList.add(new Product(4, "Iphone 15 Pro Max", 1199, "Apple Iphone 15 Pro Max 256GB"));

        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Bosildi: " + mProductList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });
    }

    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_product, null);

        EditText etName = dialogView.findViewById(R.id.et_product_name);
        EditText etPrice = dialogView.findViewById(R.id.et_product_price);
        EditText etDescription = dialogView.findViewById(R.id.et_product_description);

        builder.setView(dialogView)
                .setTitle("Yangi tovar qo'shish")
                .setPositiveButton("Qo'shish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = etName.getText().toString();
                        String priceStr = etPrice.getText().toString();
                        String description = etDescription.getText().toString();

                        if (!name.isEmpty() && !priceStr.isEmpty() && !description.isEmpty()) {
                            int price = Integer.parseInt(priceStr);
                            mProductList.add(new Product(mProductList.size() + 1, name, price, description));
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "Iltimos barcha qatorlarni to'ldiring!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Bekor qilish", null)
                .create()
                .show();
    }
}
