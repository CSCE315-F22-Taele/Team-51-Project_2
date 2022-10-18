package com.involuntary.revpos.controller;

import com.involuntary.revpos.models.MenuItem;
import com.involuntary.revpos.models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.involuntary.revpos.listeners.MyListener;
import javafx.scene.input.MouseEvent;

public class MenuItemController {

    @FXML
    private Label nameLabel;

    private MenuItem menuItem;
    private MyListener myListener;

    public MenuItemController() {
    }

    @FXML
    private void click(MouseEvent mouseEvent) {
        this.myListener.onClickListener(this.menuItem);
    }

    public void setData(MenuItem menuItem, MyListener myListener) {
        try {
            this.menuItem = menuItem;
            this.myListener = myListener;
            this.nameLabel.setText(menuItem.getName());
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            e.printStackTrace(); }
    }

    public Product bun = new Product("entrees", 2011, "bun", 2.00);
    public Product lettuce = new Product("entrees", 2012, "lettuce", 0);
    public Product tomato = new Product("entrees", 2013, "tomato", 0);
    public Product pickles = new Product("entrees", 2014, "pickles", 0);
    public Product onions = new Product("entrees", 2015, "onions", 0);


}
