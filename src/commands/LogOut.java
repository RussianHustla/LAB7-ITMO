package commands;

import app.DBManager;
import app.UserInterface;
import collection.CollectionManager;

import java.io.File;
import java.io.IOException;

public class LogOut extends Command {

    public LogOut() {
        command = "logout";
        description = "Выйти из аккаунта";
    }

    public String getCommand() {
        return command;
    }

    @Override
    public void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ClassNotFoundException {
//        if (collection.isHasUnsavedChanges()) {
//            if (CommandsManager.getInstance().confirmExecution(userInterface,"Имеются несохраненные изменения, Вы действительно хотите завершить программу?" + '\n' + "Все несохраненные данные будут утеряны. y/n")) {
//                collection.setHasUnsavedChanges(false);
//                File temp = new File("temp.xml");
//                temp.delete();
//                System.exit(0);
//            }
//        } else {
//            if (CommandsManager.getInstance().confirmExecution(userInterface, "Вы действительно хотите завершить программу? y/n")) {
//                File temp = new File("temp.xml");
//                temp.delete();
//                System.exit(0);
//            }
//
//        }

    }
}