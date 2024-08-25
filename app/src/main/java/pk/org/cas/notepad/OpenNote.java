package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OpenNote extends AppCompatActivity {
    ImageView ivOpenBack, ivFav, ivDel, ivShare;
    ExtendedFloatingActionButton fab_Update;
    EditText etOpen_Title, etOpen_Note;
    DB db = DB.getInstance(this);
    boolean isNotFavourite = true;
    int a = 5;


    List<Notes> favouriteNotesList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_note);
        ivOpenBack = findViewById(R.id.ivOpen_Back);
        ivFav = findViewById(R.id.ivFav);
        ivDel = findViewById(R.id.ivDel);
        ivShare = findViewById(R.id.ivShare);
        fab_Update = findViewById(R.id.fab_Update);
        etOpen_Title = findViewById(R.id.etOpenTitle);
        etOpen_Note = findViewById(R.id.etOpenNote);

        List<Notes> notes = db.fetchNotes();
        NotesAdapter notesAdapter = new NotesAdapter(notes);

        Intent intent = getIntent();
        int position = intent.getIntExtra("Position",-1);
        String title = intent.getStringExtra("Title");
        String note = intent.getStringExtra("Note");

        etOpen_Title.setText(title);
        etOpen_Note.setText(note);

        // Back Button
        ivOpenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Delete Button
        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertBox();
            }
            private void openAlertBox() {
                AlertDialog.Builder builder = new AlertDialog.Builder(OpenNote.this);
                builder.setTitle("Delete Note?");
                builder.setMessage("Are you sure you want to delete your note?");
                builder.setIcon(R.drawable.delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(db.deleteNote(notes.get(position).getNoteId())){
                            notes.remove(position);
                            notesAdapter.notifyItemRemoved(position);
                            finish();
                            Toast.makeText(OpenNote.this, "Note is Deleted.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Update fab Button
        fab_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = (notes.get(position).getNoteId());
                String title = String.valueOf(etOpen_Title.getText());
                String note = String.valueOf(etOpen_Note.getText());
                Notes notes1 = new Notes(id,title, note);
                if (db.updateNote(notes1)){
                    notesAdapter.notifyDataSetChanged();
                    notesAdapter.notifyItemChanged(position);
                    DateFormat df = new SimpleDateFormat("HH:mm:ss a, dd/MM/yyyy", Locale.getDefault());
                    String currentDateAndTime = df.format(new Date());
                    notes1.setDate(currentDateAndTime);
                    finish();
                    Toast.makeText(OpenNote.this, "Note Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Favourite Note.
        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNotFavourite){
                    ivFav.setImageResource(R.drawable.favourite);
                    int id = (notes.get(position).getNoteId());
                    String title = String.valueOf(etOpen_Title.getText());
                    String note = String.valueOf(etOpen_Note.getText());
                    Notes notes1 = new Notes(id,title, note);
                    favouriteNotesList.add(notes1);
                    db.deleteNote(notes.get(position).getNoteId());
                    Toast.makeText(OpenNote.this, "Favourite", Toast.LENGTH_SHORT).show();
                    isNotFavourite = false;
                }else {
                    ivFav.setImageResource(R.drawable.favourites);
                    Toast.makeText(OpenNote.this, "Size of Fav List : "+favouriteNotesList.size(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(OpenNote.this, "Not Favourite", Toast.LENGTH_SHORT).show();
                    isNotFavourite = true;
                }
            }
        });


        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, etOpen_Title.getText().toString()+"\n\n"+etOpen_Note.getText().toString());
                shareIntent.setType("text/plane");
                shareIntent = Intent.createChooser(shareIntent, "Share to : ");
                startActivity(shareIntent);
            }
        });
    }
}
