import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by s215055772 on 2017/09/21.
 */
public class Server {
    public static void main(String[] args) {

        int numArrays = 10000;
        int[][] ints = new int[numArrays][1000];

        //populate arrays
        Random rand = new Random();
        for (int i = 0; i < numArrays; i++)
            for (int j = 0; j < 1000; j++)
                ints[i][j] = rand.nextInt(100) + 1;

        try {
            int portNumber = 123;
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();

            PrintWriter out;
            BufferedReader in;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(numArrays);

            for (int i = 0; i < numArrays; i++)
            {
                out.println(Arrays.stream(ints[i]).mapToObj(Integer::toString).reduce((s, s2) -> s + "," + s2).get());
                System.out.println((i+1) + ": " + in.readLine());
            }

            serverSocket.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
