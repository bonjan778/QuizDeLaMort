package com.example.ocrexos.ui.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ocrexos.R;
import com.example.ocrexos.data.Question;
import com.example.ocrexos.databinding.FragmentQuizBinding;
import com.example.ocrexos.injection.ViewModelFactory;
import com.example.ocrexos.ui.welcome.WelcomeFragment;

import java.util.Arrays;
import java.util.List;

public class QuizFragment extends Fragment {
    private QuizViewModel viewModel;
    private FragmentQuizBinding binding;
    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,
                ViewModelFactory.getInstance()).get(QuizViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.startQuiz();
        viewModel.currentQuestion.observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                updateQuestion(question);
            }
        });

        binding.answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnswer(binding.answerA, 0);
            }
        });

        binding.answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnswer(binding.answerB, 1);
            }
        });

        binding.answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnswer(binding.answerC, 2);
            }
        });

        binding.answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnswer(binding.answerD, 3);
            }
        });

        viewModel.isLastQuestion.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLastQuestion) {
                if (isLastQuestion) {
                    binding.next.setText("Finish");
                } else {
                    binding.next.setText("Next");
                }
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isLastQuestion = viewModel.isLastQuestion.getValue();
                if (isLastQuestion != null && isLastQuestion) {
                    displayResultDialog();
                } else {
                    viewModel.nextQuestion();
                    resetQuestion();
                }
            }
        });
    }


    private void updateQuestion(Question question) {
        binding.question.setText(question.getQuestion());
        binding.answerA.setText(question.getChoiceList().get(0));
        binding.answerB.setText(question.getChoiceList().get(1));
        binding.answerC.setText(question.getChoiceList().get(2));
        binding.answerD.setText(question.getChoiceList().get(3));
    }

    private void updateAnswer(Button button, Integer index) {
        showAnswerValidity(button, index);
        enableAllAnswers(false);
        binding.next.setVisibility(View.VISIBLE);
    }


    private void showAnswerValidity(Button button, Integer index) {
        Boolean isValid = viewModel.isAnwserValid(index);
        if (isValid) {
            button.setBackgroundColor(Color.parseColor("#388e3c")); // vert foncé
            binding.goodAnswer.setText("Good Answer !");
        } else {
            button.setBackgroundColor(Color.RED);
            binding.goodAnswer.setText("Bad Answer :(");
        }
        binding.goodAnswer.setVisibility(View.VISIBLE);
    }

    private void enableAllAnswers(Boolean enable) {
        List<Button> allAnswers = Arrays.asList(binding.answerA, binding.answerB, binding.answerC, binding.answerD);
        allAnswers.forEach( answer -> { answer.setEnabled(enable);
        });
    }

    public void resetQuestion(){
        List<Button> allAnswers = Arrays.asList(binding.answerA, binding.answerB, binding.answerC, binding.answerD);
        allAnswers.forEach( answer -> { answer.setBackgroundColor(Color.parseColor("#6200EE"));
        });
        binding.goodAnswer.setVisibility(View.INVISIBLE);
        enableAllAnswers(true);
    }

    private void displayResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Finished !");
        Integer score = viewModel.score.getValue();
        builder.setMessage("Your final score is " + score);
        builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                goToWelcomeFragment();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void goToWelcomeFragment() {
        WelcomeFragment welcomeFragment = WelcomeFragment.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, welcomeFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Nettoyer le binding
    }

}