package dojo.devices;

import dojo.vo.BookItem;
import java.util.ArrayList;
import java.util.List;

public class ShopCart {
  private List<BookItem> bookItemList;
  public ShopCart() {
    this.bookItemList  = new ArrayList<BookItem>();
  }

  public List<BookItem> getBookItemList() {
    return bookItemList;
  }

  public void setBookItemList(List<BookItem> bookItemList) {
    this.bookItemList = bookItemList;
  }

  public void addBookItem(BookItem bookItem) {
    bookItemList.add(bookItem);
  }

  public int getBookTypes() {
    return bookItemList.size();
  }
}
