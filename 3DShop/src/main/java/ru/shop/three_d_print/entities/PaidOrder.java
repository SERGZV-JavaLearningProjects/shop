package ru.shop.three_d_print.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paid_order")
public class PaidOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long order_id;
    private Long user_id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paidOrder", cascade = CascadeType.ALL)
    private List<PaidBundle> bundles = new ArrayList<>();

    public PaidOrder(){}

    public PaidOrder(UserOrder userOrder, Long userId)
    {
        order_id = userOrder.getId();
        user_id = userId;

        for (var bundle : userOrder.getBundles())
        {
            bundles.add(new PaidBundle(this, bundle));
        }
    }

    public void setOrder(Long order_id, Long user_id, List<PaidBundle> bundles)
    {
        this.order_id = order_id;
        this.user_id = user_id;
        this.bundles = bundles;
    }

    public Long getOrder_id() { return order_id; }

    public void setOrder_id(Long order_id) { this.order_id = order_id; }

    public Long getUser_id() { return user_id; }

    public void setUser_id(Long user_id) { this.user_id = user_id; }

    public List<PaidBundle> getBundles() { return bundles; }

    public void setBundles(List<PaidBundle> bundle) { this.bundles = bundle; }
}
