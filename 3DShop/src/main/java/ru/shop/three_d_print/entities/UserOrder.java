package ru.shop.three_d_print.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_order")
public class UserOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private final List<Bundle> bundles = new ArrayList<>();

    public List<Bundle> getBundles() { return bundles; }

    public void setNewCountBundleItems(Long bundleProductId, int newQuantity)
    {
        var foundBundle = bundles.stream().filter(b -> b.getProductId().equals(bundleProductId)).findAny().orElse(null);
        foundBundle.setQuantity(newQuantity);
    }

    public void addBundle(Bundle bundle)
    {
        var existBundle = bundles.stream().filter(b -> b.getProductId().equals(bundle.getProductId())).findAny().orElse(null);

        if (existBundle == null) bundles.add(bundle);
        else existBundle.setQuantity(existBundle.getQuantity() + bundle.getQuantity());
    }

    public boolean deleteBundle(Long productId) { return bundles.removeIf(b -> b.getProductId().equals(productId)); }
}
