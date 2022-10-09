package com.example.schedulebud.main_activity_fragments.schedule;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.schedulebud.R;

public class EditEventDialogFragment extends DialogFragment {
    private TextView eventDetailsTextView;
    private ImageButton editEventBtn;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogfragment_edit_event, null);

        Bundle bundle = getArguments();
        String eventDetails = bundle.getString("eventDetails","");

        eventDetailsTextView = v.findViewById(R.id.eventDetailsTextView);
        editEventBtn = v.findViewById(R.id.editEventBtn);

        eventDetailsTextView.setText(eventDetails);
        editEventBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), EditEventActivity.class);
            intent.putExtra("eventDetails", eventDetails);
            startActivity(intent);
            dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        return builder.create();
    }
}
