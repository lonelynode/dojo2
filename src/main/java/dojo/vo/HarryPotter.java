package dojo.vo;

public class HarryPotter extends Book {

  private double price = 8;
  private String name = "HarryPotter";

  public HarryPotter() {
    super();
  }

  public HarryPotter(String name, double price) {
    super(name, price);
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}
