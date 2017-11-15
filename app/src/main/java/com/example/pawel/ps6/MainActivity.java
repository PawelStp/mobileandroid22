package com.example.pawel.ps6;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products = new ArrayList<Product>();
    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        products.clear();
        products.add(new Product(getResources().getString(R.string.apple),"fruit"));
        products.add(new Product(getResources().getString(R.string.tomato),"vegetable"));
        products.add(new Product(getResources().getString(R.string.cookie),"sweets"));
        products.add(new Product(getResources().getString(R.string.potato),"vegetable"));
        products.add(new Product(getResources().getString(R.string.Strawberry),"fruit"));
        products.add(new Product(getResources().getString(R.string.Bar),"sweets"));

        productList = (ListView)findViewById(R.id.productList);
        CustomAdapter customAdapter;
        customAdapter = new CustomAdapter();
        productList.setAdapter(customAdapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(buttonListener);



    }


    /* My custom adapter */
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.product_layout, null);
            final int pos = position;
            TextView productName = (TextView)view.findViewById(R.id.productName);
            TextView productType = (TextView)view.findViewById(R.id.productType);
            ImageView productImage = (ImageView)view.findViewById(R.id.productImage);
            CheckBox productCheckbox = (CheckBox)view.findViewById(R.id.productCheckbox);

            productName.setText(products.get(position).getName());
            String type = products.get(position).getType();
            switch (type) {
                case "vegetable":
                    productType.setText(getString(R.string.Vegetable));
                    productImage.setBackgroundResource(R.drawable.vegatable);
                    break;
                case "fruit":
                    productType.setText(getString(R.string.Fruit));
                    productImage.setBackgroundResource(R.drawable.fruit);
                    break;
                case "sweets":
                    productType.setText(getString(R.string.Sweets));
                    productImage.setBackgroundResource(R.drawable.cookies);
                    break;
                default:
                    productType.setText("Unknown");
            }


            productCheckbox.setChecked(products.get(position).isChecked());

            productCheckbox.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    boolean checkboxState = products.get(pos).isChecked();
                    products.get(pos).setChecked(!checkboxState);
                    //Toast.makeText(MainActivity.this, checkboxState + "", Toast.LENGTH_LONG).show();
                }
            });
            return view;
        }
    }

    private int getNumberOfChecked() {
        int count = 0;
        for(Product p : products) {
            if (p.isChecked()) {
                count++;
            }
        }
        return count;
    }


    private View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View v) {
            int checkedNumber = getNumberOfChecked();
            Resources res = getResources();
            String productsSelected = res.getQuantityString(R.plurals.element_plural, checkedNumber, checkedNumber);
            Log.d("String", productsSelected);

            Toast.makeText(MainActivity.this, productsSelected, Toast.LENGTH_LONG).show();
             /* https://developer.android.com/guide/topics/resources/string-resource.html#Plurals */
        }
    };




    /* Options menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addAction:
                Toast.makeText(this, "This is my Toast message!", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
