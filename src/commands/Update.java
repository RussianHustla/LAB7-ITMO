package commands;

import app.DBManager;
import app.InvalidInputException;
import app.Reader;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Update extends Command {

    public Update() {
        command = "update";
        description = "Обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
        String arg[] = getArgs(); //костыль
        if (arg.length < 1) {
            throw new InvalidInputException("У вызываемой команды отсутствует аргумент");
        }
        int id;
        try {
            id = Integer.parseInt(arg[0]);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("У вызываемой команды некорректный аргумент (требуется число)");
        }

        Flat flat = (Flat) getObject();
        if (collection.getFlatById(id) != null) {
            if (collection.getFlatById(id).getAuthor() == getUser().getId()) {
                dbManager.updateById(id,flat,getUser());
                collection.loadCollection(dbManager.getCollection());
                System.out.println("Элемент с id = " + id + " обновлен");
                userInterface.send("Элемент с id = " + id + " обновлен");
            } else {
                userInterface.send("Элемент не обновлен, Вы не являетесь автором элемента");
            }
        } else {
            userInterface.send("Данного элемента не существует");
        }

//            collection.HasUnsavedChanges();
//        collection.save();
    }

    @Override
    public String[] getArgs() {
        return super.getArgs();
    }

    @Override
    public Object getObject() {
        return super.getObject();
    }
}
