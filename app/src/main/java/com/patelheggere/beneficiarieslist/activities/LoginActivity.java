package com.patelheggere.beneficiarieslist.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.beneficiarieslist.R;
import com.patelheggere.beneficiarieslist.model.PhoneNumbersModel;
import com.patelheggere.beneficiarieslist.util.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private List<PhoneNumbersModel> phoneNumbersModelList;
    private Button submit;
    private EditText editTextPhoneNumber, editTextPassword;
    private int position;
    private  boolean isFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getMobileNumbers();
    }

    private void initView() {
        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
        editTextPassword = findViewById(R.id.et_pwd);
        editTextPhoneNumber = findViewById(R.id.et_mobile_number);
    }

    private void getMobileNumbers() {
        phoneNumbersModelList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("benefit").child("Leader");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren())
                {
                    PhoneNumbersModel ob = new PhoneNumbersModel();
                    ob = child.getValue(PhoneNumbersModel.class);
                    phoneNumbersModelList.add(ob);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_submit:
                if(editTextPhoneNumber.getText().toString().length()==10)
                {
                    for (int i = 0; i < phoneNumbersModelList.size(); i++) {
                        if (editTextPhoneNumber.getText().toString().equalsIgnoreCase(phoneNumbersModelList.get(i).getPhone())  && editTextPassword.getText().toString().equalsIgnoreCase(phoneNumbersModelList.get(i).getPwd())) {
                            position = i;
                            isFound = true;
                            break;
                        }

                    }
                    if (isFound)
                    {
                        SharedPrefsHelper.getInstance().save("FIRST_TIME", false);
                        SharedPrefsHelper.getInstance().save("NAME",phoneNumbersModelList.get(position).getName());
                        SharedPrefsHelper.getInstance().save("PHONE",phoneNumbersModelList.get(position).getPhone());
                      startActivity(new Intent(LoginActivity.this, MainActivity.class));
                      finish();
                    } else {
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                       // Snackbar.make(findViewById(android.R.id.content), "Your Number is not registered with us for voting please contact Admin/Concerened Person", Snackbar.LENGTH_LONG).show();

                    }
                }
                else {
                    Toast.makeText(this, "Enter 10 digit mobile number", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}
