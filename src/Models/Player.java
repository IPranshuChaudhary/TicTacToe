package Models;

import java.util.Scanner;

public class Player {

    Scanner sc = new Scanner(System.in);
    private int id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(int id, String name, Symbol symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlyerType() {
        return playerType;
    }

    public void setPlyerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {

        System.out.println(name+" Its your turn to make the move:");

        System.out.println("Enter Row: ");
        int row = sc.nextInt();

        System.out.println("Enter Col: ");
        int col = sc.nextInt();

        return new Move(new Cell(row, col), this);
    }
}
