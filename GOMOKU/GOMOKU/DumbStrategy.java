package ee.taltech.iti0202.gomoku.strategy;

import ee.taltech.iti0202.gomoku.game.Location;
import ee.taltech.iti0202.gomoku.game.SimpleBoard;
import ee.taltech.iti0202.gomoku.opponent.ComputerStrategy;

import java.util.ArrayList;
import java.util.List;

public class DumbStrategy implements ComputerStrategy {
  private final static int ENEMY_WEIGHT = 30;
  private final static int PLAYER_WEIGHT = 0;
  private static final int SUPER_DANGER = 100;
  private static final int SUPER_WIN = 10000;
  @Override
  public Location getMove(SimpleBoard board, int player) {
    int[][] b = board.getBoard();
    int[][] weightBoard = new int[b.length][b.length];
    weightBoard = evaluateRow(weightBoard, b, player);
    weightBoard = evaluateCol(weightBoard, b, player);
    weightBoard = evaluateDiagonalV2(weightBoard, b, player);
    weightBoard = evaluateDiagonalV1(weightBoard, b, player);


    int maxWeight = Integer.MIN_VALUE;
    int bestRow = 5;
    int bestCol = 5;
    for (int row = b.length - 1; row >= 0; row--) {
      for (int col = b[0].length - 1; col >= 0; col--) {
        if (weightBoard[row][col] > maxWeight && b[row][col] == 0) {
          maxWeight = weightBoard[row][col];
          bestRow = row;
          bestCol = col;
        }
      }
    }
    return new Location(bestRow, bestCol);
  }
  private int[][] evaluate (int[][] weightBoard, int myPoints, int enemyPoints, ArrayList<ArrayList<Integer>> emptyPoints, int space) {
    int pointsToAdd = 0;
    if (myPoints == 0 && enemyPoints == 0) {
      return weightBoard;
    } else if (myPoints == 0) {
      pointsToAdd = (int) Math.pow(ENEMY_WEIGHT, enemyPoints); // - space * ENEMY_WEIGHT;

//      if (enemyPoints >= 3 && space == 0 || enemyPoints >= 4) {
//        pointsToAdd = (int) Math.pow(SUPER_DANGER, enemyPoints);
//      } else if (enemyPoints <= 2 && space > 2) {
//        pointsToAdd = 0;
//      } else {
//        pointsToAdd = (int) Math.pow(ENEMY_WEIGHT, enemyPoints) - space * ENEMY_WEIGHT;
//      }
    } else if (enemyPoints == 0) {
      if (myPoints == 4) {
        pointsToAdd = (int)Math.pow(SUPER_WIN, myPoints);
      } else {
        pointsToAdd = (int) Math.pow(PLAYER_WEIGHT, myPoints);
      }
    }
    for (ArrayList<Integer> point : emptyPoints) {
      weightBoard[point.get(0)][point.get(1)] += pointsToAdd;
    }
    return weightBoard;
  }

  private int[][] evaluateDiagonalV1 (int[][] weightBoard, int[][] board, int player) {
    for (int startCol = board.length - 5; startCol > -board.length + 4; startCol--) {
      int startRow = startCol < 0 ? -startCol : 0;
      int startColTemp = startCol < 0 ? 0 : startCol;
      for (int offSet = 0; offSet < board.length - startColTemp - startRow - 4; offSet++) {
        int enemyPoints = 0;
        int myPoints = 0;
        ArrayList<ArrayList<Integer>> emptyPoints = new ArrayList<>();
        int space = 0;
        boolean hasEnd = false;
        for (int iterator = offSet; iterator <= 4 + offSet; iterator++) {
          if (board[startRow + iterator][startColTemp + iterator] == player) {
            myPoints++;
          } else if (board[startRow + iterator][startColTemp + iterator] != 0) {
            if (space != 0) {
              hasEnd = true;
            }
            enemyPoints++;
          } else {
            if (enemyPoints > 0 && !hasEnd) {
              space++;
            }
            emptyPoints.add(new ArrayList<>(List.of(startRow + iterator, startColTemp + iterator)));
          }
        }
        if (!hasEnd) {
          space = 0;
        }
        weightBoard = evaluate(weightBoard, myPoints, enemyPoints, emptyPoints, space);
      }
    }
    return weightBoard;
  }

