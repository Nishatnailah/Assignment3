package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText et_username,et_email,et_password,et_Conpass;
    private Spinner et_spinner;
    private String username,email,pass,choco,conpass;
    private Button submit;
    private Pattern et_usernamePattern = Pattern.compile("[a-z A-Z._]+");
    private Pattern et_emailPattern=Pattern.compile("[a-zA-Z\\d._%+-]+@(gmail|yahoo)\\.com");
    private Pattern et_passwordPattern=Pattern.compile("[a-zA-Z\\d]+");
    private Pattern et_conpasspattern=Pattern.compile("[a-zA-Z\\d]+");
    private LinearLayout input_layout,output_layout;
    private TextView tv_login,output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_username=findViewById(R.id.et_username);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_pass);
        et_Conpass=findViewById(R.id.et_Conpass);
        et_spinner = findViewById(R.id.et_spinner);
        submit=findViewById(R.id.submit);
        input_layout=findViewById(R.id.input_layout);
        output_layout=findViewById(R.id.output_layout);
        output=findViewById(R.id.output);
        tv_login=findViewById(R.id.tv_login);


        String[] items = new String[]{"Select ", "Kitkat", "DairyMilk", "Snickers", "Ferrero" };

        et_spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        et_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choco = et_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString();
                email = et_email.getText().toString();
                pass = et_password.getText().toString();
                conpass = et_Conpass.getText().toString();



                if (username.isEmpty()){
                    et_username.setError("Empty!!");
                    et_username.requestFocus();
                }else if (!et_usernamePattern.matcher(username).matches()){
                   et_username.setError("Name can be only Alphabet");
                  et_username.requestFocus();
                } else if (email.isEmpty()) {
                    et_email.setError("Empty!!");
                    et_email.requestFocus();
                } else if (!et_emailPattern.matcher(email).matches()){
                        et_email.setError("Invalid mail");
                        et_email.requestFocus();

                } else if (pass.isEmpty()) {
                    et_password.setError("Empty!!");
                    et_password.requestFocus();
                } else if (!et_passwordPattern.matcher(pass).matches()) {
                    et_password.setError("Invalid password!!");
                    et_password.requestFocus();

                } else if (!pass.equals(conpass)) {
                    et_Conpass.setError("Passwords do not match!");
                    et_Conpass.requestFocus();

                } else if (choco.equals("Select ")) {
                    Toast.makeText(getApplicationContext(), "Please Select Chocolates!!", Toast.LENGTH_SHORT).show();
                } else {
                    input_layout.setVisibility(View.GONE);
                    output_layout.setVisibility(View.VISIBLE);
                    String s = "Username: " + username +  "\nEmail: " + email + "\nPassword: "+pass + "\nChocolate: "+choco ;
                    output.setText(s);
                }
            }
        });

    }
}




        

