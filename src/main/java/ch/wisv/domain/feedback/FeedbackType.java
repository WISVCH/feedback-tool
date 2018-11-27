package ch.wisv.domain.feedback;

import lombok.Getter;

/**
 * Created by Tom on 29/04/2017. Updated by Karim Osman
 */
public enum FeedbackType {
    Positive("fa-laugh-beam", "#8CD19D"),
    Negative("fa-frown", "#FF5254"),
    Suggestion("fa-lightbulb", "#FCB653");

    @Getter
    private final String emoji;

    @Getter
    private final String color;

    FeedbackType(String emoji, String color) {
        this.emoji = emoji;
        this.color = color;
    }
}