  private int[][] evaluateDiagonalV2 (int[][] weightBoard, int[][] board, int player) {

    for (int startRow = 4; startRow < 2 * board.length - 5 ; startRow++) {
      int startCol = startRow < board.length ? 0 : startRow - board.length + 1;
      int startRowTemp = startRow >= board.length ? board.length - 1 : startRow;
      for (int offSet = 0; offSet <= startRowTemp - startCol - 4; offSet++) {
        int enemyPoints = 0;
        int myPoints = 0;
        ArrayList<ArrayList<Integer>> emptyPoints = new ArrayList<>();
        int space = 0;
        boolean hasEnd = false;
        for (int iterator = offSet; iterator <= 4 + offSet; iterator++) {
          if (board[startRowTemp - iterator][startCol + iterator] == player) {
            myPoints++;
          } else if (board[startRowTemp - iterator][startCol + iterator] != 0) {
            if (space != 0) {
              hasEnd = true;
            }
            enemyPoints++;
          } else {
            if (enemyPoints > 0 && !hasEnd) {
              space++;
            }
            emptyPoints.add(new ArrayList<>(List.of(startRowTemp - iterator, startCol + iterator)));
          }
        }
        if (!hasEnd) {
          space = 0;
        }
        weightBoard = evaluate(weightBoard, myPoints, enemyPoints, emptyPoints, space);

      }
    }
    return weightBoard;
  }

  private int[][] evaluateCol(int[][] weightBoard, int[][] board, int player) {
    for (int col = 0; col < board.length; col++) {
      for (int startRow = 0; startRow < board.length - 4; startRow++) {
        int enemyPoints = 0;
        int myPoints = 0;
        ArrayList<ArrayList<Integer>> emptyPoints = new ArrayList<>();
        int space = 0;
        boolean hasEnd = false;
        for (int curRow = startRow; curRow < startRow + 5; curRow++) {
          if (board[curRow][col] == player) {
            myPoints++;
          } else if (board[curRow][col] != 0) {
            if (space != 0) {
              hasEnd = true;
            }
            enemyPoints++;
          } else {
            if (enemyPoints > 0 && !hasEnd) {
              space++;
            }
            emptyPoints.add(new ArrayList<>(List.of(curRow, col)));
          }
        }
        if (!hasEnd) {
          space = 0;
        }
        weightBoard = evaluate(weightBoard, myPoints, enemyPoints, emptyPoints, space);

      }
    }
    return weightBoard;
  }

  private int[][] evaluateRow (int[][] weightBoard, int[][] board, int player) {
    for (int row = 0; row < board.length; row++) {
      for (int startCol = 0; startCol < board.length - 4; startCol++) {
        int enemyPoints = 0;
        int myPoints = 0;
        ArrayList<ArrayList<Integer>> emptyPoints = new ArrayList<>();
        int space = 0;
        boolean hasEnd = false;
        for (int curCol = startCol; curCol < startCol + 5; curCol++) {
          if (board[row][curCol] == player) {
            myPoints++;
          } else if (board[row][curCol] != 0) {
            enemyPoints++;
            if (space != 0) {
              hasEnd = true;
            }
          } else {
            if (enemyPoints > 0 && !hasEnd) {
              space++;
            }
            emptyPoints.add(new ArrayList<>(List.of(row, curCol)));
          }
        }
        if (!hasEnd) {
          space = 0;
        }
        weightBoard = evaluate(weightBoard, myPoints, enemyPoints, emptyPoints, space);

      }
    }
    return weightBoard;
  }

  @Override
  public String getName() {
    return "Dumb Strategu";
  }

}
