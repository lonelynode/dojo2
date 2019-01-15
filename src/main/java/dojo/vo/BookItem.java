package dojo.vo;

public class BookItem {
  private HarryPotter hp;
  private int bookSizes;

  public BookItem(HarryPotter hp, int bookSizes) {
    this.hp = hp;
    this.bookSizes = bookSizes;
  }

  public HarryPotter getHp() {
    return hp;
  }

  public void setHp(HarryPotter hp) {
    this.hp = hp;
  }

  public int getBookSizes() {
    return bookSizes;
  }

  public void setBookSizes(int bookSizes) {
    this.bookSizes = bookSizes;
  }
}
