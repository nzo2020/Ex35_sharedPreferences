package com.example.ex35_sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity manages user input, session persistence with SharedPreferences, and menu navigation.
 * It allows users to enter a name, track a count, reset the count, and save data upon exit.
 * Additionally, it includes an options menu for navigating to the credits screen.
 *
 * @author Noa Zohar
 * @version 1.0
 * @since 24/02/2025
 */
public class MainActivity extends AppCompatActivity {

    /**
     * EditText field for user name input.
     */
    private EditText edName;

    /**
     * TextView displaying the count value.
     */
    private TextView tvCount;

    /**
     * SharedPreferences object for storing user data persistently.
     */
    private SharedPreferences settings;

    /**
     * SharedPreferences editor for modifying stored values.
     */
    private SharedPreferences.Editor editor;

    /**
     * Counter value, tracked across app sessions.
     */
    private int count = 0;

    /**
     * User name retrieved and stored in SharedPreferences.
     */
    private String name;

    /**
     * Initializes the activity, retrieves stored data, and sets up UI elements.
     *
     * @param savedInstanceState Saved state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        tvCount = findViewById(R.id.tvCount);
        settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        editor = settings.edit();

        name = settings.getString("Name", "");
        edName.setText(name);

        count = settings.getInt("Count", 0);
        tvCount.setText(String.valueOf(count));
    }

    /**
     * Increments the counter and updates the display.
     *
     * @param view The view that triggered this method.
     */
    public void btnCount(View view) {
        count++;
        tvCount.setText(String.valueOf(count));
    }

    /**
     * Resets the counter to zero and updates the display.
     *
     * @param view The view that triggered this method.
     */
    public void btnReset(View view) {
        count = 0;
        tvCount.setText(String.valueOf(count));
    }

    /**
     * Saves the current name and count values to SharedPreferences and exits the application.
     *
     * @param view The view that triggered this method.
     */
    public void btnExit(View view) {
        editor.putString("Name", edName.getText().toString());
        editor.putInt("Count", count);
        editor.commit();
        finish();
    }

    /**
     * Inflates the options menu and adds a credits option.
     *
     * @param menu The menu to inflate.
     * @return True if the menu was successfully created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.creditsmain, menu);
        return true;
    }




    /**
     * Handles menu item selections, navigating to the credits screen if selected.
     *
     * @param item The selected menu item.
     * @return True if the selection was handled.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuCredits) {
            Intent intent = new Intent(this, mainCredits.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
