package edu.utsa.dreamweaver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

public class MainActivity extends ComponentActivity {
    Button button;
    String unameTest = "test";
    String passTest = "test";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        setupButtons();

    }

    //buttons
    private void setupButtons(){
        button = (Button) findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText uname = (EditText) findViewById(R.id.input_username);
                EditText upass = (EditText) findViewById(R.id.input_password);
                if (authenticate(uname.getText().toString(), upass.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    uname.setError("Username not found");
                    upass.setError("Incorrect Password");
                }
            }
        });
    }

    private boolean authenticate (String username, String password){
        return username.equalsIgnoreCase(unameTest) && password.equals(passTest);
    }
}
