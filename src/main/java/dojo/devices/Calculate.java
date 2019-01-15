package dojo.devices;

import dojo.uitl.ArrayUtil;
import dojo.vo.BookItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Calculate {


  private static Map<Integer, Double> discountLevel;
  private List<BalanceCriticalty> balanceCriticalties = new ArrayList<BalanceCriticalty>();


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

    getBalanceCriticality(bookTeam);
    Integer[] bookTeamArray = balanceGropu(bookTeam);

    for (Integer bookTypes : bookTeamArray) {
      price += 8 * bookTypes * (1 - getDiscount(bookTypes));
    }
    return price;
  }

  private void getBalanceCriticality(List<Integer> bookTeam) {
    Set<Integer> set = new TreeSet<Integer>();
    set.addAll(bookTeam);
    Integer[] array = set.toArray(new Integer[0]);
    for(int i =0; i < array.length; i++)
      for (int j = i + 1; j < array.length; j++) {
        double price1 = array[i] * 1 * (1 - getDiscount(array[i])) + array[j] * 1 * (1 - getDiscount(array[j]));
        int m = array[i];
        int n = array[j];
        while(m < n) {
          m++;
          n--;
          double price2 = m * 1 * (1 - getDiscount(m)) + n * 1 * (1 - getDiscount(n));
          if (price1 > price2) {
            BalanceCriticalty balanceCriticalty = new BalanceCriticalty();
            balanceCriticalty.setLow(array[i]);
            balanceCriticalty.setHigh(array[j]);
            balanceCriticalties.add(balanceCriticalty);
          }
        }
      }
  }

  private Integer[] balanceGropu(List<Integer> bookTeam) {
    Integer[] bookTeamArray = bookTeam.toArray(new Integer[0]);
    int low = 0;
    int high = bookTeamArray.length - 1;
    for(BalanceCriticalty bc: balanceCriticalties) {
      int lowLimit = bc.getLow();
      int highLimit = bc.getHigh();
      ArrayUtil.sortFromGrantToSmall(bookTeamArray, 0, high);
      while (low < high) {
        if (bookTeamArray[low] == highLimit && bookTeamArray[high] == lowLimit) {
          bookTeamArray[low]--;
          bookTeamArray[high]++;
        }
        if (bookTeamArray[low] != highLimit) {
          low++;
        }
        if (bookTeamArray[high] != lowLimit) {
          high--;
        }
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

  private class BalanceCriticalty {
    int low;
    int high;

    public int getLow() {
      return low;
    }

    public void setLow(int low) {
      this.low = low;
    }

    public int getHigh() {
      return high;
    }

    public void setHigh(int high) {
      this.high = high;
    }
  }
}
