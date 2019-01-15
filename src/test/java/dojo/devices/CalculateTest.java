package dojo.devices;

import dojo.vo.BookItem;
import dojo.vo.HarryPotter1;
import dojo.vo.HarryPotter2;
import dojo.vo.HarryPotter3;
import dojo.vo.HarryPotter4;
import dojo.vo.HarryPotter5;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculateTest {

  private Calculate calculate;
  private double rangePermitted;
  HarryPotter1 hp1;
  HarryPotter2 hp2;
  HarryPotter3 hp3;
  HarryPotter4 hp4;
  HarryPotter5 hp5;

  @Before
  public void Setup() {
    calculate = new Calculate();
    rangePermitted = 0.001;
    hp1 = new HarryPotter1();
    hp2 = new HarryPotter2();
    hp3 = new HarryPotter3();
    hp4 = new HarryPotter4();
    hp5 = new HarryPotter5();
  }

  @Test
  public void should_return_8_eur_when_calculate_book_price_given_one_book() {
    ShopCart carts = new ShopCart();
    BookItem hp1Item = new BookItem(hp1, 1);
    carts.addBookItem(hp1Item);

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 8;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }

  @Test
  public void should_return_right_eur_when_calculate_book_price_given_two_different_book() {
    ShopCart carts = new ShopCart();
    BookItem hp1Item = new BookItem(hp1, 1);
    BookItem hp2Item = new BookItem(hp2, 1);
    carts.addBookItem(hp1Item);
    carts.addBookItem(hp2Item);

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 14.4;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }

  @Test
  public void should_return_right_eur_when_calculate_book_price_given_three_different_book() {
    ShopCart carts = new ShopCart();

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 20.4;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }
}
