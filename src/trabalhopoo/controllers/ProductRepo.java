package trabalhopoo.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductRepo {

    private static HashMap<Integer, Products> data = new HashMap<>();
    private static final String INVENTORY_FOLDER = "inventory/";

    public void include(Products product) {

        data.put(product.code, product);

    }

    public void include(Integer code, String name, Integer quantity) {

        include(new Products(code, name, quantity));

    }

    public void change(Products product) {

        Products product2 = data.get(product.code);
        product2.name = product.name;
        product2.quantity = product.quantity;
        data.put(product2.code, product2);
    }

    public void change(Integer code, String name, Integer quantity) {
        change(new Products(code, name, quantity));
    }

    public void exclude(Integer code) {

        data.remove(code);

    }

    public Products get(Integer code) {

        return data.get(code);

    }

    public List<Products> getAll() {

        return new ArrayList<>(data.values());

    }
 
    
    public void save(String nameArq) {
        String filePath = INVENTORY_FOLDER + nameArq + ".txt";

        if (fileExists(filePath)) {
            System.out.println("Já existe um arquivo com o nome '" + nameArq + "'. Escolha outro nome.");
            return;
        }

        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objOut.writeObject(data);
            objOut.flush();
            System.out.println("Arquivo salvo com sucesso em: " + filePath);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }

    public void recover(String nameArq) {
        String filePath = INVENTORY_FOLDER + nameArq + ".txt";

        if (!fileExists(filePath)) {
            System.out.println("O arquivo '" + nameArq + "' não existe.");
            return;
        }

        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filePath))) {
            data = (HashMap<Integer, Products>) objIn.readObject();
            System.out.println("Arquivo recuperado com sucesso de: " + filePath);
        } catch (ClassNotFoundException | IOException error) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    public void deleteFileByName(String nameArq) {
        String filePath = INVENTORY_FOLDER + nameArq + ".txt";

        if (!fileExists(filePath)) {
            System.out.println("O arquivo '" + nameArq + "' não existe.");
            return;
        }

        try {
            Files.deleteIfExists(Paths.get(filePath));
            System.out.println("Arquivo deletado com sucesso: " + filePath);
        } catch (IOException e) {
            System.out.println("Erro ao deletar o arquivo: " + filePath);
        }
    }
    
    private static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private static void createInventoryFolder() {
        File folder = new File(INVENTORY_FOLDER);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Pasta 'inventory' criada com sucesso.");
            } else {
                System.out.println("Erro ao criar a pasta 'inventory'.");
            }
        }
    }

    static {
        createInventoryFolder();
    }
    
}
