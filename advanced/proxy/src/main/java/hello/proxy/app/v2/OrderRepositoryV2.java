package hello.proxy.app.v2;

public class OrderRepositoryV2 {
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int milias) {
        try {
            Thread.sleep(milias);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
