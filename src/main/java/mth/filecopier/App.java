package mth.filecopier;

import mth.filecopier.controller.FileCopierController;

public class App {
    public static void main(String[] args) {

        FileCopierController copierController = new FileCopierController();
        copierController.runController();
    }
}
