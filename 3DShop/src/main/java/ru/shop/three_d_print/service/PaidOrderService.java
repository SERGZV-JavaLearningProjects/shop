package ru.shop.three_d_print.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shop.three_d_print.entities.PaidBundle;
import ru.shop.three_d_print.entities.PaidOrder;
import ru.shop.three_d_print.entities.Product;
import ru.shop.three_d_print.repository.PaidOrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaidOrderService
{
    private final PaidOrderRepository paidOrderRepository;
    private final ProductService productService;

    @Autowired
    public PaidOrderService(PaidOrderRepository paidOrderRepository, ProductService productService)
    {
        this.paidOrderRepository = paidOrderRepository;
        this.productService = productService;
    }

    public void TestPaidOrder()
    {
        PaidOrder paidOrder = new PaidOrder();
        Product product1 = productService.findById(1L);
        Product product2 = productService.findById(2L);

        PaidBundle bundle1 = new PaidBundle(paidOrder, product1, 2);
        PaidBundle bundle2 = new PaidBundle(paidOrder, product2, 4);

        List<PaidBundle> bundles = new ArrayList<>();
        bundles.add(bundle1);
        bundles.add(bundle2);

        paidOrder.setOrder(50L, 28L, bundles);
        paidOrderRepository.save(paidOrder);
    }
}
