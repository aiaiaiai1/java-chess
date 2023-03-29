package view;

import java.util.ArrayList;
import java.util.List;

import domain.ChessBoard;
import domain.ChessColumn;
import domain.Rank;
import domain.Square;

public class OutputView {

    private final static List<Square> squares = makeSquares();

    private static List<Square> makeSquares() {
        List<Square> squares = new ArrayList<>();
        for (var rank : Rank.values()) {
            initChessColumn(squares, rank);
        }
        return squares;
    }

    private static void initChessColumn(List<Square> squares, Rank rank) {
        for (var chessColumn : ChessColumn.values()) {
            squares.add(Square.of(chessColumn, rank));
        }
    }

    public void printChessBoard(ChessBoard chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Square square : squares) {
            stringBuilder.append(PieceConverter.of(chessBoard.find(square)));
        }
        System.out.print(separateByNewLine(stringBuilder.toString()) + "\n");
    }

    private String separateByNewLine(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, count = 1; i < input.length(); i++, count++) {
            stringBuilder.append(input.charAt(i));
            addNewLine(stringBuilder, count);
        }
        return stringBuilder.toString();
    }

    private void addNewLine(StringBuilder stringBuilder, int count) {
        if (count % ChessColumn.values().length == 0) {
            stringBuilder.append("\n");
        }
    }

    public void printError(String message) {
        System.out.printf("[ERROR]: %s\n", message);
    }

    public void printScore(ChessBoard chessBoard) {
        System.out.printf("Black의 점수 : " + chessBoard.getBlackScore());
        System.out.println();
        System.out.printf("White의 점수 : " + chessBoard.getWhiteScore());
        System.out.println();
    }

    public void printGameEndMessage() {
        System.out.println("King이 죽었습니다. 게임종료!");
    }

    public void printEndMessage() {
        System.out.println("게임을 종료합니다.");
    }
}
