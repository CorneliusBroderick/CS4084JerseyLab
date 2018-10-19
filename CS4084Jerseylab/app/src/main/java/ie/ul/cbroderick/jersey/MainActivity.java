package ie.ul.cbroderick.jersey;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mNameTextView, mNumberTextView;
    private Jersey mCurrentJersey;
    private Jersey mClearedJersey;


    // TODO: mColour boolean GREEN PURPLE true/false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameTextView = findViewById(R.id.name_text);
        mNumberTextView = findViewById(R.id.number_text);


        // Boilerplate code. Don't mess with it.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addJersey();
            }
        });
    }

    private void addJersey() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Customize the dialog for needs.
//        builder.setTitle("My title");
//        builder.setMessage("Hello");
//        builder.setPositiveButton("OK", null);
//        builder.setNegativeButton("Cancel", null);

        View view = getLayoutInflater().inflate(R.layout.dialog_add, null, false);
        builder.setView(view);

        final EditText namedEditText = view.findViewById(R.id.edit_name_dialog);
        final EditText numberEditText = view.findViewById(R.id.edit_number_dialog);

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = namedEditText.getText().toString();
                int newNumber = Integer.parseInt(numberEditText.getText().toString());

                mCurrentJersey = new Jersey(name, newNumber);

            }
        });
        builder.create().show();
    }

    private void showCurrentItem() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Boilerplate code. Don't mess with it.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // TODO: Later worry about menus.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));

            return true;
        }

        if (id ==R.id.action_reset) {
            mClearedJersey = mCurrentJersey;
            mCurrentJersey = new Jersey();
            showCurrentItem();
            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator_layout),
                    "Item cleared", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentJersey = mClearedJersey;
                    showCurrentItem();
                    Snackbar.make(findViewById(R.id.coordinator_layout),
                            "Item restored", Snackbar.LENGTH_SHORT).show();
                }
            });
            snackbar.show();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}
