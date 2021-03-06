package seedu.planner.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import seedu.planner.Main;
import seedu.planner.model.module.Lesson;
import seedu.planner.storage.JsonSerializableLesson;

/**
 * Helper Class to import Lesson data from a JSON file retrieved from NUS Mods API.
 */
public class LessonDataImporterUtil {

    private static List<Lesson> lessons = new ArrayList<>();

    /**
     * Performs the conversion of lessons stored in a JSON into a list of {@code Lesson} objects.
     *
     * @return A list of {@code Lesson} objects
     */
    public static List<Lesson> run(String modCode, int sem) {
        Lesson lesson;
        String fileName = "";
        String finalText = "";
        int startIndex;
        int endIndex;
        try {
            fileName = "json/{0}.json".replace("{0}", modCode);
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            String text = new Scanner(Main.class.getClassLoader().getResourceAsStream(fileName),
                    "UTF-8").useDelimiter("\\A").next();
            startIndex = text.indexOf("semester\":1") + 24;
            endIndex = text.indexOf("examDate") - 2;
            if (sem == 1) {
                finalText = text.substring(startIndex, endIndex);
            } else {
                startIndex = text.indexOf("semester\":2") + 24;
                String sem2String = text.substring(startIndex);

                endIndex = sem2String.indexOf("examDate") - 2;
                finalText = sem2String.substring(0, endIndex);
            }
            System.out.println(finalText);

            Optional<JsonSerializableLesson[]> optionalLesson = JsonUtil.readJsonString(finalText,
                    JsonSerializableLesson[].class);
            if (optionalLesson.isPresent()) {
                JsonSerializableLesson[] lessonArray = optionalLesson.get();
                for (JsonSerializableLesson l : lessonArray) {
                    lesson = l.toModelType();
                    if (!lessons.contains(lesson)) {
                        lessons.add(lesson);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading file: " + fileName);
            System.err.println(e);
        }
        return lessons;
    }
}
