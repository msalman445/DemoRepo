package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NavDrawerHeader extends AppCompatActivity {
    ImageView ivPicture;
    TextView tvUserName, tvEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer_header);
        ivPicture = findViewById(R.id.ivPerson);
        tvUserName = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        tvUserName.setText(name);
        tvEmail.setText(email);

    }
}