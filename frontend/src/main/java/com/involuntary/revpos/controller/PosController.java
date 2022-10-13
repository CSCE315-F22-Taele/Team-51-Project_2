package com.involuntary.revpos.controller;

import java.io.IOException;

import com.involuntary.revpos.controller.InventoryController;
import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class PosController {

    @FXML
    private VBox cart;

    HashMap<String, Product> cartProducts = new HashMap<>();

    @FXML
    public void addBlackBeanBurger() {
        if (!cartProducts.containsKey("blackbeanfillet")) {
            cartProducts.put("blackbeanfillet", new Product(1010, "black bean fillet", 5.29, 570, 1));
            cartProducts.put("bun", new Product(1011, "bun", 2.00, 200, 1));
            cartProducts.put("lettuce", new Product(1012, "lettuce", 0, 0, 1));
            cartProducts.put("tomato", new Product(1013, "tomato", 0, 0, 1));
            cartProducts.put("pickles", new Product(1014, "pickles", 0, 0, 1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, 1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, 1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, 1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, 1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, 1));
        } else {
            cartProducts.put("blackbeanfillet", new Product(1010, "black bean fillet", 5.29, 570, cartProducts.get("blackbeanfillet").getQuantity()+1));
            cartProducts.put("bun", new Product(1011, "bun", 2.00, 200, cartProducts.get("bun").getQuantity()+1));
            cartProducts.put("lettuce", new Product(1012, "lettuce", 0, 0, cartProducts.get("lettuce").getQuantity()+1));
            cartProducts.put("tomato", new Product(1013, "tomato", 0, 0, cartProducts.get("tomato").getQuantity()+1));
            cartProducts.put("pickles", new Product(1014, "pickles", 0, 0, cartProducts.get("pickles").getQuantity()+1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, cartProducts.get("mealtray").getQuantity()+1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, cartProducts.get("salt").getQuantity()+1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, cartProducts.get("pepper").getQuantity()+1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, cartProducts.get("utensils").getQuantity()+1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, cartProducts.get("napkins").getQuantity()+1));
        }

        HBox entry = new HBox();
        Label label = new Label("Black Bean Burger x" + cartProducts.get("blackbeanfillet").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        addKetchup();
        addMayo();
        addMustard();

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addBurger() {
        if (!cartProducts.containsKey("burgerfillet")) {
            cartProducts.put("burgerfillet", new Product(1008, "burger fillet", 4.49, 310, 1));
            cartProducts.put("bun", new Product(1011, "bun", 2.00, 200, 1));
            cartProducts.put("lettuce", new Product(1012, "lettuce", 0, 0, 1));
            cartProducts.put("tomato", new Product(1013, "tomato", 0, 0, 1));
            cartProducts.put("pickles", new Product(1014, "pickles", 0, 0, 1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, 1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, 1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, 1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, 1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, 1));
        } else {
            cartProducts.put("burgerfillet", new Product(1008, "burger fillet", 4.49, 310, cartProducts.get("burgerfillet").getQuantity()+1));
            cartProducts.put("bun", new Product(1011, "bun", 2.00, 200, cartProducts.get("bun").getQuantity()+1));
            cartProducts.put("lettuce", new Product(1012, "lettuce", 0, 0, cartProducts.get("lettuce").getQuantity()+1));
            cartProducts.put("tomato", new Product(1013, "tomato", 0, 0, cartProducts.get("tomato").getQuantity()+1));
            cartProducts.put("pickles", new Product(1014, "pickles", 0, 0, cartProducts.get("pickles").getQuantity()+1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, cartProducts.get("mealtray").getQuantity()+1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, cartProducts.get("salt").getQuantity()+1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, cartProducts.get("pepper").getQuantity()+1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, cartProducts.get("utensils").getQuantity()+1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, cartProducts.get("napkins").getQuantity()+1));
        }

        HBox entry = new HBox();
        Label label = new Label("Burger x" + cartProducts.get("burgerfillet").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        addKetchup();
        addMayo();
        addMustard();

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }


    @FXML
    public void addChickenBurger() {
        if (!cartProducts.containsKey("chickenfillet")) {
            cartProducts.put("chickenfillet", new Product(1009, "chicken fillet", 5.49, 310, 1));
            cartProducts.put("bun", new Product(1011, "bun", 2.00, 200, 1));
            cartProducts.put("lettuce", new Product(1012, "lettuce", 0, 0, 1));
            cartProducts.put("tomato", new Product(1013, "tomato", 0, 0, 1));
            cartProducts.put("pickles", new Product(1014, "pickles", 0, 0, 1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, 1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, 1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, 1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, 1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, 1));
        } else {
            cartProducts.put("chickenfillet", new Product(1009, "chicken fillet", 5.49, 310, cartProducts.get("chickenfillet").getQuantity()+1));
            cartProducts.put("bun", new Product(1011, "bun", 2.00, 200, cartProducts.get("bun").getQuantity()+1));
            cartProducts.put("lettuce", new Product(1012, "lettuce", 0, 0, cartProducts.get("lettuce").getQuantity()+1));
            cartProducts.put("tomato", new Product(1013, "tomato", 0, 0, cartProducts.get("tomato").getQuantity()+1));
            cartProducts.put("pickles", new Product(1014, "pickles", 0, 0, cartProducts.get("pickles").getQuantity()+1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, cartProducts.get("mealtray").getQuantity()+1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, cartProducts.get("salt").getQuantity()+1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, cartProducts.get("pepper").getQuantity()+1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, cartProducts.get("utensils").getQuantity()+1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, cartProducts.get("napkins").getQuantity()+1));
        }

        HBox entry = new HBox();
        Label label = new Label("Chicken Burger x" + cartProducts.get("chickenfillet").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        addKetchup();
        addMayo();
        addMustard();

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addChickenTenders() {
        if (!cartProducts.containsKey("tenders")) {
            cartProducts.put("tenders", new Product(1009, "chicken tenders", 1.60, 300, 1));
            //cartProducts.put("fries", new Product(1018, "fries", 2.69, 240, 1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, 1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, 1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, 1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, 1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, 1));

        } else {
            cartProducts.put("tenders", new Product(1009, "chicken tenders", 1.60, 300, cartProducts.get("tenders").getQuantity()+1));
            //cartProducts.put("fries", new Product(1018, "fries", 2.69, 240, cartProducts.get("fries").getQuantity()+1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, cartProducts.get("mealtray").getQuantity()+1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, cartProducts.get("salt").getQuantity()+1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, cartProducts.get("pepper").getQuantity()+1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, cartProducts.get("utensils").getQuantity()+1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, cartProducts.get("napkins").getQuantity()+1));

        }
        HBox entry = new HBox();
        Label label = new Label("Chicken Tenders x" + cartProducts.get("tenders").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        addKetchup();
        addFries();
    }

    @FXML
    public void addFries() {
        if (!cartProducts.containsKey("fries")) {
            cartProducts.put("fries", new Product(1018, "fries", 2.69, 240, 1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, 1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, 1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, 1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, 1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, 1));
        } else {
            cartProducts.put("fries", new Product(1018, "fries", 2.69, 240, cartProducts.get("fries").getQuantity()+1));
            cartProducts.put("mealtray", new Product(1020, "mealtray", 0, 0, 1));
            cartProducts.put("salt", new Product(1021, "salt", 0, 0, 1));
            cartProducts.put("pepper", new Product(1022, "pepper", 0, 0, 1));
            cartProducts.put("utensils", new Product(1023, "utensils", 0, 0, 1));
            cartProducts.put("napkins", new Product(1024, "napkins", 0, 0, 1));
        }
        HBox entry = new HBox();
        Label label = new Label("Fries x" + cartProducts.get("fries").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addBrisk() {
        if (!cartProducts.containsKey("brisk")) {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, 1));
            cartProducts.put("brisk", new Product(1005, "brisk", 1.45, 70, 1));
        } else {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, cartProducts.get("drinkcup").getQuantity()+1));
            cartProducts.put("brisk", new Product(1005, "brisk", 1.45, 70, cartProducts.get("brisk").getQuantity()+1));
        }

        HBox entry = new HBox();
        Label label = new Label("Brisk x" + cartProducts.get("brisk").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addGatorade() {
        if (!cartProducts.containsKey("gatorade")) {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, 1));
            cartProducts.put("gatorade", new Product(1002, "brisk", 1.45, 50, 1));
        } else {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, cartProducts.get("drinkcup").getQuantity()+1));
            cartProducts.put("gatorade", new Product(1002, "gatorade", 1.45, 50, cartProducts.get("gatorade").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Gatorade x" + cartProducts.get("gatorade").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addRootBeer() {
        if (!cartProducts.containsKey("rootbeer")) {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, 1));
            cartProducts.put("rootbeer", new Product(1002, "mug rootbeer", 1.45, 160, 1));
        } else {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, cartProducts.get("drinkcup").getQuantity()+1));
            cartProducts.put("rootbeer", new Product(1002, "mug rootbeer", 1.45, 160, cartProducts.get("rootbeer").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Root Beer x" + cartProducts.get("rootbeer").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addPepsi() {
        if (!cartProducts.containsKey("rootbeer")) {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, 1));
            cartProducts.put("pepsi", new Product(1002, "pepsi", 1.45, 280, 1));
        } else {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, cartProducts.get("drinkcup").getQuantity()+1));
            cartProducts.put("pepsi", new Product(1002, "pepsi", 1.45, 280, cartProducts.get("pepsi").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Pepsi x" + cartProducts.get("pepsi").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addDietPepsi() {
        if (!cartProducts.containsKey("dietpepsi")) {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, 1));
            cartProducts.put("dietpepsi", new Product(1002, "diet pepsi", 1.45, 0, 1));
        } else {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, cartProducts.get("drinkcup").getQuantity()+1));
            cartProducts.put("dietpepsi", new Product(1002, "diet pepsi", 1.45, 0, cartProducts.get("dietpepsi").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Diet Pepsi x" + cartProducts.get("dietpepsi").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addSierraMist() {
        if (!cartProducts.containsKey("sierramist")) {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, 1));
            cartProducts.put("sierramist", new Product(1002, "sierra mist", 1.45, 280, 1));
        } else {
            cartProducts.put("drinkcup", new Product(1006, "drink cup", 1.00, 0, cartProducts.get("drinkcup").getQuantity()+1));
            cartProducts.put("sierramist", new Product(1002, "sierra mist", 1.45, 280, cartProducts.get("sierramist").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Sierra Mist x" + cartProducts.get("sierramist").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addCaesarDressing() {
        if (!cartProducts.containsKey("caesardressing")) {
            cartProducts.put("caesardressing", new Product(1031, "caesar dressing", 0, 0, 1));
        } else {
            cartProducts.put("caesardressing", new Product(1031, "caesar dressing", 0, 0, cartProducts.get("caesardressing").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Caesar Dressing x" + cartProducts.get("caesardressing").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addGigemSauce() {
        if (!cartProducts.containsKey("gigemsauce")) {
            cartProducts.put("gigemsauce", new Product(1025, "gigem sauce", 0, 0, 1));
        } else {
            cartProducts.put("gigemsauce", new Product(1025, "gigem sauce", 0, 0, cartProducts.get("gigemsauce").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Gigem Sauce x" + cartProducts.get("gigemsauce").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addHoneyBBQ() {
        if (!cartProducts.containsKey("honeybbq")) {
            cartProducts.put("honeybbq", new Product(1030, "honey bbq", 0, 0, 1));
        } else {
            cartProducts.put("honeybbq", new Product(1030, "honey bbq", 0, 0, cartProducts.get("honeybbq").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Honey BBQ x" + cartProducts.get("honeybbq").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addKetchup() {
        if (!cartProducts.containsKey("ketchup")) {
            cartProducts.put("ketchup", new Product(1026, "ketchup", 0, 0, 1));
        } else {
            cartProducts.put("ketchup", new Product(1026, "ketchup", 0, 0, cartProducts.get("ketchup").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Ketchup x" + cartProducts.get("ketchup").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addMayo() {
        if (!cartProducts.containsKey("mayo")) {
            cartProducts.put("mayo", new Product(1028, "mayo", 0, 0, 1));
        } else {
            cartProducts.put("mayo", new Product(1028, "mayo", 0, 0, cartProducts.get("mayo").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Mayo x" + cartProducts.get("mayo").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addMustard() {
        if (!cartProducts.containsKey("mustard")) {
            cartProducts.put("mustard", new Product(1027, "mustard", 0, 0, 1));
        } else {
            cartProducts.put("mustard", new Product(1027, "mustard", 0, 0, cartProducts.get("mustard").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Mustard x" + cartProducts.get("mustard").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addRanch() {
        if (!cartProducts.containsKey("ranch")) {
            cartProducts.put("ranch", new Product(1029, "ranch", 0, 0, 1));
        } else {
            cartProducts.put("ranch", new Product(1029, "ranch", 0, 0, cartProducts.get("ranch").getQuantity()+1));
        }
        HBox entry = new HBox();
        Label label = new Label("Ranch x" + cartProducts.get("ranch").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addChocolateIceCream() {
        if (!cartProducts.containsKey("chocolateicecream")) {
            cartProducts.put("chocolateicecream", new Product(1032, "chocolate", 3.29, 342, 1));
        } else {
            cartProducts.put("chocolateicecream", new Product(1032, "chocolate", 3.29, 342, cartProducts.get("chocolateicecream").getQuantity()));
        }
        addDessertCup();
        HBox entry = new HBox();
        Label label = new Label("Chocolate Ice Cream x" + cartProducts.get("chocolateicecream").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addCookie() {
        if (!cartProducts.containsKey("cookie")) {
            cartProducts.put("cookie", new Product(1035, "cookie", 0, 0, 1));
        } else {
            cartProducts.put("cookie", new Product(1035, "cookie", 0, 0, cartProducts.get("cookie").getQuantity()+1));
        }
    }

    @FXML
    public void addCookieSandwich() {
        if (!cartProducts.containsKey("cookiesandwich")) {
            cartProducts.put("cookiesandwich", new Product(1035, "cookie sandwich", 4.69, 342, 1));
        } else {
            cartProducts.put("cookiesandwich", new Product(1035, "cookie sandwich", 4.69, 342, cartProducts.get("cookiesandwich").getQuantity()));
        }
        HBox entry = new HBox();
        Label label = new Label("Cookie Sandwich x" +cartProducts.get("cookiesandwich").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addDessertBowl() {
        HBox entry = new HBox();
        Label label = new Label("Dessert Bowl");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addDessertCup() {
        if (!cartProducts.containsKey("dessertcup")) {
            cartProducts.put("dessertcup", new Product(1036, "dessert cup", 0, 0, 1));
        } else {
            cartProducts.put("dessertcup", new Product(1036, "dessert cup", 0, 0, cartProducts.get("dessertcup").getQuantity()));
        }
    }

    @FXML
    public void addStrawberryIceCream() {
        if (!cartProducts.containsKey("strawberryicecream")) {
            cartProducts.put("strawberryicecream", new Product(1034, "strawberry", 3.29, 342, 1));
        } else {
            cartProducts.put("strawberryicecream", new Product(1034, "strawberry", 3.29, 342, cartProducts.get("strawberryicecream").getQuantity()));
        }

        addDessertCup();
        HBox entry = new HBox();
        Label label = new Label("Strawberry Ice Cream x" + cartProducts.get("strawberryicecream").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    @FXML
    public void addVanillaIceCream() {
        if (!cartProducts.containsKey("vanillaicecream")) {
            cartProducts.put("vanillaicecream", new Product(1034, "vanilla", 3.29, 342, 1));
        } else {
            cartProducts.put("vanillaicecream", new Product(1034, "vanilla", 3.29, 342, cartProducts.get("vanillaicecream").getQuantity()));
        }

        addDessertCup();
        HBox entry = new HBox();
        Label label = new Label("Vanilla Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    public void openSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/settings.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    public String getCategory(Product p) {
        String category = "";
        if (p.getId() >= 1000 && p.getId() <= 1006) {
            category = "drinks";
        } else if (p.getId() >= 1007 && p.getId() <= 1024) { //entree
            category = "entrees";
        } else if (p.getId() >= 1025 && p.getId() <= 1031) {
            category = "sauces";
        } else if (p.getId() >= 1032 && p.getId() <= 1038) {
            category = "desserts";
        } else {
            category = "error";
        }
        return category;
    }

    public boolean updateCall(String category, String id, int quantity) {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            String sql = "UPDATE " + category + " SET inventory = inventory - " + quantity + " WHERE id = " + id;
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();

            statement = dbConnection.createStatement();
            int callStatus = statement.executeUpdate(sql);
            if(callStatus != 1) {
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try { if(statement != null) statement.close(); } catch (Exception e) {};
            try { if(dbConnection != null) dbConnection.close(); } catch (Exception e) {};
        }
        return true;
    }

    public void checkout() {
        int total_cost = 0;
        for (Product item : cartProducts.values()) {
            total_cost += item.getQuantity()*item.getPrice();
            String id = String.valueOf(item.getId());
            updateCall(getCategory(item), id, item.getQuantity());
        }

        cart.getChildren().clear();
        String cost = String.valueOf(total_cost);
        cart.getChildren().add(new Label(cost));
    }
}