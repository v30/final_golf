package za.co.finalgolf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import za.co.finalgolf.helper_classes.Util;

import static za.co.finalgolf.helper_classes.JavaConstants.ROUND_ID;

public class AcrivityRoundDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acrivity_round_details);
    }

    public void startRound(View view) {
        String roundId = Util.generateGuid();

        Intent intent = new Intent(this, ActivityHole.class);
        intent.putExtra(ROUND_ID, roundId);
        startActivity(intent);
    }
}
