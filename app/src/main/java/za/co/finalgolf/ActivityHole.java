package za.co.finalgolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import za.co.finalgolf.helper_classes.Util;

import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_ID;
import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_NUMBER;
import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_PAR;
import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_STROKE;
import static za.co.finalgolf.helper_classes.JavaConstants.ROUND_ID;

public class ActivityHole extends AppCompatActivity {
    public static EditText editPar, editStroke;
    Button btnStartHole;
    int holeNumber = 1;
    String roundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole);
        Intent roundIntent = getIntent();
        roundId = roundIntent.getStringExtra(ROUND_ID);
        initResources();
    }

    public void initResources() {
        editPar = findViewById(R.id.edit_par);
        editStroke = findViewById(R.id.edit_stroke);
        btnStartHole = findViewById(R.id.btn_start_hole);
    }

    public void playHole(View view) {
        Intent intent = new Intent(this, ActivityShot.class);
        intent.putExtra(ROUND_ID, roundId);
        intent.putExtra(HOLE_ID, Util.generateGuid());
        intent.putExtra(HOLE_NUMBER, holeNumber);
        intent.putExtra(HOLE_PAR, Integer.parseInt(editPar.getText().toString()));
        intent.putExtra(HOLE_STROKE, Integer.parseInt(editStroke.getText().toString()));
        holeNumber++;
        if (holeNumber > 18) {
            btnStartHole.setVisibility(View.GONE);
            endRound();
        }
        startActivity(intent);
    }

    private void endRound() {
        System.out.println("ENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDENDEND");
    }
}
