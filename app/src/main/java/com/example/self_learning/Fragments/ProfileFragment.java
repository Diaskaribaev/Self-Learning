package com.example.self_learning.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.self_learning.Authentication.User;
import com.example.self_learning.R;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class ProfileFragment extends Fragment {

    Button changephoto;
    ImageView photo;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    TextView Name;
    TextView Surname;
    TextView Email;
    TextView Fullname;



    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        changephoto = view.findViewById(R.id.changephoto);
        auth = FirebaseAuth.getInstance();

        Name = view.findViewById(R.id.name);
        Surname = view.findViewById(R.id.surname);
        Email = view.findViewById(R.id.email);
        Fullname = view.findViewById(R.id.fullname);
        storage =FirebaseStorage.getInstance();
        database =FirebaseDatabase.getInstance();

        photo = view.findViewById(R.id.photo);
        changephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,11);
            }
        });

        database.getReference().child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    Picasso.get().load(user.getProfilePhoto()).placeholder(R.drawable.personicon).into(photo);
                    Name.setText(user.getName());
                    Surname.setText(user.getSurname());
                    Email.setText(user.getEmail());
                    Fullname.setText(user.getName() + " " + user.getSurname());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!= null){
            Uri uri  = data.getData();
            photo.setImageURI(uri);


            final StorageReference reference = storage.getReference().child("ProfilePhoto")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(),"Profile photo saved", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(auth.getUid()).child("ProfilePhoto").setValue(uri.toString());
                        }
                    });
                }


            });

        }
    }
}