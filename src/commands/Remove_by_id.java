package commands;

import app.DBManager;
import app.InvalidInputException;
import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Remove_by_id extends Command{

    public Remove_by_id() {
        command = "remove_by_id";
        description = "Удалить элемент из коллекции по его id";
    }

    @Override
    public void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
        String arg[] = getArgs();
        if (arg.length < 1) {
            throw new InvalidInputException("У вызываемой команды отсутствует аргумент");
        }
        int id;
        try {
            id = Integer.parseInt((String) args[0]);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("У вызываемой команды некорректный аргумент (требуется число)");
        }
//        if (CommandsManager.getInstance().confirmExecution(userInterface,"Вы действительно хотите удалить элемент из коллекции? y/n")) {
//            if(collection.removeById(id)) {
//                System.out.println("Элемент с id = " + id + " успешно удален из коллекции");
//                collection.HasUnsavedChanges();
//            } else {
//                System.err.println("Элемент с id = " + id + " отсутствует в коллекции");
//            }
//        }

        if (collection.getFlatById(id) != null) {
            //System.out.println(collection.getFlatById(id));
            if (collection.getFlatById(id).getAuthor() == getUser().getId()) {
                dbManager.remove_by_id(id);
                collection.loadCollection(dbManager.getCollection());
                System.out.println("Элемент с id = " + id + " успешно удален из коллекции");
                userInterface.send("Элемент с id = " + id + " успешно удален из коллекции");
            } else {
                //System.err.println("Вы не являетесь автором данного элемента");
                userInterface.send("Элемент не удален. Вы не являетесь автором данного элемента");
            }
        } else {
            System.err.println("Элемент с id = " + id + " отсутствует в коллекции");
            userInterface.send("Элемент с id = " + id + " отсутствует в коллекции");
        }

//        if(collection.removeById(id)) {
//            System.out.println("Элемент с id = " + id + " успешно удален из коллекции");
//            userInterface.send("Элемент с id = " + id + " успешно удален из коллекции");
//            collection.HasUnsavedChanges();
//        } else {
//            System.err.println("Элемент с id = " + id + " отсутствует в коллекции");
//            userInterface.send("Элемент с id = " + id + " отсутствует в коллекции");
//        }
//        collection.save();

    }

    @Override
    public String[] getArgs() {
        return super.getArgs();
    }
}
