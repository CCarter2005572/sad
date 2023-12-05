package com.example.security.ui.camera;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.provider.MediaStore;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.security.databinding.FragmentCameraBinding;

public class CameraFragment extends Fragment {

    private FragmentCameraBinding binding;
    private static final int CAMERA_REQUEST_CODE = 100; // Request code for camera

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CameraViewModel cameraViewModel =
                new ViewModelProvider(this).get(CameraViewModel.class);

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnOpenCamera = binding.btnOpenCamera;
        // Set onClick listener for the button to open the camera
        btnOpenCamera.setOnClickListener(view -> openCamera());

        final TextView textView = binding.textCamera;
        // Observe the ViewModel's text data and set it to the TextView
        cameraViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root; // Return the root view
    }

    private void openCamera() {
        // Check if the device has a camera feature
        if (requireActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            // Create an intent to open the camera
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Check if there's a camera app available to handle the intent
            if (cameraIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                // Start the camera activity and expect a result with a specific request code
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            } else {
                // Display a message if no camera app is found
                Toast.makeText(requireContext(), "No camera app found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Display a message if the device doesn't have a camera feature
            Toast.makeText(requireContext(), "Device doesn't have a camera", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Set binding to null to avoid memory leaks
    }
}