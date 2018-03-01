package mth.filecopier.controller;

import mth.filecopier.abstraction.Filter;
import mth.filecopier.exceptions.InvalidChoiceException;
import mth.filecopier.exceptions.InvalidPathException;
import mth.filecopier.model.Resource;
import mth.filecopier.service.Duplicator;
import mth.filecopier.service.SourcePathParser;
import mth.filecopier.service.filters.FileFilterFactory;
import mth.filecopier.service.filters.FileFilterOptions;
import mth.filecopier.view.FileCopierView;
import org.springframework.stereotype.Controller;


@Controller
public class FileCopierController {

    private FileCopierView fileCopierView;
    private SourcePathParser sourcePathParser;

    public FileCopierController(FileCopierView fileCopierView,
                                SourcePathParser sourcePathParser) {

        this.fileCopierView = fileCopierView;
        this.sourcePathParser = sourcePathParser;
    }

    public void runController() {

        while (true) {

            Duplicator duplicator = getDuplicator();

            Thread thread = new Thread(duplicator);
            thread.start();
        }
    }

    private Duplicator getDuplicator() {

        String source = this.fileCopierView.askInputSource();
        String destination = this.fileCopierView.askInputDestination();
        destination = getDestination(source, destination);

        String yesOrNo = this.fileCopierView.askCopyOrOverWrite();
        Filter<Resource> currentFilter = getCurrentFilter(yesOrNo);

        Resource currentResource = new Resource(source, destination);
        Duplicator duplicator = new Duplicator(currentResource, currentFilter);

        return duplicator;
    }

    private String getDestination(String source, String destination) {

        String path = "";

        try {
            path = this.sourcePathParser.retrieveFileName(source)
                    .orElseThrow(InvalidPathException::new);
        } catch (InvalidPathException e) {
            e.printStackTrace();
        }

        return destination + path;
    }

    private Filter<Resource> getCurrentFilter(String yesOrNo) {
        FileFilterOptions option = FileFilterOptions.getOptionOrDefault(yesOrNo, FileFilterOptions.NO_FILTER);
        return FileFilterFactory.createFileFilter(option);
    }
}