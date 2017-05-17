package examples.guilherme.chat_android.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import examples.guilherme.chat_android.model.ChatModel;
import examples.guilherme.chat_android.R;
import examples.guilherme.chat_android.utils.Utils;

@EActivity
public class ChatActivity extends AppCompatActivity {


    @ViewById
    protected Toolbar toolbar;
    @ViewById
    protected EditText text_msg;
    @ViewById
    protected FloatingActionButton button_send;
    @ViewById
    protected ListView list_of_messages;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseListAdapter<ChatModel> adapter;
    public static String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        configToolbar();
        configAuthFirebase();
        requestMessagesFirebase();

    }

    private void configAuthFirebase() {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }

    private void requestMessagesFirebase() {
        adapter = new FirebaseListAdapter<ChatModel>(this, ChatModel.class, R.layout.item_chat, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, ChatModel model, int position) {

                TextView messageText = (TextView) v.findViewById(R.id.item_text_msg);
                messageText.setText(model.getText());

                list_of_messages.smoothScrollToPositionFromTop(position, 0, 0);


            }
        };


        list_of_messages.setAdapter(adapter);


    }


    public void buttonSendMessageWithFirebase(View view) {

        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                .setValue(new ChatModel(text_msg.getText().toString()));

        text_msg.setText("");
        list_of_messages.smoothScrollToPositionFromTop(adapter.getCount() - 1, 0, 0);
        Utils.hideKeyboard(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                mAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
