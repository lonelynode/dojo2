package dojo.devices;

import dojo.vo.BookItem;
import dojo.vo.HarryPotter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculateTest {

  private Calculate calculate;
  private double rangePermitted;
  private HarryPotter harryPotter1;
  private HarryPotter harryPotter2;
  private HarryPotter harryPotter3;
  private HarryPotter harryPotter4;
  private HarryPotter harryPotter5;

  @Before
  public void Setup() {
    calculate = new Calculate();
    rangePermitted = 0.001;
    harryPotter1 = new HarryPotter("HarryPotter1");
    harryPotter2 = new HarryPotter("HarryPotter2");
    harryPotter3 = new HarryPotter("HarryPotter3");
    harryPotter4 = new HarryPotter("HarryPotter4");
    harryPotter5 = new HarryPotter("HarryPotter5");
  }

  @Test
  public void should_return_8_eur_when_calculate_book_price_given_one_book() {
    ShopCart carts = new ShopCart();
    BookItem hp1Item = new BookItem(harryPotter1, 1);
    carts.addBookItem(hp1Item);

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 8;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }

  @Test
  public void should_return_right_eur_when_calculate_book_price_given_two_different_book() {
    ShopCart carts = new ShopCart();
    BookItem hp1Item = new BookItem(harryPotter1, 1);
    BookItem hp2Item = new BookItem(harryPotter2, 1);
    carts.addBookItem(hp1Item);
    carts.addBookItem(hp2Item);

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 15.2;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }

  @Test
  public void should_return_right_eur_when_calculate_book_price_given_three_different_book() {
    ShopCart carts = new ShopCart();

    BookItem hp1Item = new BookItem(harryPotter1, 2);
    BookItem hp2Item = new BookItem(harryPotter2, 1);
    BookItem hp3Item = new BookItem(harryPotter3, 1);
    BookItem hp4Item = new BookItem(harryPotter4, 1);
    BookItem hp5Item = new BookItem(harryPotter5, 1);
    carts.addBookItem(hp1Item);
    carts.addBookItem(hp2Item);
    carts.addBookItem(hp3Item);
    carts.addBookItem(hp4Item);
    carts.addBookItem(hp5Item);

    double actualPrice = calculate.calculateBookPrice(carts);

    double expectedPrice = 38;
    Assert.assertEquals(expectedPrice, actualPrice, rangePermitted);
  }
}
