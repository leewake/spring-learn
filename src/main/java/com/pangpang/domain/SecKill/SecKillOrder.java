package com.pangpang.domain.SecKill;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by leewake on 2017/8/10 0010.
 */

@Entity
@Table(name = "sec_kill_order")
public class SecKillOrder implements Serializable {

    @Id
    @GenericGenerator(name = "PKUUID", strategy = "uuid2")
    @GeneratedValue(generator = "PKUUID")
    @Column(length = 36)
    private String id;

    @Column(name = "consumer")
    private String consumer; //用户名称

    @Column(name = "goods_id")
    private String goodsId; //秒杀产品编号

    @Column(name = "num")
    private Integer num; //购买数量

    public SecKillOrder() {
    }

    public SecKillOrder(String consumer, String goodsId, Integer num) {
        this.consumer = consumer;
        this.goodsId = goodsId;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SecKillOrder{" +
                "id='" + id + '\'' +
                ", consumer='" + consumer + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", num=" + num +
                '}';
    }
}
