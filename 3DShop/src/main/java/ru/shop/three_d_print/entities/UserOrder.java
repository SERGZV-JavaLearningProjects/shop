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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Bundle> bundles = new ArrayList<>();

    public List<Bundle> getBundles() { return bundles; }

    public void addBundle(Bundle bundle)
    {
        var existBundle = bundles.stream().filter(b -> b.getProduct().getId().equals(bundle.getProduct().getId())).findAny().orElse(null);

        if (existBundle == null) bundles.add(bundle);
        else existBundle.setQuantity(existBundle.getQuantity() + bundle.getQuantity());
    }
}
