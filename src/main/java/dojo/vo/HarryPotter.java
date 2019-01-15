package dojo.vo;

public class HarryPotter extends Book {

  private double price = 8;

  public HarryPotter(String name) {
    super(name);
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
