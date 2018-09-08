package com.patelheggere.beneficiarieslist.activities;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patelheggere.beneficiarieslist.R;
import com.patelheggere.beneficiarieslist.dbhelper.DBManager;
import com.patelheggere.beneficiarieslist.dbhelper.DatabaseHelper;
import com.patelheggere.beneficiarieslist.model.DetailsModel;
import com.patelheggere.beneficiarieslist.util.SharedPrefsHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSubmit;
    private EditText editTextVillage, editTextWard, editTextName, editTextMobile, editTextBenefit, editTextEpic;
    private Spinner spinnerVillage;
    private DBManager dbManager;
    private Cursor mCursor;
    private int nofItems;
    private DatabaseReference mDBRef;
    private String village;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        dbManager = new DBManager(MainActivity.this);
        buttonSubmit = findViewById(R.id.btn_submit);
        buttonSubmit.setOnClickListener(this);
        spinnerVillage = findViewById(R.id.sp_village);
        editTextBenefit = findViewById(R.id.et_benefit);
        editTextEpic = findViewById(R.id.et_epic);
        editTextMobile = findViewById(R.id.et_mobile);
        editTextName = findViewById(R.id.et_name);
        editTextWard = findViewById(R.id.et_ward);
        final List<String> catList = new ArrayList<>();
        catList.add("Select Village");
        catList.add("Kuduchi");
        catList.add("Gundawad");
        catList.add("Shiragur ");
        catList.add("Paramanandwadi ");
        catList.add("Nilaji ");
        catList.add("Suttatti ");
        catList.add("Morab ");
        catList.add("Bekkeri ");
        catList.add("Nidagundi ");
        catList.add("Alagawadi ");
        catList.add("Alakhanur ");
        catList.add("Yalparatti ");
        catList.add("Khemalapur ");
        catList.add("Siddapur ");
        catList.add("Koligudda ");
        catList.add("Yabaratti ");
        catList.add("Badabyakud ");
        catList.add("Harugeri ");
        catList.add("Bastawad ");
        catList.add("Savasuddi ");
        catList.add("Devapurahatti ");
        catList.add("Katakabhavi ");
        catList.add("Hidakal ");
        catList.add("Khanadal ");
        catList.add("Mugalkhod ");
        catList.add("Itanal ");
        catList.add("Palabhavi ");
        catList.add("Sultanapur ");
        catList.add("Kappalguddi ");
        catList.add("Marakudi ");
        catList.add("Handigund");


        ArrayAdapter<String> CategoryAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, catList);
         CategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to our spinner
        spinnerVillage.setAdapter(CategoryAdapter);
        spinnerVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(catList.get(position).equalsIgnoreCase("Select Village"))
                {
                    village = null;
                }
                else {
                    village = catList.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this,"Select Village", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_submit:
                submitDetails();
                break;
        }
    }

    private void submitDetails() {
        DetailsModel  ob = new DetailsModel();
        ob.setBenefit(editTextBenefit.getText().toString());
        String benefit = editTextBenefit.getText().toString();
        String ward = editTextWard.getText().toString();
        String epic = editTextEpic.getText().toString();
        String name = editTextName.getText().toString();
        String mobile = editTextMobile.getText().toString();

        if(TextUtils.isEmpty(benefit))
        {
            editTextBenefit.setError("Please Enter Benefit Details");
            return;
        }
        if(TextUtils.isEmpty(village))
        {
            Toast.makeText(MainActivity.this, "Enter Village Name", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(ward))
        {
            editTextWard.setError("Please Enter Ward Details");
            return;
        }

        if(TextUtils.isEmpty(epic))
        {
            editTextEpic.setError("Please Enter EPIC Details");
            return;
        }
        if(TextUtils.isEmpty(name))
        {
            editTextName.setError("Please Enter Name");
            return;
        }
        if(TextUtils.isEmpty(mobile))
        {
            editTextMobile.setError("Please Enter Mobile Number");
            return;
        }
        ob.setBenefit(benefit);
        editTextBenefit.setText("");
        ob.setEpic(epic);
        editTextEpic.setText("");
        ob.setMobile(mobile);
        editTextMobile.setText("");
        ob.setVillage(village);
        ob.setWard(ward);
        editTextWard.setText("");
        ob.setName(name);
        editTextName.setText("");
        ob.setEnteredBy(SharedPrefsHelper.getInstance().get("NAME","null" ));
        ob.setEnteredMobile(SharedPrefsHelper.getInstance().get("PHONE","null" ));
        ob.setDate(getCurDateFromTimeStamp(new Date().getTime()));
        dbManager.open();
        dbManager.insert(ob);
        dbManager.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.updload:
                upload();
               // Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void upload()
    {
        dbManager = new DBManager(MainActivity.this);
        dbManager.open();
        mCursor = dbManager.fetch();
        nofItems=mCursor.getCount();
        mCursor = dbManager.fetch();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirm");
        builder.setMessage("There are "+nofItems+" details to upload");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DetailsModel ob = new DetailsModel();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                mDBRef = firebaseDatabase.getReference().child("BenefitDetails").child(SharedPrefsHelper.getInstance().get("PHONE", "9611620128"));
                if (mCursor.moveToFirst()){
                    do{
                        ob.setEnteredMobile(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.ENTERED_MOBILE)));
                        ob.setMobile(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.MOBILE)));
                        ob.setName(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.NAME)));
                        ob.setEnteredBy(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.ENTERED_NAME)));
                        ob.setVillage(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.VILLAGE)));
                        ob.setWard(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.WARD)));
                        ob.setEpic(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.EPIC)));
                        ob.setBenefit(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.BENEFIT)));
                        ob.setDate(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.DATE)));
                        mDBRef.push().setValue(ob);
                        dbManager.delete(mCursor.getLong(mCursor.getColumnIndex(DatabaseHelper._ID)));
                        nofItems = nofItems-1;
                        // do what ever you want here
                    }while(mCursor.moveToNext());
                    Toast.makeText(MainActivity.this, "Uploaded Successfully to server", Toast.LENGTH_LONG).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        if(nofItems>0)
        {
            alertDialog.show();
        }
        else {
            Toast.makeText(MainActivity.this, "No data to uplaod", Toast.LENGTH_LONG).show();
        }

    }



    public static String getCurDateFromTimeStamp(long tim)
    {
        try {
            DateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
            Date netDate = new Date(tim);
            return sdf.format(netDate);
        }catch (Exception ex)
        {

        }
        return "";
    }
}
