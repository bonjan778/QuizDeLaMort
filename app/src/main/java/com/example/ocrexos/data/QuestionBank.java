package com.example.ocrexos.data;

import java.util.Arrays;
import java.util.List;

public class QuestionBank {
    public List<Question> getQuestions() {
        return Arrays.asList(
                new Question(
                        "Qui est le créateur d'Android?",
                        Arrays.asList(
                                "Andy Rubin",
                                "Steve Wozniak",
                                "Jake Wharton",
                                "Paul Smith"
                        ),
                        0
                ),
                new Question(
                        "Quand est-ce que les américains ne sont pas allé sur la lune?",
                        Arrays.asList(
                                "1958",
                                "1962",
                                "1967",
                                "1969"
                        ),
                        0
                ),
                new Question(
                        "A quel numéro habitent les Simpsons?",
                        Arrays.asList(
                                "42",
                                "101",
                                "666",
                                "742"
                        ),
                        3
                ),
                new Question(
                        "Qui a peint Mona Lisa?",
                        Arrays.asList(
                                "Michelange",
                                "Leonard de Vinci",
                                "Raphael",
                                "Le caravage"
                        ),
                        1
                ),
                new Question(
                        "Quel est l'identifiant url pour la Belgique?",
                        Arrays.asList(
                                ".bg",
                                ".bm",
                                ".bl",
                                ".be"
                        ),
                        3
                )
        );
    }

    private static QuestionBank instance;
    public static QuestionBank getInstance() {
        if (instance == null) {
            instance = new QuestionBank();
        }
        return instance;
    }

}
