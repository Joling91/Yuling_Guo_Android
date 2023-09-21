package algonquin.cst2335.guo00125.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.guo00125.R;
import algonquin.cst2335.guo00125.data.MainViewModel;
import algonquin.cst2335.guo00125.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        model = new ViewModelProvider(this).get(MainViewModel.class);
        setContentView(binding.getRoot());

        TextView tv = binding.myTextView;
        Button b = binding.myButton;
        EditText et = binding.myEditText;
        CheckBox checkBox = binding.checkBox;
        Switch switch1 = binding.switch1;
        RadioButton radioButton = binding.radioButton;
        ImageView imageView = binding.myImage;
        ImageButton imageButton = binding.myImageButton;

        binding.checkBox.setOnCheckedChangeListener(
                (btn, onOrOff) -> {
                    model.onOrOff.postValue(onOrOff);
                });
        binding.switch1.setOnCheckedChangeListener((btn, onOrOff) -> {
            model.onOrOff.postValue(onOrOff);
        });
        binding.radioButton.setOnCheckedChangeListener((btn, onOrOff) -> {
            model.onOrOff.postValue(onOrOff);
        });
        binding.myImageButton.setOnClickListener(clk -> {
        });
        model.onOrOff.observe(this, newValue -> {
            binding.checkBox.setChecked(newValue);
            binding.switch1.setChecked(newValue);
            binding.radioButton.setChecked(newValue);
            String toastMessage = "The value is now" + newValue;
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = et.getText().toString();
                model.userString.postValue(string); // Use setValue() on MutableLiveData
                b.setText("You clicked the button");

            }
        });

        model.userString.observe(this, s -> {
            tv.setText("Your text is now:" + s);
        });

        binding.myImageButton.setOnClickListener(view -> {
            int width = binding.myImageButton.getWidth();
            int height = binding.myImageButton.getHeight();

            // Show a Toast message with the width and height
            String toastMessage = "The width = " + width + " and height = " + height;
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
        });

    }

    }

