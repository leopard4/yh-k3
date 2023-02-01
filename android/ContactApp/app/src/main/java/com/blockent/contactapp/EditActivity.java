package com.blockent.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.blockent.contactapp.model.Contact;

public class EditActivity extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

//        int id = getIntent().getIntExtra("id", 0);
//        String name = getIntent().getStringExtra("name");
//        String phone = getIntent().getStringExtra("phone");
//        editName.setText(name);
//        editPhone.setText(phone);

        Contact contact = (Contact) getIntent().getSerializableExtra("contact");

        editName.setText( contact.name  );
        editPhone.setText( contact.phone );

    }
}






