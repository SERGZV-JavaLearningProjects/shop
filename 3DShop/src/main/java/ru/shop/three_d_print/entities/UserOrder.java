package ru.shop.three_d_print.entities;

import javax.persistence.*;
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
    private List<Bundle> bundles;

    public List<Bundle> getBundles() { return bundles; }

    public void setBundles(List<Bundle> bundles) { this.bundles = bundles; }
}
