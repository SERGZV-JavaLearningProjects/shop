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
import ru.shop.three_d_print.search.Search;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController
{
    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) { this.productDAO = productDAO; }

    @GetMapping("/{id}")
    public String showItem(@PathVariable int id, Model model)
    {
        Product product = productDAO.getProduct(id);
        if(product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Page not found");

        Search search = new Search();
        List<String> imageNames = search.getDirectoryFileNames("static/images/products/" + id);
        List<String> imageLinks = new ArrayList<>();

        if(imageNames.size() > 0)
        {
            for (String imageName : imageNames)
                imageLinks.add("/static/images/products/" + id + "/" + imageName);
        }
        else
        {
            imageLinks.add("/static/images/elements/no-image.jpg");
        }

        product.setImages(imageLinks);

        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(1);

        model.addAttribute("order", order);
        return "product/product";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute Order order, Model model)
    {
        System.out.println("Метод вызван");

        model.addAttribute("order", order);
        return "product/product";
    }
}
