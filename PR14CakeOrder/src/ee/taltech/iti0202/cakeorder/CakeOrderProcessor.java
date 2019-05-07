package ee.taltech.iti0202.cakeorder;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CakeOrderProcessor {
  private int nextOrderId = 1;

  private class CakeOrder {
    @SerializedName("order_id")
    private Integer orderId;
    private List<Cake> cakes;
    private Double total;
  }

  private class Cake {
    @SerializedName("cake_id")
    private String cakeId;
    private String name;
    @SerializedName("BBD")
    private String bbd;
    private double price;
    private double kg;
    private List<String> ingredients;
  }
  public enum CakeOrderProcessorType {
    MAKE_DAIRY_FREE,
    COUNT_TOTAL_SUM,
    REMOVE_BEST_BEFORE_DAY_OVER

  }

  private CakeOrderProcessorType type;

  public CakeOrderProcessor(CakeOrderProcessorType type) {
    this.type = type;
  }

  public String process(String jsonInput) {
    Gson gson = new Gson();
    CakeOrder order = gson.fromJson(jsonInput, CakeOrder.class);
    order.orderId = nextOrderId;
    nextOrderId++;
    Double total = 0d;
    List<Cake> removeList = new ArrayList<>();
//    int cakeCounter = 0;
    for (Cake cake : order.cakes) {
      String cakeId = "";
      List<String> nameWords = Arrays.asList(cake.name.split(" "));
      for (String word : nameWords) {
        cakeId = cakeId.concat(word.substring(0, 1));
      }
      cakeId += cakeId.length();
      cake.cakeId = cakeId;
      total += cake.price * cake.kg;
      if (type.equals(CakeOrderProcessorType.MAKE_DAIRY_FREE)) {
        List<String> newIngredients = new ArrayList<>();
        Double multiplier = 1d;
        for (String ingredient : cake.ingredients) {
          if (ingredient.equals("milk") || ingredient.equals("cream-cheese") || ingredient.equals("yoghurt")) {
            newIngredients.add("plant-" + ingredient);
            multiplier += 0.1;
          } else {
            newIngredients.add(ingredient);
          }
        }
        cake.ingredients = newIngredients;
        cake.price *= multiplier;
        // TODO make sure that it changes the cake object like I want it to
      }
      if (type.equals(CakeOrderProcessorType.REMOVE_BEST_BEFORE_DAY_OVER)) {
        LocalDate bbd = LocalDate.parse(cake.bbd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (LocalDate.now().compareTo(bbd) > 0) {
          removeList.add(cake);
        }
      }
//      cakeCounter++;
    }
    if (type.equals(CakeOrderProcessorType.COUNT_TOTAL_SUM)) {
      order.total = total;
    }
    for (Cake cake : removeList) {
      order.cakes.remove(cake);
    }
    return gson.toJson(order);
  }
}