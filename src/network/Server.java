package network;

import app.*;
import collection.CollectionManager;
import collection.Coordinates;
import commands.Command;
import commands.CommandsManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final int PORT = 8000;

        CollectionManager collection = CollectionManager.getInstance();
        DBManager dbManager = new DBManager();
        //System.getProperties().list(System.out);
        //System.out.println(System.getProperties());

       if (System.getProperty("os.name").equals("Windows 10")) {
           dbManager.getSshTunnel(8777); //прокидываем ssh при запуске вне гелиоса
       }


        ServerSocket ss = new ServerSocket(PORT);

        int clientCount = 0;


        System.out.println("Server started");
        try {
            collection.loadCollection(dbManager.getCollection());
        } catch (SQLException throwables) {
            System.err.println("Ошибка при загрузке коллекции");
            throwables.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Коллекция успешно загруженна из базы данных");
        while(true) {

            try {
                Socket s = ss.accept();
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream();
                UserInterface userInterface = new UserInterface(in, out);
                //DBManager dbManager = new DBManager();

                System.out.println("client accepted #" + ++clientCount);

                Object o = userInterface.receive();

                //System.out.println(o.getClass().getName());
                if (o != null) {
                    System.out.println(o);
                    try {
                        Command command = (Command) o;
                        //System.out.println("команда " + command);
                        String arg[] = command.getArgs();
//                        for (int i = 0; i < arg.length; i++) {
//                            System.out.println(arg[i]);
//                        }
                        //System.out.println(command.getObject());
                        CommandsManager.getInstance().executeCommandWithObj(dbManager, userInterface, collection, command, arg);
                    } catch (ParserConfigurationException | TransformerException | NoSuchCommandException e) {
                        userInterface.send("Неизвестная команда, используйте команду help, чтобы посмотреть список всех доступных команд.");
                        e.printStackTrace();
                    } catch (InvalidInputException e) {
                        userInterface.send("Некорректный ввод команды");
                    } catch (IOException e) {
                        System.err.println("Ошибка ввода/вывода");
                        e.printStackTrace();
                    } catch (ClassCastException e) {
                        System.out.println("Команда из строки");
                        try {
                            CommandsManager.getInstance().executeCommand(dbManager, userInterface, collection, o.toString());
                        } catch (ParserConfigurationException | TransformerException | NoSuchCommandException ex) {
                            userInterface.send("Неизвестная команда, используйте команду help, чтобы посмотреть список всех доступных команд.");
                            e.printStackTrace();
                        } catch (InvalidInputException ex) {
                            userInterface.send("Некорректный ввод команды");
                        } catch (IOException ex) {
                            System.err.println("Ошибка ввода/вывода");
                            e.printStackTrace();
                        } catch (Exception ex) {
                            System.err.println("Неизвестная ошибка команды из строки");
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        System.err.println("Неизвестная ошибка");
                        e.printStackTrace();
                    }
                }


                in.close();
                out.close();
                s.close();
            } catch (SocketException | EOFException e) {
                System.err.println("Потеряна связь с клиентом #" + clientCount);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Кажется что то пошло не так, но я все еще работаю!");
            }



        }
    }


}
