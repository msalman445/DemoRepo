package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteNotes extends AppCompatActivity {
    ImageView ivBack;
    RecyclerView rvFav;
    NotesAdapter notesAdapter;
    OpenNote openNoteRef = new OpenNote();

    public static List<Notes> favNotesList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_notes);
        rvFav = findViewById(R.id.rvFav);
        ivBack = findViewById(R.id.ivBackFav);


        notesAdapter = new NotesAdapter(favNotesList);
        rvFav.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvFav.setAdapter(notesAdapter);
        rvFav.setHasFixedSize(true);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





    }
}