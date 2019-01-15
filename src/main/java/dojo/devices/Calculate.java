package dojo.devices;

import dojo.vo.BookItem;
import dojo.vo.HarryPotter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
    double price = 0;
    if(carts.getBookTypes() < 2) {
      BookItem bookItem = carts.getBookItemList().get(0);
      return bookItem.getBookSizes() * bookItem.getHp().getPrice();
    }
    
    return 0;
  }

  private double getDiscount(int bookTypes) {
    return discountLevel.get(bookTypes);
  }
}
