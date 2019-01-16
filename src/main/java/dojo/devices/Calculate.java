package dojo.devices;

import dojo.uitl.ArrayUtil;
import dojo.vo.BookItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Calculate {


  private static Map<Integer, Double> discountLevel;
  private BalanceCriticalty[] bcArray;


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
    Integer[] bookTeamArray = balanceGroup(bookTeam);

    for (Integer bookTypes : bookTeamArray) {
      price += calculateGroupPrice(bookTypes, 8, getDiscount(bookTypes));
    }
    return price;
  }

  private double calculateGroupPrice(Integer bookTypes, int i, double discount) {
    return i * bookTypes * (1 - discount);
  }

  private void getBalanceCriticality(List<Integer> bookTeam) {
    Set<Integer> set = new TreeSet<Integer>();
    set.addAll(bookTeam);
    List<BalanceCriticalty> balanceCriticalties = new ArrayList<BalanceCriticalty>();
    Integer[] array = set.toArray(new Integer[0]);
    for(int i =0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        double price1 = calculateGroupPrice(array[i], 8, getDiscount(array[i])) + calculateGroupPrice(array[j], 8, getDiscount(array[j]));
        int low = 0;
        int high = 0;
        int m = array[i];
        int n = array[j];

        if(!alreadyHasCriticalties(m , n)) {
          while (m < n) {
            m++;
            n--;
            double price2 = calculateGroupPrice(m, 8, getDiscount(m)) + calculateGroupPrice(n, 8, getDiscount(n));
            if (price1 > price2) {
              low = array[i];
              high = array[j];
            }
          }
          if (low != 0 && high != 0) {
            BalanceCriticalty balanceCriticalty = new BalanceCriticalty();
            balanceCriticalty.setLow(array[i]);
            balanceCriticalty.setHigh(array[j]);
            balanceCriticalties.add(balanceCriticalty);
          }
        }
      }
    }
    bcArray = balanceCriticalties.toArray(new BalanceCriticalty[0]);
    sortBalanceCriticalty(bcArray);
  }

  private boolean alreadyHasCriticalties(int m, int n) {
    if(bcArray == null || bcArray.length == 0)
      return false;
    for(BalanceCriticalty bc: bcArray) {
      if(bc.getLow() == m && bc.getHigh() == n)
        return true;
    }
    return false;
  }

  private Integer[] balanceGroup(List<Integer> bookTeam) {
    Integer[] bookTeamArray = bookTeam.toArray(new Integer[0]);
    int low = 0;
    int high = bookTeamArray.length - 1;
    for(BalanceCriticalty bc: bcArray ) {
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

  private void sortBalanceCriticalty(BalanceCriticalty[] bcArray) {
    if(bcArray.length > 1) {
      for (int i = 0; i < bcArray.length; i++) {
        for (int j = i + 1; j > 0; j--) {
          double price1 = bcArray[i].getLow() * 1 * (1 - getDiscount(bcArray[i].getLow())) + bcArray[i].getHigh() * 1 * (1 - getDiscount(bcArray[i].getHigh()));
          double price2 = bcArray[j].getLow() * 1 * (1 - getDiscount(bcArray[j].getLow())) + bcArray[j].getHigh() * 1 * (1 - getDiscount(bcArray[j].getHigh()));
          if (price1 < price2) {
            BalanceCriticalty temp;
            temp = bcArray[i];
            bcArray[i] = bcArray[j];
            bcArray[j] = temp;
          }
        }
      }
    }
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

    int getLow() {
      return low;
    }

    void setLow(int low) {
      this.low = low;
    }

    int getHigh() {
      return high;
    }

    void setHigh(int high) {
      this.high = high;
    }
  }
}
