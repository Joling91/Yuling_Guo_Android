import static algonquin.cst2335.guo00125.R.string.button_text;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import algonquin.cst2335.guo00125.R;
import algonquin.cst2335.guo00125.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TextView tv = binding.myTextview;
        Button b = binding.myButton;
        EditText et = binding.myEditText;
        b.setOnClickListener(v -> {
             tv.setText(button_text);
             et.setText("You clicked the button");
             b.setText("You clicked the button");
        });
    }
}