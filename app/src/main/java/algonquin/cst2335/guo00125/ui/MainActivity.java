package algonquin.cst2335.guo00125.ui;

import static algonquin.cst2335.guo00125.R.string.button_text;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import algonquin.cst2335.guo00125.R;
import algonquin.cst2335.guo00125.data.MainViewModel;
import algonquin.cst2335.guo00125.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    @Override //app starts here
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate( getLayoutInflater() );
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setContentView( binding.getRoot() );

    TextView tv =binding.myTextView; //same as getElementById() in HTML
    Button b =  binding.myButton; //must not be null
    EditText et =binding.myEditText;

    //this will be called when the value changes:
        viewModel.userString.observe(this, new Observer<String>() {
        @Override
        public void onChanged(String s) {
            tv.setText(s);
        }
    });
        et.setText( viewModel.userString.getValue() );
        b.setOnClickListener(v -> {

        String string = et.getText().toString(); //read what the user typed
        viewModel.userString.postValue( string ); //set the value, and notify observers
        b.setText("You clicked the button");
    });
}
}