package com.pangpang.domain.SecKill;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by leewake on 2017/8/10 0010.
 */

@Entity
@Table(name = "sec_kill_goods")
public class SecKillGoods implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "remain_num")
    private Integer remainNum;

    @Column(name = "goods_name")
    private String goodsName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "SecKillGoods{" +
                "id='" + id + '\'' +
                ", remainNum=" + remainNum +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
