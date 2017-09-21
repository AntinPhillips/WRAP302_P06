import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by s215055772 on 2017/09/21.
 */
public class Server {
    public static void main(String[] args) {

        try
        {
            int portNumber = 123;
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();

            PrintWriter out;
            BufferedReader in;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            boolean newGame = true;
            int startTurn = 0;
            int curTurn = startTurn;

            int scoreServer = 0;
            int scoreClient = 0;

            while (newGame)
            {
                Game game = new Game();

                while (!game.gameOver)
                {
                    if (curTurn == 0)
                    {
                        //SERVER TURN

                        //REPEAT UNTIL CORRECT
                        Arrays.stream(game.getLines()).forEach(System.out::println);
                        System.out.println("Please enter the number where you'd like to place your mark.");
                        String choice = consoleIn.readLine();
                        while (!game.add(false, Integer.valueOf(choice)))
                        {
                            System.out.println("Entered value was not valid. Enter again.");
                            choice = consoleIn.readLine();
                        }
                    }
                    else
                    {
                        //CLIENT TURN

                        //REPEAT UNTIL CORRECT
                        out.println("1");
                        Arrays.stream(game.getLines()).forEach(out::println);
                        String choice = in.readLine();

                        while (!game.add(true, Integer.valueOf(choice)))
                        {
                            System.out.println("Entered value was not valid. Enter again.");
                            out.println("3");
                            choice = in.readLine();
                        }
                    }

                    if (game.gameOver)
                        if (curTurn == 0)
                            scoreServer++;
                        else
                            scoreClient++;

                    //switch turns
                    curTurn++;
                    curTurn%=2;
                }

                out.println("2");
                out.println(scoreServer);
                out.println(scoreClient);
                Arrays.stream(game.getLines()).forEach(out::println);
                String choiceClient = in.readLine();

                System.out.println("Game Over");
                System.out.println("Sever score: " + scoreServer);
                System.out.println("Sever client: " + scoreClient);
                System.out.println("Would you like to play again? Y/N");
                String choiceServer = consoleIn.readLine();

                while (!choiceServer.equals("Y") && !choiceServer.equals("N")) {
                    System.out.println("Entered value was not valid. Enter again.");
                    choiceServer = consoleIn.readLine();
                }

                if (!choiceClient.equals(choiceServer) || !choiceClient.equals("Y"))
                    newGame = false;

                //set starting turn for new game
                startTurn++;
                startTurn %= 2;
                curTurn = startTurn;
            }

            out.println("4");
            serverSocket.close();
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }
}
