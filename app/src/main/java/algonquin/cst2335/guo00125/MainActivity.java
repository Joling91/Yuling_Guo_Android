package algonquin.cst2335.guo00125;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import algonquin.cst2335.guo00125.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
        Log.d( TAG, "Message");

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("EmailAddress", "");

        binding.emailEditText.setText(emailAddress);


        binding.loginButton.setOnClickListener(clk -> {
            Log.i(TAG, "You clicked the button");

            Intent nextPage = new Intent(MainActivity.this , SecondActivity.class);
            nextPage.putExtra( "EmailAddress", binding.emailEditText.getText().toString() );
            String emailAddress1 = binding.emailEditText.getText().toString();
            SharedPreferences.Editor myEditor = prefs.edit();
            myEditor.putString("EmailAddress",emailAddress1);
            myEditor.apply();
            startActivity(nextPage);
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.w( "MainActivity", "In onStart() - Loading Widgets");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( "MainActivity", "In onPause() - Loading Widgets" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( "MainActivity", "In onDestroy() - Loading Widgets" );
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.w( "MainActivity", "In onResume() - Loading Widgets" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( "MainActivity", "In onStop() - Loading Widgets" );
    }


}