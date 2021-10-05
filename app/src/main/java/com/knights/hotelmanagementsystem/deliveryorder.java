package com.knights.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deliveryorder extends AppCompatActivity {
    Spinner foditm;
    Spinner ncity;
    Button nextBtn;
    delivery_model delimodel;
    EditText name, email, phone, quantity, address;
    DatabaseReference dbref;
    int totalprice;
    String phoneNo;
    String emailAdd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryorder);

        name = findViewById(R.id.edittxtName);
        email = findViewById(R.id.edittxtEmail);
        phone = findViewById(R.id.edittxtPhone);
        quantity = findViewById(R.id.edittxtQuantity);
        address = findViewById(R.id.edittxtAddress);

        delimodel = new delivery_model();


        foditm = findViewById(R.id.fooditemlist);
        ArrayAdapter<String> fditm = new ArrayAdapter<>(deliveryorder.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fooditem));
        fditm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foditm.setAdapter(fditm);

        ncity = findViewById(R.id.nearcitylist);
        ArrayAdapter<String> ncty = new ArrayAdapter<>(deliveryorder.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nearcity));
        ncty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ncity.setAdapter(ncty);

        nextBtn = (Button) findViewById(R.id.plcodrBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneNo = phone.getText().toString();
                emailAdd = email.getText().toString().trim();

                dbref = FirebaseDatabase.getInstance().getReference().child("Delivery");
                try {
                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(email.getText().toString()) || !emailAdd.matches(emailPattern))
                        Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(foditm.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), "Please select a food item", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(phone.getText().toString()) || phoneNo.length() != 10)
                        Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(quantity.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(address.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a delivery address", Toast.LENGTH_SHORT).show();
                    else {
                        delimodel.setName(name.getText().toString().trim());
                        delimodel.setEmail(email.getText().toString().trim());
                        delimodel.setFooditm(foditm.getSelectedItem().toString().trim());
                        delimodel.setPhone(phone.getText().toString().trim());
                        delimodel.setQuantity(quantity.getText().toString().trim());
                        delimodel.setAddress(address.getText().toString().trim());
                        delimodel.setCity(ncity.getSelectedItem().toString().trim());
                        totalprice = totPriceCalculation(foditm.getSelectedItem().toString(), Integer.parseInt(quantity.getText().toString()));

                        delimodel.setTotalprice(totalprice);

                        dbref.push().setValue(delimodel);

                        dbref.child("lastDeliveryData").setValue(delimodel);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

                        Intent nextintent = new Intent(deliveryorder.this, deliveryconfirm.class);
                        startActivity(nextintent);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    public int totPriceCalculation(String npeople, int quantity) {
        if (npeople.equals("Rice And Curry")) {
            totalprice = 250 * quantity;
        } else if (npeople.equals("Fried Rice")) {
            totalprice = 350 * quantity;
        } else if (npeople.equals("Koththu")) {
            totalprice = 400 * quantity;
        } else if (npeople.equals("Hoppers")) {
            totalprice = 30 * quantity;
        }

        return totalprice;
    }
}