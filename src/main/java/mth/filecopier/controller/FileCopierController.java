package mth.filecopier.controller;

import mth.filecopier.abstraction.Filter;
import mth.filecopier.exceptions.ApplicationException;
import mth.filecopier.exceptions.InvalidChoiceException;
import mth.filecopier.exceptions.InvalidPathException;
import mth.filecopier.model.Resource;
import mth.filecopier.service.DestinationPathParser;
import mth.filecopier.service.Duplicator;
import mth.filecopier.service.SourcePathParser;
import mth.filecopier.service.filters.FileFilterFactory;
import mth.filecopier.service.filters.FileFilterOptions;
import mth.filecopier.view.FileCopierView;
import org.springframework.stereotype.Controller;


@Controller
public class FileCopierController {

    private FileCopierView fileCopierView;
    private DestinationPathParser destinationPathParser;
    private SourcePathParser sourcePathParser;

    public FileCopierController(FileCopierView fileCopierView,
                                SourcePathParser sourcePathParser,
                                DestinationPathParser destinationPathParser) {

        this.fileCopierView = fileCopierView;
        this.sourcePathParser = sourcePathParser;
        this.destinationPathParser = destinationPathParser;
    }

    public void runController() {

        while (true) {
            try {
                Duplicator duplicator = getDuplicator();

                Thread thread = new Thread(duplicator);
                thread.start();
            } catch (ApplicationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Duplicator getDuplicator() throws ApplicationException {

        String source = this.fileCopierView.askInputSource();
        String destination = this.fileCopierView.askInputDestination();
        destination = getDestination(source, destination);

        String yesOrNo = this.fileCopierView.askCopyOrOverWrite();
        Filter<Resource> currentFilter = getCurrentFilter(yesOrNo);

        Resource currentResource = new Resource(source, destination);
        Duplicator duplicator = new Duplicator(currentResource, currentFilter);

        return duplicator;
    }

    private String getDestination(String source, String destination) throws InvalidPathException {
        destination = this.destinationPathParser.retrieveFileDestination(destination);
        String path = this.sourcePathParser.retrieveFileName(source)
                                           .orElseThrow(() -> new InvalidPathException("Invalid path delivered"));


        return destination + path;
    }

    private Filter<Resource> getCurrentFilter(String yesOrNo) throws InvalidChoiceException {
        FileFilterOptions option = FileFilterOptions.getOptionByIdentity(yesOrNo);
        return FileFilterFactory.createFileFilter(option);
    }
}