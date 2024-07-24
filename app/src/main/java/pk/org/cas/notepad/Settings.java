package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {
    RadioGroup radioGroupFirst, radioGroupSecond;
    ImageView ivBack;
    ConstraintLayout settings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        radioGroupFirst = findViewById(R.id.RadioGroupFirst);
        radioGroupSecond = findViewById(R.id.RadioGroupSecond);
        ivBack = findViewById(R.id.ivBackSettings);
        settings = findViewById(R.id.mainSettings);


        radioGroupFirst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.rbLight){
                    settings.setBackgroundResource(R.color.light);
//                    Toast.makeText(Settings.this, "Light", Toast.LENGTH_SHORT).show();
                }
                if(checkedId == R.id.rbDark){
                    settings.setBackgroundResource(R.color.dark);
//                    Toast.makeText(Settings.this, "Dark", Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioGroupSecond.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.rbNormal){

//                    Toast.makeText(Settings.this, "Normal", Toast.LENGTH_SHORT).show();
                }
                if(checkedId == R.id.rbBold){

//                    Toast.makeText(Settings.this, "Bold", Toast.LENGTH_SHORT).show();
                }
                if(checkedId == R.id.rbItalic){
//                    Toast.makeText(Settings.this, "Italic", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}