package mth.filecopier.controller;

import mth.filecopier.abstraction.Filter;
import mth.filecopier.model.Resource;
import mth.filecopier.service.Duplicator;
import mth.filecopier.service.filters.FileFilterFactory;
import mth.filecopier.service.filters.FileFilterOptions;
import mth.filecopier.view.View;

public class FileCopierController {

    public void runController() {

        while (true) {

            String source = View.askInputsource();
            String destination = View.askInputDestination();
            String yesOrNo = View.askCopyOrOverWrite();

            FileFilterOptions option = null;
            try {
                option = FileFilterOptions.getOptionByIdentity(yesOrNo);

            } catch (Exception e) { e.printStackTrace();}

            Filter<Resource> currentFilter = FileFilterFactory.createFileFiter(option);

            Resource currentResource = new Resource(source, destination);
            Duplicator duplicator = new Duplicator(currentResource, currentFilter);

            Thread thread = new Thread(duplicator);
            thread.start();

        }
    }
}
