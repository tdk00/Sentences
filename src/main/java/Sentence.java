import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sentence {

    public static void main(String[] args) throws IOException {

        final Map<String, List<String>> subj_verb=new HashMap<>();
        Map<String,List<String>> verb_obj=new HashMap<>();
        Path path1= Paths.get("./data","subj_verb");
        List<String> lines = Files.readAllLines(path1, StandardCharsets.UTF_8);
        for (String line : lines) {
            String[] str= line.split(":");
            System.out.println(str[0]);
            String[] commas= str[1].split(",");
            subj_verb.put(str[0], Arrays.asList(commas));
        }
        Path path2=Paths.get("./data","verb_obj");
        List<String> lines2 = Files.readAllLines(path2, StandardCharsets.UTF_8);
        for (String line : lines2) {
            String[] verbs= line.split(":");
            System.out.println(verbs[0]);
            String[] commas= verbs[1].split(",");
            verb_obj.put(verbs[0], Arrays.asList(commas));
        }

        System.out.println(subj_verb.entrySet());
        List<Sentence> outcome =
                subj_verb.keySet().stream().flatMap(subj ->
                        subj_verb.get(subj).stream().flatMap(verb ->
                                verb_obj.get(verb).stream().map(obj ->
                                   new Concatenation(subj,verb,obj)
                                )
                        )
                ).collect(Collectors.toList());
        outcome.forEach(s -> System.out.println(s));


    }



}
