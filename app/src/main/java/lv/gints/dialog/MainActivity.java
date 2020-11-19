package lv.gints.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList selectedItems = new ArrayList();
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToSecond = (Button) findViewById(R.id.Go2nd);
        goToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        Button dialog = (Button) findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dialog_title);
                builder.setMultiChoiceItems(R.array.gints_array,null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        String checked = getResources().getStringArray(R.array.gints_array)[which];
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(which);
                            if (toast != null) toast.cancel();
                            toast = Toast.makeText(MainActivity.this, checked + " checked", Toast.LENGTH_SHORT);
                            toast.show();
                        } else if (selectedItems.contains(which)) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(Integer.valueOf(which));
                            if (toast != null) toast.cancel();
                            toast = toast.makeText(MainActivity.this, checked + " unchecked", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.setNegativeButton("Calcel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (toast != null) toast.cancel();
                                toast = toast.makeText(MainActivity.this, "You closed dialog", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                final AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean wantToCloseDialog = false;
                        if (toast != null) toast.cancel();
                        toast = toast.makeText(MainActivity.this, "You clicked OK", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
    }
}