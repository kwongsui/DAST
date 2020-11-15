package pro.kwongsui.dast.stack;

public class Browser {
  private StackLinkedList<String> x = new StackLinkedList<>();
  private StackLinkedList<String> y = new StackLinkedList<>();

  public void open(String url) {
    x.push(url);
    y.clear();
    System.out.println("Open page: " + url);
  }

  public void back() {
    String page = x.pop();
    if (x.peek() == null) {
      System.out.println("No page to back");
      x.push(page);
      return;
    }
    System.out.println("Open page: " + x.peek());
    y.push(page);
  }

  public void forward() {
    if (y.isEmpty()) {
      System.out.println("No page to forward");
      return;
    }
    String page = y.pop();
    System.out.println("Open page: " + page);
    x.push(page);
  }

  public static void main(String[] args) {
    Browser browser = new Browser();
    browser.open("a");
    browser.back();
    browser.open("b");
    browser.back();
    browser.open("c");
    browser.back();
    browser.back();
    browser.forward();
    browser.open("d");
    browser.back();
    browser.forward();
    browser.forward();
    System.out.println();
  }
}
