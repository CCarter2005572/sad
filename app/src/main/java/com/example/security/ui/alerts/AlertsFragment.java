package com.example.security.ui.alerts;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.security.R;
import com.example.security.databinding.FragmentAlertsBinding;

public class AlertsFragment extends Fragment {

    private MediaPlayer player; // MediaPlayer instance for playing audio
    private FragmentAlertsBinding binding; // View binding for the fragment

    // Fragment's view creation
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // ViewModel setup
        AlertsViewModel alertsViewModel =
                new ViewModelProvider(this).get(AlertsViewModel.class);

        // Inflate the binding and get the root view
        binding = FragmentAlertsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAlerts;
        // Set ViewModel's text data to the TextView
        alertsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Play button setup and onClick listener
        Button playButton = root.findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(); // Call play method on button click
            }
        });

        // Pause button setup and onClick listener
        Button pauseButton = root.findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause(); // Call pause method on button click
            }
        });

        // Stop button setup and onClick listener
        Button stopButton = root.findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer(); // Call stopPlayer method on button click
            }
        });

        return root; // Return the root view
    }

    // Method to start playing the audio
    private void play() {
        if (player == null) {
            // Initialize the MediaPlayer with the song
            player = MediaPlayer.create(requireContext(), R.raw.song);
            // Set a listener for when the song completes playing
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer(); // Call stopPlayer method when song completes
                }
            });
        }
        player.start(); // Start playing the song
    }

    // Method to pause the audio
    private void pause() {
        if (player != null) {
            player.pause(); // Pause the currently playing song
        }
    }

    // Method to stop and release the MediaPlayer
    private void stopPlayer() {
        if (player != null) {
            player.stop(); // Stop the MediaPlayer
            player.release(); // Release the resources
            player = null; // Set MediaPlayer instance to null
            Toast.makeText(requireContext(), "Media player released", Toast.LENGTH_SHORT).show(); // Show a toast message
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlayer(); // Stop and release MediaPlayer on Fragment's onStop
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopPlayer(); // Stop and release MediaPlayer on Fragment's onDestroyView
        binding = null; // Set binding to null to avoid memory leaks
    }
}