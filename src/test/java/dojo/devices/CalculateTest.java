package dojo.devices;

import dojo.vo.HarryPotter1;
import dojo.vo.HarryPotter2;
import dojo.vo.HarryPotter3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculateTest {

  private Calculate calculate;
  private double rangePermitted;

  @Before
  public void Setup() {
    calculate = new Calculate();
    rangePermitted = 0.001;
  }

  @Test
  public void should_return_8_eur_when_calculate_book_price_given_one_book() {
    ShopCart carts = new ShopCart();
    HarryPotter1 hp1 = new HarryPotter1();
    carts.getCarts().add(hp1);

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 8;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }

  @Test
  public void should_return_right_eur_when_calculate_book_price_given_two_different_book() {
    ShopCart carts = new ShopCart();
    HarryPotter1 hp1 = new HarryPotter1();
    HarryPotter2 hp2 = new HarryPotter2();
    carts.getCarts().add(hp1);
    carts.getCarts().add(hp2);

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 14.4;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }

}
