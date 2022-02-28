package ru.shop.three_d_print.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.shop.three_d_print.dao.ProductDAO;
import ru.shop.three_d_print.models.Order;
import ru.shop.three_d_print.models.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController
{
    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) { this.productDAO = productDAO; }

    // Путь к изображениям на сервере
    // "/E:/Other Files/Development/Java Server/shopRepository/3DShop/target/shop/WEB-INF/classes/images"
    // /E:/Other Files/Development/Java Server/shopRepository/3DShop/target/shop/WEB-INF/classes/images/products/1/

    @GetMapping("/{id}")
    public String showItem(@PathVariable int id, Model model)
    {
        // Новый тестовый код
        System.out.println("До !!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        Product testProduct = productDAO.getProduct(id);
        if(testProduct == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Page not found");

//        readFile("images/products/" + id);
        List<String> imageNames = getDirectoryFileNames("images/products/" + id);

        System.out.println("После !!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        //....

        // Старый код
        Product product = new Product();
        product.setName("ABS plastic 1,75 SEM blue");
        product.setDescription
        (
            "The domestic trademark SEM presents to your attention a classic plastic for 3D printing" +
            " - ABS plastic 1.75 SEM blue. This is a practical and affordable material that" +
            " can be used to print various designs, body parts, souvenirs and medical supplies," +
            " car parts and various devices.\n" +
            "\n" +
            "This plastic is a universal consumable material that can be used in almost all 3D" +
            " printers that print using FDM technology, both expensive and quite budget models.\n" +
            "\n" +
            "The blue ABS plastic SEM is characterized by high impact and heat resistance."
        );

        product.setPrice(19.25f);

        List<String> imageLinks = new ArrayList<>();
//        imageLinks.add("/E:/Other Files/Development/Java Server/shopRepository/3DShop/target/shop/WEB-INF/classes/images/products/1/1.jpg");
        imageLinks.add("/resources/static/images/products/1/1.jpg");
        imageLinks.add("/resources/static/images/products/1/korobka.png");
        imageLinks.add("/resources/static/images/products/1/primer-plastika-sem-cvet-sinij.jpg");

        product.setImages(imageLinks);

        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(1);

        model.addAttribute("order", order);
        return "product/product";
        //
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute Order order, Model model)
    {
        System.out.println("Метод вызван");

        model.addAttribute("order", order);
        return "product/product";
    }

//    private void readFile(String fileName)
//    {
//        System.out.println("Запускаем метод readFile");
//        Class testClass = getClass();
//        URL url = testClass.getClassLoader().getResource(fileName);
//        String stringPath = url.getPath().replace("%20", " ");
//        File file = new File(stringPath);
//        Path path = Paths.get(file.getPath());
//
//        if(Files.exists(path))
//        {
//            System.out.println("Путь существует");
//            getDirectoryFileNames(file);
//        }
//        else
//        {
//            System.out.println("Путь не существует");
//        }
//    }

    public List<String> getDirectoryFileNames(String clippedPath)
    {
        Class thisClass = getClass();
        URL url = thisClass.getClassLoader().getResource(clippedPath);
        String correctedPath = url.getPath().replace("%20", " ");
        File folder = new File(correctedPath);
        Path path = Paths.get(folder.getPath());

        if (!Files.exists(path)) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

        File[] files = folder.listFiles();
        List<String> fileNames = new ArrayList();

        if (files != null)
        {
            for(File file : files)
            {
                System.out.println("Очередной файл был наден: " + file.getName());
                fileNames.add(file.getName());
            }
        }

        return fileNames;
    }

//    public List<String> getDirectoryFileNames(final File folder)
//    {
//        File[] files = folder.listFiles();
//        List<String> fileNames = new ArrayList();
//
//        if (files != null)
//        {
//            for(File file : files)
//            {
//                if(file.isDirectory())
//                {
//                    getDirectoryFileNames(file);
//                }
//                else
//                {
//                    System.out.println("Директория или файл были наденны: " + file.getName());
//                    fileNames.add(file.getName());
//                }
//            }
//        }
//
//        return fileNames;
//    }
}
