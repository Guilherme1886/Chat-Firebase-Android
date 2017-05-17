package examples.guilherme.chat_android.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;



import examples.guilherme.chat_android.R;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @ViewById
    protected EditText email_login;
    @ViewById
    protected EditText password_login;
    @ViewById
    protected Button button_entrar;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if (mUser != null) {
            startActivity(new Intent(this, ChatActivity_.class));
            finish();

        }
    }


    private boolean verifyFields() {

        boolean retorno = true;

        if (email_login.getText().length() == 0) {
            email_login.setError("Preencha um e-mail");
            retorno = false;

        }
        if (password_login.getText().length() == 0) {
            password_login.setError("Preencha uma senha");
            retorno = false;

        }
        if (password_login.getText().length() <= 5) {
            password_login.setError("Preencha uma senha com no minimo 6 algarismos");
            retorno = false;

        }


        return retorno;

    }

    public void buttonCadastrar(View view) {

        startActivity(new Intent(this, CadastroActivity_.class));

    }

    public void buttonEntrar(View view) {

        String email = email_login.getText().toString();
        String pass = password_login.getText().toString();

        if (verifyFields()) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
                try {

                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, ChatActivity_.class));
                    }


                } catch (Exception e) {

                    Log.v("error", e.getMessage());

                }


            });
        }


    }
}
