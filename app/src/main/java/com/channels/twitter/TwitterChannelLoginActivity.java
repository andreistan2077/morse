package com.channels.twitter;

import com.R;
import java.util.List;
import android.os.Bundle;
import com.morse.Channel;
import com.morse.Contact;
import com.morse.Message;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Class that handles the UI/UX for logging in to a Twitter account.
 * After a successful login, it will redirect the user to another activity.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class TwitterChannelLoginActivity extends AppCompatActivity implements Channel {
    private AppCompatActivity parentActivity;
    private EditText userCredential;
    private EditText userPassword;
    private Button loginButton;
    private TextView remainingAttempts;
    private boolean credentialsCheckPassed      = false;
    private int currentNumberOfAvailableRetries = 3;
    /* Hard-coded for now, just to see it works */
    private final String accountName            = "@rreloaded_";

    public TwitterChannelLoginActivity() {}

    public TwitterChannelLoginActivity(AppCompatActivity activity) {
        this.parentActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_login_activity);

        this.userCredential = findViewById(R.id.userCredential);
        this.userPassword = findViewById(R.id.userPassword);
        this.loginButton = findViewById(R.id.loginButton);
        this.remainingAttempts = findViewById(R.id.remainingAttemps);

        loginButton.setOnClickListener(v -> {
            String inputName = userCredential.getText().toString();
            String inputPassword = userPassword.getText().toString();

            if (inputName.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(TwitterChannelLoginActivity.this,
                        "You didn't forgot anything? :)", Toast.LENGTH_SHORT).show();
            } else {
                credentialsCheckPassed = validateCredentials(inputName, inputPassword);
                if (credentialsCheckPassed) {
                    Toast.makeText(TwitterChannelLoginActivity.this,
                            "Login successful", Toast.LENGTH_SHORT).show();

                    /* If we are here it means that the user successfully logged in, thus
                     * move to the next activity and that should be the activity the user sees
                     * after successfully logging in.
                     */
                    Intent intent = new Intent(TwitterChannelLoginActivity.this,
                            TwitterChannelLoginActivity.class);
                    startActivity(intent);
                } else {
                    currentNumberOfAvailableRetries--;
                    Toast.makeText(TwitterChannelLoginActivity.this,
                            "Incorrect username or password", Toast.LENGTH_SHORT).show();

                    remainingAttempts.setText(currentNumberOfAvailableRetries);
                    if (currentNumberOfAvailableRetries == 0){
                        loginButton.setEnabled(false);
                    }
                }
            }
        });
    }

    private boolean validateCredentials(String userCredential, String userPassword){
        /* TODO: Implement this to use the Twitter's API in order to check the validity of the
         * credentials.
         */
        return true;
    }

    @Override
    public Intent getIntent(){
        return new Intent(this.parentActivity, TwitterChannelLoginActivity.class);
    }

    @Override
    public void login() {
        /* TODO: Implement this! */
    }

    @Override
    public void refreshChannel() {
        /* TODO: Implement this! */
    }

    @Override
    public void getContacts(int contactNumber) {
        /* TODO: Implement this! */
    }

    @Override
    public void checkCredentials() {
        /* TODO: Implement this! */
    }

    @Override
    public void sendDelayedMessage(Message message, List<Contact> contact) {
        /* TODO: Implement this! */
    }

    @Override
    public String toString() {
        return "Twitter: " + this.accountName;
    }
}
