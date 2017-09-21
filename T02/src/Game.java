/**
 * Created by s215055772 on 2017/09/21.
 */
public class Game {
    String[][] values;
    boolean gameOver;

    public Game() {
        values = new String[3][3];
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                values[r][c] = String.valueOf((r * 3) + c + 1);

        gameOver = false;
    }

    public boolean add(boolean cross, int pos) {
        //returns whether the choice was valid or not
        //"cross" is true if a cross is to be place

        if (pos < 1 || pos > 9)
            return false;

        int r = (pos - 1) / 3;
        int c = (pos - 1) % 3;

        //check if choice is valid
        if (values[r][c].equals("X") || values[r][c].equals("O"))
            return false;

        if (cross)
            values[r][c] = "X";
        else
            values[r][c] = "O";

        //check if game is over
        checkGameOver();

        return true;
    }

    private void checkGameOver()
    {
        boolean one = checkGameOverForLetter("X");
        boolean two = checkGameOverForLetter("O");

        if (one || two)
            gameOver = true;
    }

    private boolean checkGameOverForLetter(String letter)
    {
        //check horizontal lines
        for (int r = 0; r < 3; r++)
            if (values[r][0].equals(values[r][1]) && values[r][1].equals(values[r][2]))
                return true;

        //check vertical lines
        for (int c = 0; c < 3; c++)
            if (values[0][c].equals(values[1][c]) && values[1][c].equals(values[2][c]))
                return true;

        //check diagonals

        //top left to bot right
        if (values[0][0].equals(values[1][1]) && values[1][1].equals(values[2][2]))
            return true;

        //bot left to top right
        if (values[2][0].equals(values[1][1]) && values[1][1].equals(values[0][2]))
            return true;

        return false;
    }

    public String[] getLines() {
        String[] lines = new String[3];
        lines[0] = values[0][0] + values[0][1] + values[0][2];
        lines[1] = values[1][0] + values[1][1] + values[1][2];
        lines[2] = values[2][0] + values[2][1] + values[2][2];
        return lines;
    }
}
