package algonquin.cst2335.guo00125;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import algonquin.cst2335.guo00125.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.guo00125.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {
    ArrayList<String> messages = new ArrayList<>();
    ActivityChatRoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.sendButton.setOnClickListener(cli -> {
            messages.add(binding.textInput.getText().toString());
            binding.textInput.setText("");
        });
        binding.myRecycler.setAdapter(new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
                return new MyRowHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

            }

            @Override
            public int getItemCount() {

                return messages.size();
            }
        });
    };
    }
    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageView;
        TextView timeView;
        public MyRowHolder(@NonNull View theRootConstraintLayout) {
            super(theRootConstraintLayout);

            messageView = theRootConstraintLayout.findViewById(R.id.textView);
            timeView = theRootConstraintLayout.findViewById(R.id.textView2);
        }
    }
