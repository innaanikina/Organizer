package bot.commands.organizer;

import java.io.Serializable;

public enum Flag implements Serializable {
    COMPLETED(":white_check_mark:", "Выполнено"),
    DEADLINE_IS_COMING(":sos:", "Скоро дедлайн"),
    IN_PROGRESS(":thinking:", "В процессе"),
    FAILED(":skull_crossbones:", "Потрачено"),
    NO_CHECK(":white_circle:", "Без дедлайна");
    private String emoji;
    private String name;

    Flag(String emoji, String name) {
        this.emoji = emoji;
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getName() {
        return name;
    }
}
