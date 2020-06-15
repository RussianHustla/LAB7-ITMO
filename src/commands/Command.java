package commands;

import app.DBManager;
import app.User;
import app.UserInterface;
import collection.CollectionManager;
import collection.Flat;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Абстрактный класс-предок всех команд.
 */
public abstract class Command implements Serializable {
    protected String command;
    protected String description;
    protected Object object;
    protected boolean withObj = false;
    protected String[] args;
    protected User user;

    public abstract void execute(DBManager dbManager, UserInterface userInterface, CollectionManager collection, Object[] args) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, SQLException, NoSuchAlgorithmException;

    public void execute(UserInterface userInterface, Object[] args) throws IOException {

    };

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public void setObject(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }

    public boolean isWithObj() {
        return withObj;
    }

    public void setWithObj(boolean withObj) {
        this.withObj = withObj;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
