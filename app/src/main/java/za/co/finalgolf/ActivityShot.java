package za.co.finalgolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import za.co.finalgolf.helper_classes.DBHelper;
import za.co.finalgolf.helper_classes.Util;

import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_ID;
import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_NUMBER;
import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_PAR;
import static za.co.finalgolf.helper_classes.JavaConstants.HOLE_STROKE;
import static za.co.finalgolf.helper_classes.JavaConstants.ROUND_ID;

public class ActivityShot extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DBHelper dbHelper;
    SQLiteDatabase db;
    Spinner spnShotType, spnClubHit, spnBallFlight, spnOutcome;
    EditText editTargetDistance;
    boolean isPutt = false;
    String holeId, roundId;
    int holeNumber, par, stroke;
    String shotType;
    String clubHit;
    String ballFlight;
    String outcome;
    int targetDistance = 0;
    int shotNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        editTargetDistance = findViewById(R.id.edit_target_distance);
        Intent holeIntent = getIntent();
        holeId = holeIntent.getStringExtra(HOLE_ID);
        roundId = holeIntent.getStringExtra(ROUND_ID);
        holeNumber = holeIntent.getIntExtra(HOLE_NUMBER, 0);
        par = holeIntent.getIntExtra(HOLE_PAR, 0);
        stroke = holeIntent.getIntExtra(HOLE_STROKE, 0);
        initShotTypeSpinner();
    }

    private void initShotTypeSpinner() {
        spnShotType = findViewById(R.id.spinner_shot_type);
        spnShotType.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.shot_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnShotType.setAdapter(adapter);
    }

    private void initClubHitSpinner(boolean isPutt) {
        spnClubHit = findViewById(R.id.spinner_club_hit);
        spnClubHit.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter;
        if (!isPutt)
            adapter = ArrayAdapter.createFromResource(this,R.array.club_hit, android.R.layout.simple_spinner_item);
        else
            adapter = ArrayAdapter.createFromResource(this,R.array.club_hit_putter, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnClubHit.setAdapter(adapter);
    }

    private void initBallFlightSpinner(boolean isPutt) {
        spnBallFlight = findViewById(R.id.spinner_ball_flight);
        spnBallFlight.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter;
        if (!isPutt)
            adapter = ArrayAdapter.createFromResource(this,R.array.ball_flight, android.R.layout.simple_spinner_item);
        else
            adapter = ArrayAdapter.createFromResource(this,R.array.putt_roll, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBallFlight.setAdapter(adapter);
    }

    private void initOutcomeSpinner(boolean isPutt) {
        spnOutcome = findViewById(R.id.spinner_outcome);
        spnOutcome.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter;
        if (!isPutt)
            adapter = ArrayAdapter.createFromResource(this,R.array.outcome, android.R.layout.simple_spinner_item);
        else
            adapter = ArrayAdapter.createFromResource(this,R.array.putt_outcome, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutcome.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int parentId = parent.getId();


        switch (parentId) {
            case R.id.spinner_shot_type:
                shotType = spnShotType.getSelectedItem().toString();
                isPutt = shotType.equalsIgnoreCase("putt");
                initClubHitSpinner(isPutt);
                break;
            case R.id.spinner_club_hit:
                clubHit = spnClubHit.getSelectedItem().toString();
                initBallFlightSpinner(isPutt);
                break;
            case R.id.spinner_ball_flight:
                ballFlight = spnBallFlight.getSelectedItem().toString();
                initOutcomeSpinner(isPutt);
                break;
            case R.id.spinner_outcome:
                outcome = spnOutcome.getSelectedItem().toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void endShot(View view) {
        if (!editTargetDistance.getText().toString().isEmpty())
            targetDistance = Integer.parseInt(editTargetDistance.getText().toString());
        saveShotToDatabase();
    }

    private void saveShotToDatabase() {
        String shotId = Util.generateGuid();
        dbHelper.insertShot(db, shotId, holeId, shotType, targetDistance, clubHit, ballFlight, outcome);

        if (outcome.equalsIgnoreCase("hole")) {
            saveHoleToDatabase();
            finish();
        }

        shotNumber++;
        editTargetDistance.setText("");
        initShotTypeSpinner();
    }

    private void saveHoleToDatabase() {
        dbHelper.insertHole(db, holeId, roundId, holeNumber, par, stroke, Util.calculateMedalScore(shotNumber, par, stroke), Util.calculateStableford(shotNumber, par, stroke), shotNumber);
    }
}
