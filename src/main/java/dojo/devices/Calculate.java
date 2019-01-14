package dojo.devices;

import dojo.vo.HarryPotter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculate {


  private static Map<Integer, Double> discountLevel;


  static {
    discountLevel = new HashMap<Integer, Double>();
    discountLevel.put(1, 0.05);
    discountLevel.put(2, 0.1);
    discountLevel.put(3, 0.15);
    discountLevel.put(4, 0.2);
    discountLevel.put(5, 0.25);

  }

  public double calculateBookPrice(ShopCart carts) {
    List<HarryPotter> books = carts.getCarts();
    double price = 0;
    for(HarryPotter book: books) {
      price += book.getPrice();
    }
    return books.size() > 1 ? (price * (1 - getDiscount(books.size()))) : price;
  }

  private double getDiscount(int bookNumbers) {
    return discountLevel.get(bookNumbers);
  }
}
