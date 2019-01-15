package dojo.devices;

import dojo.uitl.ArrayUtil;
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
      int booktype = extractBookGroup(bookItemList);
      bookTeam.add(booktype);
    }

    Integer[] bookTeamArray = balanceGropu(bookTeam);

    for (Integer bookTypes : bookTeamArray) {
      price += 8 * bookTypes * (1 - getDiscount(bookTypes));
    }
    return price;
  }

  private Integer[] balanceGropu(List<Integer> bookTeam) {
    Integer[] bookTeamArray = bookTeam.toArray(new Integer[bookTeam.size()]);
    int low = 0;
    int high = bookTeamArray.length - 1;
    ArrayUtil.sortFromGrantToSmall(bookTeamArray, 0, high);
    while(low < high) {
      if(bookTeamArray[low] == 5 && bookTeamArray[high] == 3) {
        bookTeamArray[low]--;
        bookTeamArray[high]++;
      }
      if(bookTeamArray[low] != 5) {
        low++;
      }
      if(bookTeamArray[high] != 3) {
        high--;
      }
    }
    return bookTeamArray;
  }

  private int extractBookGroup(List<BookItem> bookItemList) {
    int bookTypes = bookItemList.size();
    for (int i = 0; i < bookItemList.size(); i++) {
      BookItem bookItem = bookItemList.get(i);
      bookItem.decreaseBookSizes();
      bookItemList.set(i, bookItem);
      if (bookItem.hasBookSize()) {
        bookItemList.remove(bookItem);
        i--;
      }
    }
    return bookTypes;
  }

  private double getDiscount(int bookTypes) {
    if(bookTypes == 1)
      return 0;
    return discountLevel.get(bookTypes);
  }
}
