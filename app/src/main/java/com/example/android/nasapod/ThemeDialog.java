package com.example.android.nasapod;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.android.nasapod.activities.MainActivity;

public class ThemeDialog extends DialogFragment {

        private Button mainMenuLightButton;
        private Button mainMenuDarkButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.theme_dialog, null);

        builder.setView(view);

        mainMenuLightButton = view.findViewById(R.id.light_button);
        mainMenuDarkButton = view.findViewById(R.id.dark_button);

        mainMenuLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Light", Toast.LENGTH_SHORT).show();
            }
        });

        mainMenuDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Dark", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
}
