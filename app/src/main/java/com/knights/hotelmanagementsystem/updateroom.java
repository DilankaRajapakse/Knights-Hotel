package com.knights.hotelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class updateroom extends AppCompatActivity {

    Spinner rlistup;
    Button uproombtn;
    EditText chiinput, choinput, mumofroomsup, adlinput, chilinput, fullnin, emin, phonein;
    DatabaseReference dbRef;
    DatabaseReference dbRef3;
    room_model roomModel;
    String roomList;
    int total;
    long diff;
    String dateinval;
    String dateoutval;
    String phoneNo;
    String emailAdd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String datePattern = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateroom);

        chiinput = findViewById(R.id.chiinput);
        choinput = findViewById(R.id.choinput);
        mumofroomsup = findViewById(R.id.mumofroomsup);
        adlinput = findViewById(R.id.adlinput);
        chilinput = findViewById(R.id.chilinput);
        fullnin = findViewById(R.id.fullnin);
        emin = findViewById(R.id.emin);
        phonein = findViewById(R.id.phonein);

        uproombtn = findViewById(R.id.rupbtn);

        roomModel = new room_model();

        rlistup = findViewById(R.id.roomlistup);

        final ArrayAdapter<String> radp = new ArrayAdapter<String>(updateroom.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rolist));
        radp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rlistup.setAdapter(radp);




        DatabaseReference dbRef2 = FirebaseDatabase.getInstance().getReference().child("Rooms").child("lastRoomData");
        dbRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    rlistup.setSelection(radp.getPosition(dataSnapshot.child("roomlist").getValue().toString()));
                    chiinput.setText(dataSnapshot.child("checkIn").getValue().toString());
                    choinput.setText(dataSnapshot.child("checkOut").getValue().toString());
                    emin.setText(dataSnapshot.child("emin").getValue().toString());
                    fullnin.setText(dataSnapshot.child("fullnin").getValue().toString());
                    adlinput.setText(dataSnapshot.child("numofAdults").getValue().toString());
                    chilinput.setText(dataSnapshot.child("numofChildren").getValue().toString());
                    mumofroomsup.setText(dataSnapshot.child("numofRooms").getValue().toString());
                    phonein.setText(dataSnapshot.child("phonein").getValue().toString());
                    roomList = dataSnapshot.child("roomlist").getValue().toString();



                } else {
                    Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        uproombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNo = phonein.getText().toString();
                emailAdd = emin.getText().toString().trim();
                dateinval = chiinput.getText().toString().trim();
                dateoutval = choinput.getText().toString().trim();
                dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> arrkeys = new ArrayList<>();
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            arrkeys.add(data.getKey());
                        }

                        String lastKey = arrkeys.get(arrkeys.size()-2);


                        if(dataSnapshot.hasChild(lastKey)){
                            try{

                                if (TextUtils.isEmpty(chiinput.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter check-in date", Toast.LENGTH_SHORT).show();
                                else if (!dateinval.matches(datePattern))
                                    Toast.makeText(getApplicationContext(), "Please enter valid check-in date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(choinput.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter check-out date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(rlistup.getSelectedItem().toString()))
                                    Toast.makeText(getApplicationContext(), "Please select a room type", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(mumofroomsup.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter no. of rooms", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(adlinput.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter no.of adults", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(chilinput.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter no. of children", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(fullnin.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter full name", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(emin.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                                else if (!emailAdd.matches(emailPattern))
                                    Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(phonein.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                                else if (phoneNo.length() != 10)
                                    Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                                else {

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                                    Date firstDate = null;
                                    try {
                                        firstDate = sdf.parse(chiinput.getText().toString());
                                        Date secondDate = sdf.parse(choinput.getText().toString());
                                        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                                        diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                                        Log.d("xxx", String.valueOf(diff));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    roomList = rlistup.getSelectedItem().toString().trim();

                                    roomModel.setCheckIn(chiinput.getText().toString().trim());
                                    roomModel.setCheckOut(choinput.getText().toString().trim());
                                    roomModel.setEmin(emin.getText().toString().trim());
                                    roomModel.setFullnin(fullnin.getText().toString().trim());
                                    roomModel.setNumofAdults(adlinput.getText().toString().trim());
                                    roomModel.setNumofChildren(chilinput.getText().toString().trim());
                                    roomModel.setNumofRooms(mumofroomsup.getText().toString().trim());
                                    roomModel.setPhonein(phonein.getText().toString().trim());
                                    roomModel.setRoomlist(rlistup.getSelectedItem().toString().trim());

                                    Log.d("total", String.valueOf(total));
                                    total = roomtotPriceCalculation(roomList, Integer.parseInt(mumofroomsup.getText().toString()), (int) diff);

                                    roomModel.setTotal(total);

                                    dbRef3 = FirebaseDatabase.getInstance().getReference().child("Rooms").child(lastKey);
                                    dbRef3.setValue(roomModel);

                                    Intent intent = new Intent(updateroom.this, bookedroomdply.class);
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(), "Booking Update Successfully", Toast.LENGTH_SHORT).show();

                                }

                            }catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                            }
                        }


                        if(dataSnapshot.hasChild("lastRoomData")) {
                            try{
                                if (TextUtils.isEmpty(chiinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter check-in date", Toast.LENGTH_SHORT).show();
                                else if (!dateinval.matches(datePattern));
                                    //Toast.makeText(getApplicationContext(), "Please enter valid check-in date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(choinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter check-out date", Toast.LENGTH_SHORT).show();
                                else if (!dateoutval.matches(datePattern));
                                    //Toast.makeText(getApplicationContext(), "Please enter valid check-out date", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(rlistup.getSelectedItem().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please select a room type", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(mumofroomsup.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter no. of rooms", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(adlinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter no.of adults", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(chilinput.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter no. of children", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(fullnin.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter full name", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(emin.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                                else if (!emailAdd.matches(emailPattern));
                                    //Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                                else if (TextUtils.isEmpty(phonein.getText().toString()));
                                    //Toast.makeText(getApplicationContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
                                else if (phoneNo.length() != 10);
                                    //Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                                else {
                                    roomModel.setCheckIn(chiinput.getText().toString().trim());
                                    roomModel.setCheckOut(choinput.getText().toString().trim());
                                    roomModel.setEmin(emin.getText().toString().trim());
                                    roomModel.setFullnin(fullnin.getText().toString().trim());
                                    roomModel.setNumofAdults(adlinput.getText().toString().trim());
                                    roomModel.setNumofChildren(chilinput.getText().toString().trim());
                                    roomModel.setNumofRooms(mumofroomsup.getText().toString().trim());
                                    roomModel.setPhonein(phonein.getText().toString().trim());
                                    roomModel.setRoomlist(rlistup.getSelectedItem().toString().trim());

                                    total = roomtotPriceCalculation(rlistup.getSelectedItem().toString(), Integer.parseInt(mumofroomsup.getText().toString()), (int) diff);

                                    roomModel.setTotal(total);

                                    dbRef3 = FirebaseDatabase.getInstance().getReference().child("Rooms").child("lastRoomData");
                                    dbRef3.setValue(roomModel);

                                    //Intent intent = new Intent(updateroom.this, bookedroomdply.class);
                                    //startActivity(intent);

                                    //Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                                }
                            }catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }

    public int roomtotPriceCalculation(String roomlist, int noOfRooms, int dif){
        if (roomlist.equals("Single Room")) {
            total = dif * 10500 * noOfRooms;
        } else if (roomlist.equals("Double Room")) {
            total = dif * 14500 * noOfRooms;
        } else if (roomlist.equals("Triple Room")) {
            total = dif * 16500 * noOfRooms;
        } else if (roomlist.equals("Quadruple Room")) {
            total = dif * 18000 * noOfRooms;
        }

        return total;
    }

}
