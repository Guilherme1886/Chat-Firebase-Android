package examples.guilherme.chat_android.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


import examples.guilherme.chat_android.R;

@EActivity
public class CadastroActivity extends AppCompatActivity {

    @ViewById
    protected EditText email_cadastro;
    @ViewById
    protected EditText password_cadastro;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    private boolean verifyFields() {

        boolean retorno = true;

        if (email_cadastro.getText().length() == 0) {
            email_cadastro.setError("Preencha um e-mail");
            retorno = false;

        }
        if (password_cadastro.getText().length() == 0) {
            password_cadastro.setError("Preencha uma senha");
            retorno = false;

        }
        if (password_cadastro.getText().length() <= 5) {
            password_cadastro.setError("Preencha uma senha com no minimo 6 algarismos");
            retorno = false;

        }


        return retorno;

    }

    public void buttonSendFB(View view) {

        if (verifyFields()) {
            try {
                String email = email_cadastro.getText().toString();
                String password = password_cadastro.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CadastroActivity.this, task -> {

                    if (task.isSuccessful()) {

                        Toast.makeText(CadastroActivity.this, "Usuário criado com sucesso!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CadastroActivity.this, LoginActivity_.class));


                    }

                });


            } catch (Exception e) {

            }

        }

    }
}
