package hello.proxy.app.v1;

public class OrderRepositoryV1Impl implements OrderRepositoryV1{
    @Override
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
