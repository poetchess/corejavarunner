package streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poetchess on 9/7/17.
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("../gutenberg/alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;

        // The loop specifies exactly how the computation should work, forgoes
        // any chances of optimization.
        for (String w : words) {
            if (w.length() > 12)
                count++;
        }
        System.out.println(count);

        // Streams follow the 'what, not how' principle.
        // We don't specify in which order, or in which thread, this should happen.

        // Typical workflow when working with streams, setting up a pipeline of
        // operations in three stages:
        // 1. Create a stream
        // 2. Specify intermediate operations for transforming the initial stream
        //    into others, possibly in multiple steps.
        // 3. Apply a terminal operation to produce a result. This operation forces
        //    the execution of the lazy operations that precedes it. Afterwards,
        //    the stream can no longer be used.
        count = words.stream().filter(w->w.length() > 12).count();
        System.out.println(count);

        count = words.parallelStream().filter(w->w.length() > 12).count();
        System.out.println(count);
    }
}
