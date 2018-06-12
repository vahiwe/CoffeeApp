package com.example.android.coffeeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int calcPrice() {
        int basePrice = 5;
        if (whippedCream()) {
            basePrice += 1;
        }
        if (chocolate()) {
            basePrice += 2;
        }
        int price= quantity * basePrice;
        return price;
    }

    public String orderMessage (String name, int price) {
        String summary = "Welcome To Kath's Cafe, " + name + " \n";
        summary += "You ordered ";
        if (quantity == 1) {
            if (whippedCream()) {
                summary += "a cup of Coffee with a topping of Whipped Cream" + "\n";
            } else if (chocolate()) {
                summary += "a cup of Chocolate flavored Coffee" + "\n";
            } else if (whippedCream() && chocolate()) {
                summary += "a cup of Chocolate flavored Coffee With topping of Whipped Cream" + "\n";
            } else {
                summary += "a cup of Coffee\n";
            }
        } else {
            if (whippedCream()) {
                summary += quantity + " cups of Coffee with toppings of Whipped Cream" + "\n";
            } else if (chocolate()) {
                summary += quantity + " cups of Chocolate flavored Coffee" + "\n";
            } else if (whippedCream() && chocolate()) {
                summary += quantity + " cups of Chocolate flavored Coffee With toppings of Whipped Cream" + "\n";
            } else {
                summary += quantity + " cups of Coffee\n";
            }
        }

        summary += "Your bill is $" + price + "\n";
        summary += "Thank you for your patronage\nCome again Soon!!!";
        return summary;
    }

    public void order (View view) {
        EditText text = (EditText) findViewById(R.id.name);
        String name = text.getText().toString();
        if (name == "") {
            Toast.makeText(this, "Type in your name!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        String order = orderMessage(name, calcPrice());
        summary(order);
    }

    public void increment (View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You've reached the maximum number of cups!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        quantityView(quantity);
    }

    public void decrement (View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You've reached the minimum number of cups!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        quantityView(quantity);
    }

    public void summary (String message) {
        TextView textView = (TextView) findViewById(R.id.summary);
        textView.setText(message);
    }

    public void quantityView (int number) {
        TextView textView = (TextView) findViewById(R.id.quantity);
        textView.setText("" + number);
    }

    public boolean whippedCream () {
        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped);
        boolean value = checkBox.isChecked();
        return value;
    }

    public boolean chocolate () {
        CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate);
        boolean value = checkBox.isChecked();
        return value;
    }
}
