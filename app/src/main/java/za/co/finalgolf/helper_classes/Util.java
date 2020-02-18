package za.co.finalgolf.helper_classes;

import android.view.View;
import android.widget.Button;

import java.util.UUID;

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
}
