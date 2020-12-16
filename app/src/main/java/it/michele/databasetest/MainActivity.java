package it.michele.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView output = findViewById(R.id.output);

        SQLiteDatabase sqlite = openOrCreateDatabase("utenti", MODE_PRIVATE, null);

        sqlite.execSQL("CREATE TABLE IF NOT EXISTS users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username VARCHAR(30) NOT NULL, " +
                "accepted TINYINT NOT NULL DEFAULT 0" +
                ")");

        sqlite.execSQL("INSERT INTO users (username, accepted) VALUES ('Alberto', 1)");
        sqlite.execSQL("INSERT INTO users (username, accepted) VALUES ('Luca', 1)");
        sqlite.execSQL("INSERT INTO users (username, accepted) VALUES ('Giovanni', 1)");

        Cursor cursor = sqlite.rawQuery("SELECT * FROM users", null);

        if(cursor.moveToFirst()){
            StringBuilder builder = new StringBuilder();
            do {
                builder.append(cursor.getInt(0)).append(" - ")
                        .append(cursor.getString(1)).append(" - ")
                        .append(cursor.getInt(2) == 1).append("\n");
            } while (cursor.moveToNext());
            output.setText(builder.toString());
            System.out.println(builder.toString());
        } else {
           output.setText("La Tabella è vuota");
            System.out.println("La Tabella è vuota");
        }
        cursor.close();
        sqlite.close();
    }
}