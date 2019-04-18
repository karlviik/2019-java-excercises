package ee.taltech.iti0202.sum100;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sum100 {
  private static List<String> iterator(int currentDigit, int sumSoFar, int currentNumber) {
    List<String> options = new ArrayList<>();
    if (currentDigit == 10) {
      if (sumSoFar + currentNumber == 100) {
        return List.of(currentNumber < 0 ? String.valueOf(currentNumber) : String.valueOf(currentNumber));
      } else {
        return List.of();
      }
    }
    List<String> makeNumBigger = iterator(currentDigit + 1, sumSoFar, currentNumber < 0
        ? currentNumber * 10 - currentDigit
        : currentNumber * 10 + currentDigit);
    List<String> nextPosNum = iterator(currentDigit + 1, sumSoFar + currentNumber, currentDigit);
    List<String> nextNegNum = iterator(currentDigit + 1, sumSoFar + currentNumber, -currentDigit);

    options.addAll(makeNumBigger);
    options.addAll(nextPosNum.stream().map(x -> currentNumber + "+" + x).collect(Collectors.toList()));
    options.addAll(nextNegNum.stream().map(x -> currentNumber + x).collect(Collectors.toList()));
    return options;
  }

  public static List<String> calcSums() {
    List<String> all = new ArrayList<>();
    all.addAll(iterator(2, 0, 1));
    all.addAll(iterator(2, 0, -1));

    return all; // TODO
  }

}


