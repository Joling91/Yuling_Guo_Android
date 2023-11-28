package algonquin.cst2335.guo00125;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import algonquin.cst2335.guo00125.databinding.ActivityMainBinding;




/**
 * The `MainActivity` class is the main entry point for the Android application.
 * It provides a user interface with an EditText for entering a password, a Button to initiate the password check, and a TextView for displaying messages.
 *
 * @author Yuling Guo
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
//    /**
//     * This holds the text at the centre of the screen.
//     */
//    private TextView tv = null;
//
//    /**
//     * This holds the EditText at the centre of the screen.
//     */
//    private EditText et = null;
//
//    /**
//     * This holds the Button at the centre of the screen.
//     */
//    private Button btn = null;

    //
//    /**
//     * This function checks the complexity of the provided password.
//     *
//     * @param pw The password string to be checked.
//     * @return `true` if the password meets complexity requirements, `false` otherwise.
//     */
//    boolean checkPasswordComplexity(String pw) {
//        boolean foundUpperCase = false;
//        boolean foundLowerCase = false;
//        boolean foundNumber = false;
//        boolean foundSpecial = false;
//
//        for (int i = 0; i < pw.length(); i++) {
//            char c = pw.charAt(i);
//            if (Character.isUpperCase(c)) {
//                foundUpperCase = true;
//            } else if (Character.isLowerCase(c)) {
//                foundLowerCase = true;
//            } else if (Character.isDigit(c)) {
//                foundNumber = true;
//            } else if (!Character.isLetterOrDigit(c)) {
//                foundSpecial = true;
//            }
//        }
//
//        if (!foundUpperCase) {
//            Toast.makeText(this, "You are missing an uppercase letter.", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (!foundLowerCase) {
//            Toast.makeText(this, "You are missing a lower case letter.", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (!foundNumber) {
//            Toast.makeText(this, "You are missing a number.", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (!foundSpecial) {
//            Toast.makeText(this, "You are missing a special character.", Toast.LENGTH_SHORT).show();
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//
//    /**
//     * Checks if the provided character is a special character.
//     *
//     * @param c The character to be checked.
//     * @return Return true if the character is a special character, false otherwise.
//     */
//    boolean isSpecialCharacter(char c)
//    {
//        switch (c) {
//            // Return true if `c` is one of: #, ?, $, %, ^, &, *, !, @
//            case '#':
//            case '?':
//            case '$':
//            case '%':
//            case '^':
//            case '&':
//            case '*':
//            case '!':
//            case '@':
//                return true;
//            default:
//                return false; // Return false otherwise
//        }
//    }
//
//    /**
//     * Called when the activity is first created. This is where you should do all of your normal
//     * static set up: create views, bind data to lists, etc. This method also provides a Bundle
//     * containing the activity's previously frozen state, if there was one.
//     *
//     * @param savedInstanceState If the activity is being re-initialized after previously being
//     *                           shut down, this Bundle contains the data it most recently
//     *                           supplied in onSaveInstanceState(Bundle). Note: Otherwise, it is
//     *                           null.
//     */
    ActivityMainBinding binding;
    protected String cityName;
    protected String url;
    protected RequestQueue queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView(binding.getRoot());
        queue = Volley.newRequestQueue(this);
        Button search = binding.getButton;

        search.setOnClickListener(click -> {
            Log.d("Button Clicked", "Button was clicked");
            try {
                cityName = URLEncoder.encode(binding.editCityName.getText().toString(),"UTF-8");
                url = "http://api.openweathermap.org/data/2.5/weather?q="
                        + cityName
                        + "&appid=7e943c97096a9784391a981c4d878b22&units=metric";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, this::onResponse, (error) -> { });
                queue.add(request);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void onResponse(JSONObject response) {
        try {
            JSONArray weatherArray = response.getJSONArray("weather");

            JSONObject mainObject = response.getJSONObject("main");


            double current = mainObject.getDouble("temp");
            binding.temp.setText("The current temperature is " + current);
            binding.temp.setVisibility(View.VISIBLE);

            double min = mainObject.getDouble("temp_min");
            binding.minTemp.setText("The min temperature is " + min);
            binding.minTemp.setVisibility(View.VISIBLE);

            double max = mainObject.getDouble("temp_max");
            binding.maxTemp.setText("The max temperature is " + max);
            binding.maxTemp.setVisibility(View.VISIBLE);

            int humidity = mainObject.getInt("humidity");
            binding.humidity.setText("The humidity is " + humidity);
            binding.humidity.setVisibility(View.VISIBLE);


            String iconname = null;
            String description = null;
            for (int i = 0; i < weatherArray.length(); i++) {
                JSONObject thisObj = weatherArray.getJSONObject(i);
                description = thisObj.getString("description");
                iconname = thisObj.getString("icon");

            }
            binding.description.setText(description);
            binding.description.setVisibility(View.VISIBLE);


//image request
            String pathname = getFilesDir() + "/" + iconname + ".png";
            File imagefile = new File(pathname);
            if (imagefile.exists()) {
                //load from disk
                binding.icon.setImageBitmap(BitmapFactory.decodeFile(pathname));
                binding.icon.setVisibility(View.VISIBLE);
            } else {
                String imageUrl = "http://openweathermap.org/img/w/" + iconname + ".png";
                ImageRequest imgReq = new ImageRequest(imageUrl, bitmap -> {
                    // Do something with loaded bitmap...
                    binding.icon.setImageBitmap(bitmap);
                    binding.icon.setVisibility(View.VISIBLE);
                    Log.d("Run", "Image loaded");
                }, 1024, 1024, ImageView.ScaleType.CENTER, null, (error) -> {
                    Log.d("Error", "No image");
                });
                queue.add(imgReq);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
