package examples.guilherme.chat_android.model;

/**
 * Created by Guilherme on 27/04/2017.
 */

public class ChatModel {

    private String text;

    public ChatModel() {
    }

    public ChatModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
