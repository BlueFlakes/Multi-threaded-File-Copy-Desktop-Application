package mth.filecopier.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Scope("singleton")
@Component
public class SourcePathParser {

    public Optional<String> retrieveFileName(String name) {
        String[] paths = name.split("/");

        if (paths.length > 0) {
            int last = paths.length - 1;
            return Optional.ofNullable(paths[last]);
        }

        return Optional.empty();
    }
}
