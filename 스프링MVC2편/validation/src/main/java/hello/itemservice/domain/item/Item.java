package hello.itemservice.domain.item;

import hello.itemservice.web.validation.form.ItemEditForm;
import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Item {

    @NotNull(groups = UpdateCheck.class)
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(ItemSaveForm form) {
        this.itemName = form.getItemName();
        this.price = form.getPrice();
        this.quantity = form.getQuantity();
    }

    public Item(ItemEditForm form) {
        this.id = form.getId();
        this.itemName = form.getItemName();
        this.price = form.getPrice();
        this.quantity = form.getQuantity();
    }
}
