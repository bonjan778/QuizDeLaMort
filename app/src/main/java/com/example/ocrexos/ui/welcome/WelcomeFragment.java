package com.example.ocrexos.ui.welcome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ocrexos.R;
import com.example.ocrexos.databinding.FragmentWelcomeBinding;
import com.example.ocrexos.ui.quiz.QuizFragment;

public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Alex", "fragment onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Alex", "fragment onCreateView()");
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.playButton.setEnabled(false);

        binding.usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean isEmpty = s.toString().isEmpty();
                binding.playButton.setEnabled(!isEmpty);
            }
        });

        binding.playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // The user just clicked
                Log.d("Alex", "ça clique!");
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                QuizFragment quizFragment = QuizFragment.newInstance();
                fragmentTransaction.add(R.id.fragment_container_view_tag, quizFragment);
                fragmentTransaction.commit();
            }
        });

        Log.d("Alex", "fragment onViewCreated()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Alex", "fragment onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Alex", "fragment onDetach()");
    }
}