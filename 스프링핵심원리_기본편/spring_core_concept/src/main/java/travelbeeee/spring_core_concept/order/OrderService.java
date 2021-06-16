package travelbeeee.spring_core_concept.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
