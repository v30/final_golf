package za.co.finalgolf.helper_classes;

import android.view.View;
import android.widget.Button;

import java.util.UUID;

import static za.co.finalgolf.helper_classes.JavaConstants.PLAYER_HANDICAP;

public class Util {
    public static String generateGuid() {
        return UUID.randomUUID().toString();
    }

    public static void setActive(Button activeButton, int color) {
        activeButton.setBackgroundColor(color);
    }

    public static void setVisible(Button[] buttons, boolean visible) {
        for (Button button : buttons) {
            if (visible)
                button.setVisibility(View.GONE);
            else
                button.setVisibility(View.VISIBLE);
        }
    }

    public static int calculateMedalScore(int shotNumber, int par, int stroke) {
        int result = -1000;
        if (stroke > 0) {
            if (stroke <= PLAYER_HANDICAP) {
                if (shotNumber == par - 2)
                    result = -3;
                else if (shotNumber == par - 1)
                    result = -2;
                else if (shotNumber == par)
                    result = -1;
                else if (shotNumber == par + 1)
                    result = 0;
                else if (shotNumber == par + 2)
                    result = 1;
                else if (shotNumber >= par + 3)
                    result = 2;
            }
            else {
                if (shotNumber == par - 2)
                    result = -2;
                else if (shotNumber == par - 1)
                    result = -1;
                else if (shotNumber == par)
                    result = 0;
                else if (shotNumber == par + 1)
                    result = 1;
                else if (shotNumber >= par + 2)
                    result = 2;
            }
        }
        return result;
    }

    public static int calculateStableford(int shotNumber, int par, int stroke) {
        int result = -1000;
        if (stroke > 0) {
            if (stroke <= PLAYER_HANDICAP) {
                if (shotNumber == par - 2)
                    result = 5;
                else if (shotNumber == par - 1)
                    result = 4;
                else if (shotNumber == par)
                    result = 3;
                else if (shotNumber == par + 1)
                    result = 2;
                else if (shotNumber == par + 2)
                    result = 1;
                else if (shotNumber >= par + 3)
                    result = 0;
            }
            else {
                if (shotNumber == par - 2)
                    result = 4;
                else if (shotNumber == par - 1)
                    result = 3;
                else if (shotNumber == par)
                    result = 2;
                else if (shotNumber == par + 1)
                    result = 1;
                else if (shotNumber >= par + 2)
                    result = 0;
            }
        }
        return result;
    }
}
