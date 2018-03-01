package mth.filecopier.controller;

import mth.filecopier.abstraction.Filter;
import mth.filecopier.exceptions.InvalidPathException;
import mth.filecopier.model.Resource;
import mth.filecopier.service.Duplicator;
import mth.filecopier.service.SourcePathParser;
import mth.filecopier.service.filters.FileFilterFactory;
import mth.filecopier.service.filters.FileFilterOptions;
import mth.filecopier.view.FileCopierView;
import org.springframework.stereotype.Controller;

import java.util.Optional;

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


            try {
                String source = this.fileCopierView.askInputSource();

                String destination = getDestination(source);


                String yesOrNo = this.fileCopierView.askCopyOrOverWrite();

                FileFilterOptions option = FileFilterOptions.getOptionByIdentity(yesOrNo);

                Filter<Resource> currentFilter = FileFilterFactory.createFileFilter(option);

                Resource currentResource = new Resource(source, destination);
                Duplicator duplicator = new Duplicator(currentResource, currentFilter);

                Thread thread = new Thread(duplicator);
                thread.start();

            } catch (Exception e) { e.printStackTrace();}
        }
    }

    private String getDestination(String source) throws InvalidPathException {
        String destination = this.fileCopierView.askInputDestination();

        String path = this.sourcePathParser.retrieveFileName(source)
                          .orElseThrow(InvalidPathException::new);

        return destination + path;
    }
}
