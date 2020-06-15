package commands;

import app.DBManager;
import app.InvalidInputException;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SingUp extends Command{

    public SingUp() {
        command = "singup";
        description = "Зарегистрироваться в базе данных";
        object = null;
        withObj = true;
    }

    @Override
    public void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        if (args.length < 2) {
            throw new InvalidInputException("У вызываемой команды некорректное количество аргументов");
        }
        String login = (String)args[0];
        String pass = (String)args[1];
        dbManager.signUpUser(login, pass);
        System.out.println("Новый пользователь зарегистрирован");
        userInterface.send("Вы успешно зарегистрированы");
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
