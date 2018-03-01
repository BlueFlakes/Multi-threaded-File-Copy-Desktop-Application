package mth.filecopier.controller;

import mth.filecopier.abstraction.Filter;
import mth.filecopier.model.Resource;
import mth.filecopier.service.Duplicator;
import mth.filecopier.service.filters.FileFilterFactory;
import mth.filecopier.service.filters.FileFilterOptions;
import mth.filecopier.view.FileCopierView;

public class FileCopierController {
    private FileCopierView fileCopierView;

    public FileCopierController(FileCopierView fileCopierView) {
        this.fileCopierView = fileCopierView;
    }

    public void runController() {

        while (true) {

            String source = this.fileCopierView.askInputSource();
            String destination = this.fileCopierView.askInputDestination();
            String yesOrNo = this.fileCopierView.askCopyOrOverWrite();

            FileFilterOptions option = null;
            try {
                option = FileFilterOptions.getOptionByIdentity(yesOrNo);

            } catch (Exception e) { e.printStackTrace();}

            Filter<Resource> currentFilter = FileFilterFactory.createFileFilter(option);

            Resource currentResource = new Resource(source, destination);
            Duplicator duplicator = new Duplicator(currentResource, currentFilter);

            Thread thread = new Thread(duplicator);
            thread.start();

        }
    }
}
