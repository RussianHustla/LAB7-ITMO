package commands;

import app.DBManager;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Remove_first extends Command {

    public Remove_first() {
        command = "remove_first";
        description = "Удалить первый элемент из коллекции";
    }
    @Override
    public void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ClassNotFoundException, ParserConfigurationException, SQLException {
//        if (CommandsManager.getInstance().confirmExecution(userInterface,"Вы действительно хотите удалить первый элемент из коллекции? y/n")) {
//            try {
//                collection.removeByIndex(0);
//                System.out.println("Первый элемент удален из коллекции");
//                collection.HasUnsavedChanges();
//            } catch (IndexOutOfBoundsException | ParserConfigurationException e) {
//                System.err.println("Коллекция пуста");
//            }
//        }

        if (collection.size() > 0) {
            Flat flat = collection.getFlatByIndex(0);
            if (flat.getAuthor() == getUser().getId()) {
                dbManager.remove_by_id(flat.getId());
                collection.loadCollection(dbManager.getCollection());
                userInterface.send("Первый элемент удален из коллекции");
            } else {
                userInterface.send("Элемент не удален. Вы не являетесь автором данного элемента");
            }
        } else {
            userInterface.send("Коллекция пуста");
        }
//        try {
//            collection.removeByIndex(0);
//            userInterface.send("Первый элемент удален из коллекции");
//            collection.HasUnsavedChanges();
//        } catch (IndexOutOfBoundsException | ParserConfigurationException e) {
//            System.err.println("Коллекция пуста");
//            userInterface.send("Коллекция пуста");
//        }
//        collection.save();

    }
}
