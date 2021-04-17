package com.hiral;

import com.hiral.entity.Inventory;
import com.hiral.entity.Store;
import com.hiral.interceptor.Logged;
import com.hiral.inventory.InventoryService;
import com.hiral.inventory.StoreService;
import lombok.Builder;
import lombok.Data;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@SessionScoped
@Named
@Data
@Builder
public class StoreAppBean implements Serializable {

    private Long id;

    @Size(min = 5, max = 20)
    @NotEmpty
    private String name;

    @Size(min = 2, max = 55)
    @NotEmpty
    private String location;

    @Size(min = 2)
    @NotEmpty
    private String listOfInventory;

    @EJB
    private StoreService storeService;
    @EJB
    private InventoryService inventoryService;

    public List<Store> getStoreList() {
        return storeService.getStoreList();
    }


    @Logged
    public void addToList() {
        storeService.addToList(buidconstruct());
    }
    private Store buidconstruct() {
        return Store.builder().name(name).location(location).build();
    }

    private void clearFields() {
        setName("");
        setLocation("");
        setListOfInventory("");
    }
}
