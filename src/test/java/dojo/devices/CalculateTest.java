package dojo.devices;

import dojo.vo.HarryPotter1;
import org.junit.Assert;
import org.junit.Test;

public class CalculateTest {

  @Test
  public void should_return_8_eur_when_calculate_proper_pay_way_given_one_book() {
    ShopCart carts = new ShopCart();
    HarryPotter1 hp1 = new HarryPotter1();
    carts.getCarts().add(hp1);
    Calculate calculate = new Calculate();
    double actualPrice = calculate.calculateBookPrice(carts);
    double expectedPrice = 8;
    Assert.assertEquals(expectedPrice, actualPrice, 0.001);
  }
}
