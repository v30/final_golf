package za.co.finalgolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import za.co.finalgolf.helper_classes.DBHelper;

import static za.co.finalgolf.helper_classes.JavaConstants.HOLES;
import static za.co.finalgolf.helper_classes.JavaConstants.ROUNDS;
import static za.co.finalgolf.helper_classes.JavaConstants.SHOTS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTables();
//        dropTables();
    }

    private void dropTables() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.dropTables(db, new String[] {ROUNDS, HOLES, SHOTS});
    }

    private void createTables() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.roundTableCreate(db);
        dbHelper.holeTableCreate(db);
        dbHelper.shotTableCreate(db);

    }

    public void newRound(View view) {
        Intent intent = new Intent(this, ActivityRoundDetails.class);
        startActivity(intent);
    }
}
