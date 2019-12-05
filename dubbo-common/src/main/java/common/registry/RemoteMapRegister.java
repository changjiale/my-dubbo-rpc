package common.registry;

import common.URL;

import java.io.*;
import java.util.*;

/**
 * @author: changjiale
 * @create: 2019/12/05 11:43
 * @description:
 */
public class RemoteMapRegister {

    //{服务名：{URL:实现类}}
    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    //一个接口对应多个map,每个map只有一个key/value，key为可用的url(服务地址),value(具体的实现类)
    public static void register(String interfaceName, URL url) {
        List<URL> list = Collections.singletonList(url);
        REGISTER.put(interfaceName,list);
        saveFile();
    }


    public static URL random(String interfaceName){
        REGISTER=getFile();

        List<URL> list = REGISTER.get(interfaceName);
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }


    public static Map<String,List<URL>> getFile(){
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/"+"temp.txt");
            ObjectInputStream in=new ObjectInputStream(fileInputStream);
            Object o = in.readObject();
            return (Map<String, List<URL>>) o;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void saveFile(){
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+"/"+"temp.txt");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
