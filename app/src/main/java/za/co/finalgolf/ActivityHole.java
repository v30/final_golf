package za.co.finalgolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import za.co.finalgolf.helper_classes.Util;

import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_ID;
import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_NUMBER;
import static za.co.finalgolf.helper_classes.JavaConstants.ROUND_ID;

public class ActivityHole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole);
    }

    public void playHole(View view) {
        Button btnClicked = findViewById(view.getId());
        int holeNumber = Integer.parseInt(btnClicked.getText().toString());
        String holeId = Util.generateGuid();

        Intent roundIntent = getIntent();
        String roundId = roundIntent.getStringExtra(ROUND_ID);
        Intent intent = new Intent(this, ActivityShot.class);
        intent.putExtra(HOLE_ID, holeId);
        intent.putExtra(ROUND_ID, roundId);
        intent.putExtra(HOLE_NUMBER, holeNumber);
        startActivity(intent);
    }
}
