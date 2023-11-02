package algonquin.cst2335.guo00125;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import algonquin.cst2335.data.ChatRoomViewModel;
import algonquin.cst2335.guo00125.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.guo00125.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {
    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages = new ArrayList<>();

    ChatRoomViewModel chatModel ;
    private RecyclerView.Adapter myAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(clk -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            String inputMessage = binding.textInput.getText().toString();
            boolean sentButton = true;

            messages.add(new ChatMessage(inputMessage, currentDateandTime, sentButton));

            // clear teh previous text
            binding.textInput.setText("");

            myAdapter.notifyDataSetChanged();


        });


        binding.receiveButton.setOnClickListener(clk -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            String inputMessage = binding.textInput.getText().toString();
            boolean sentButton = false;

            messages.add(new ChatMessage(inputMessage, currentDateandTime, sentButton));

            myAdapter.notifyDataSetChanged();

            // clear teh previous text
            binding.textInput.setText("");
        });

        binding.recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                // viewType will either be 0 or 1

                if (viewType == 0) {
                    // 1. load a XML layout
                    SentMessageBinding binding =                            // parent is incase matchparent
                            SentMessageBinding.inflate(getLayoutInflater(), parent, false);

                    // 2. call our constructor below
                    return new MyRowHolder(binding.getRoot()); // getRoot returns a ConstraintLayout with TextViews inside
                }
                else{
                    // 1. load a XML layout
                    ReceiveMessageBinding binding =                            // parent is incase matchparent
                            ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);

                    // 2. call our constructor below
                    return new MyRowHolder(binding.getRoot()); // getRoot returns a ConstraintLayout with TextViews inside

                }
            }

            public int  getItemViewType(int position){
                // determine which layout to load at row position
                if (messages.get(position).isSentButton() == true) // for the first 5 rows
                {
                    return 0;
                }
                else return 1;
            }
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.getMessage());
//                holder.messageText.setText("");
                holder.timeText.setText(obj.getTimeSent());

            }

            @Override
            public int getItemCount() {
                return messages.size();
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class MyRowHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }
}