package commands;

import app.DBManager;
import app.UserInterface;
import collection.Flat;
import collection.CollectionManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

public class Add extends Command {

    public Add() {
        command = "add";
        description = "Добавить новый элемент в коллекцию";
        object = null;
        withObj = true;
    }

    @Override
    public void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, SQLException {
        Flat flat = (Flat) getObject();
        int id = dbManager.addFlat(flat, getUser());
        if (id > 0) {
//            flat.setId(id);
//            collection.add(flat);
//            System.out.println(collection.getLast());
            collection.loadCollection(dbManager.getCollection());
            System.out.println("Новый элемент c id = " + id + " добавлен в коллекцию");
            userInterface.send("Новый элемент c id = " + id + " добавлен в коллекцию");
        }
//        collection.add(flat);
//        System.out.println(collection.getLast());
//        System.out.println("Новый элемент добавлен в коллекцию");
//        userInterface.send("Новый элемент добавлен в коллекцию");
//        collection.HasUnsavedChanges();
//        collection.save();
        //CommandsManager.getInstance().executeCommand(userInterface, collection, "save flats.xml");
    }



    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public Object getObject() {
        return super.getObject();
    }

    @Override
    public String[] getArgs() {
        return super.getArgs();
    }

    @Override
    public void setArgs(String[] args) {
        super.setArgs(args);
    }
}
