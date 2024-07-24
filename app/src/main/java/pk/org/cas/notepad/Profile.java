package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;

public class Profile extends AppCompatActivity {
    private Uri capturedImageUri;
    ImageView ivPhoto, ivBack;
    EditText etName, etEmail;
    AppCompatButton btnSaveProfile;
    private static final int REQUEST_IMAGE_CAPTURE = 123;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivPhoto = findViewById(R.id.ivProfilePhoto);
        ivBack = findViewById(R.id.ivBackProfile);
        etName = findViewById(R.id.etProfileUsername);
        etEmail = findViewById(R.id.etProfileEmail);
        btnSaveProfile = findViewById(R.id.btnProfileSave);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
//                intent.putExtra("pic", ivPhoto);
                intent.putExtra("name", etName.getText().toString());
                intent.putExtra("email", etEmail.getText().toString());
                startActivity(intent);
                Toast.makeText(Profile.this, "Profile Saved.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            ivPhoto.setImageBitmap(data.getParcelableExtra("data"));
        }
    }
}