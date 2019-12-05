package common.protocol.http;

import common.request.RpcRequest;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: changjiale
 * @create: 2019/12/05 11:30
 * @description:
 */
public class HttpClient {
    public String send(String hostname, Integer port, RpcRequest rpcRequest) {
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            OutputStream outputStream = urlConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(rpcRequest);
            oos.flush();
            oos.close();
            InputStream inputStream = urlConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
