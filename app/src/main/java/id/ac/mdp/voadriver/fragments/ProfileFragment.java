package id.ac.mdp.voadriver.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ac.mdp.voadriver.activities.EditProfilActivity;
import id.ac.mdp.voadriver.activities.LoginActivity;
import id.ac.mdp.voadriver.models.User;
import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.utils.Utilities;

/*
 * Created by Tengku Kevin on 3/24/2017.
 */

public class ProfileFragment extends Fragment {
    CircleImageView circleImageViewUserProfileicture;

    public ProfileFragment(){
        // Constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*INITIALIZE*/
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_profile);
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        circleImageViewUserProfileicture = (CircleImageView) view.findViewById(R.id.ivUserProfilePicture);
        circleImageViewUserProfileicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        Utilities utilities=new Utilities();
        TextView tvName=(TextView)view.findViewById(R.id.tv_user_name);
        TextView tvPosition=(TextView)view.findViewById(R.id.tv_user_position);
        //TextView tvEmail=(TextView)view.findViewById(R.id.tv_email_content);
        TextView tvPhoneNumber=(TextView)view.findViewById(R.id.tv_contact_content);

        User currentUser=utilities.getUser(getContext());
        tvName.setText(currentUser.getName());
        tvPosition.setText(currentUser.getPosition());
        //tvEmail.setText(currentUser.getUserId());
        tvPhoneNumber.setText(currentUser.getPhoneNumber());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_edit_profil || super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.action_edit_profil){
            startActivity(new Intent(getContext(), EditProfilActivity.class));
        }else if(id==R.id.action_sign_out){
            Utilities util=new Utilities();
            util.clearUser(getContext());
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        final int SELECT_PICTURE = 1;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Tambah Foto!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,
                            "Select Picture"), SELECT_PICTURE);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}