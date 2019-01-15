package dojo.devices;

import dojo.vo.BookItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculate {


  private static Map<Integer, Double> discountLevel;


  static {
    discountLevel = new HashMap<Integer, Double>();
    discountLevel.put(2, 0.05);
    discountLevel.put(3, 0.1);
    discountLevel.put(4, 0.2);
    discountLevel.put(5, 0.25);

  }

  public double calculateBookPrice(ShopCart carts) {
    double price = 0;
    if (carts.getBookTypes() < 2) {
      BookItem bookItem = carts.getBookItemList().get(0);
      return bookItem.getBookSizes() * bookItem.getHp().getPrice();
    }

    List<BookItem> bookItemList = carts.getBookItemList();
    List<Integer> bookTeam = new ArrayList<Integer>();
    while (bookItemList.size() > 0) {
      int booktype = 0;
      for (int i = 0; i < bookItemList.size(); i++) {
        BookItem bookItem = bookItemList.get(i);
        int bookSize = bookItem.getBookSizes();
        bookItem.decreaseBookSizes();
        bookItemList.set(i, bookItem);
        booktype++;
        if (bookSize - 1 == 0) {
          bookItemList.remove(bookItem);
          i--;
        }
      }
      bookTeam.add(booktype);
    }
    for (Integer size : bookTeam) {
      price += 8 * size * (1 - getDiscount(size));
    }

    return price;
  }

  private double getDiscount(int bookTypes) {
    if(bookTypes == 1)
      return 0;
    return discountLevel.get(bookTypes);
  }
}
