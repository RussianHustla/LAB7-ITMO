package commands;

import app.DBManager;
import app.UserInterface;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Clear extends Command {

    public Clear() {
        command = "clear";
        description = "Отчистить коллекцию";
    }
    @Override
    public void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
        //int count = collection.size();
        dbManager.deleteByAuthor(getUser().getId());
        collection.loadCollection(dbManager.getCollection());
        System.out.println("Коллекция успешно очищенна от элементов, созданных автором с id = " + getUser().getId());
        userInterface.send("Коллекция успешно очищенна от элементов, созданных Вами");
//        collection.HasUnsavedChanges();
//        collection.save();

    }
}
