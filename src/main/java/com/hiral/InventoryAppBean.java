package com.hiral;

import com.hiral.entity.Inventory;
import com.hiral.interceptor.Logged;
import com.hiral.inventory.InventoryService;
//import com.hiral.inventory.SportService;
import lombok.Builder;
import lombok.Data;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Named
@Data
@Builder
public class InventoryAppBean implements Serializable {

    @Size(min = 5, max = 20)
    @NotEmpty
    private String name;

    @Size(min = 3, max = 25)
    @NotEmpty
    private String sportt;

    @Size(min = 1)
    @NotEmpty
    private int numberOfQuantity;

    @Size(min = 1)
    @NotEmpty
    private double pricePerUnit;


    @EJB
    private InventoryService inventoryService;


    public List<Inventory> getInventoryList() {

        return inventoryService.getInventoryList();
    }

    public List<Inventory> listOfInventoryy()
    {
        return inventoryService.getInventoryList();
    }


    @Logged
    public void addToList() {
        inventoryService.addToList(buidconstruct());
    }
    private Inventory buidconstruct() {
        return Inventory.builder().name(name).sportt(sportt).numberOfQuantity(numberOfQuantity).pricePerUnit(pricePerUnit).build();
    }
    private void clearFields() {

        setName("");
        setSportt("");
        setPricePerUnit(0.0);
        setNumberOfQuantity(1);
    }


}
