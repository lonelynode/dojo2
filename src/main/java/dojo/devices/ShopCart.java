package dojo.devices;

import dojo.vo.Book;
import dojo.vo.HarryPotter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopCart {
  private List<HarryPotter> carts;
  public ShopCart() {
    this.carts = new ArrayList<HarryPotter>();
  }

  public List<HarryPotter> getCarts() {
    return carts;
  }

  public void setCarts(List<HarryPotter> carts) {
    this.carts = carts;
  }
}