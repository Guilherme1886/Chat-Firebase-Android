package examples.guilherme.chat_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import examples.guilherme.chat_android.model.ChatModel;

/**
 * Created by Guilherme on 27/04/2017.
 */


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<ChatModel> chatModelList;

    public ChatAdapter(List<ChatModel> chatModelList) {
        this.chatModelList = chatModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ChatModel chatModel = chatModelList.get(position);
        holder.item_text_msg.setText(chatModel.getText());


    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView item_text_msg;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_text_msg = (TextView) itemView.findViewById(R.id.item_text_msg);

        }
    }
}
