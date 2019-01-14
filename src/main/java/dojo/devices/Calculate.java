package dojo.devices;

import dojo.vo.Book;
import dojo.vo.HarryPotter;
import java.util.List;

public class Calculate {


  public double calculateBookPrice(ShopCart carts) {
    List<HarryPotter> books = carts.getCarts();
    double price = 0;
    for(HarryPotter book: books) {
      System.out.println(book.getPrice());
      price += book.getPrice();
    }
    return price;
  }
}
