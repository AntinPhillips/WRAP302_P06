import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by s215055772 on 2017/09/21.
 */
public class Client {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 123;

        try {
            Socket socket = new Socket(hostName, portNumber);

            PrintWriter out;
            BufferedReader in;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int numArrays = Integer.valueOf(in.readLine());

            for (int i = 0; i < numArrays; i++)
            {
                String values = in.readLine();
                Histogram histogram = new Histogram(100, 1, 20);
                Arrays.stream(values.split(",")).forEach(s -> histogram.add(Integer.valueOf(s)));
                out.println(histogram.toString());
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
