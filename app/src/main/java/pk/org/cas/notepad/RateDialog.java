package pk.org.cas.notepad;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateDialog extends Dialog {
    private int userRate = 0;

    AppCompatButton btnLater, btnRateApp;
    AppCompatRatingBar ratingBar;

    public RateDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_dialog);

//
//        ratingBar = findViewById(R.id.ratingBar);
//
//
//        btnRateApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Thanks a lot for Rating our NotePad App.", Toast.LENGTH_SHORT).show();
//                dismiss();
//            }
//        });
//
//        btnLater.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                dialog box close.
//                dismiss();
//            }
//        });
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//
//            }
//        });
//
    }
}