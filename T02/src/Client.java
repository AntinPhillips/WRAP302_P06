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
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String instruction = in.readLine();

                switch (instruction) {
                    case "1": {
                        //display board and ask for input
                        for (int i = 0; i < 3; i++)
                            System.out.println(in.readLine());

                        System.out.println("Please enter the number where you'd like to place your mark.");
                        String choice = consoleIn.readLine();
                        out.println(choice);
                        break;
                    }
                    case "2": {
                        //game over. display board, display scores and ask if they want to play again
                        for (int i = 0; i < 3; i++)
                            System.out.println(in.readLine());

                        System.out.println("Game Over");

                        //get scores
                        int scoreServer = Integer.valueOf(in.readLine());
                        int scoreClient = Integer.valueOf(in.readLine());
                        System.out.println("Sever score: " + scoreServer);
                        System.out.println("Sever client: " + scoreClient);

                        System.out.println("Would you like to play again? Y/N");
                        String choice = consoleIn.readLine();

                        while (!choice.equals("Y") && !choice.equals("N")) {
                            System.out.println("Entered value was not valid. Enter again.");
                            choice = consoleIn.readLine();
                        }

                        out.println(choice);
                        break;
                    }
                    case "3": {
                        //enter your damn choice again because it wasn't valid!
                        System.out.println("Entered value was not valid. Enter again.");
                        String choice = consoleIn.readLine();
                        out.println(choice);
                        break;
                    }
                    case "4": {
                        //end game
                        socket.close();
                        return;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
