package mth.filecopier;

import mth.filecopier.controller.FileCopierController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static final ClassPathXmlApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) {
        FileCopierController fileCopierController = ctx.getBean(FileCopierController.class);
        fileCopierController.runController();
    }
}
