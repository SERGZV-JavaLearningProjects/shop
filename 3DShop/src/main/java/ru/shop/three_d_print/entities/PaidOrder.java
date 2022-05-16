package ru.shop.three_d_print.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "paid_order")
public class PaidOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long row_id;
    private Long order_id;
    private Long user_id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paidOrder", cascade = CascadeType.ALL)
    private List<PaidBundle> bundles;

    public PaidOrder(){}

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
