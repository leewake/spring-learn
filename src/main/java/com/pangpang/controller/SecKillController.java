package com.pangpang.controller;

import com.pangpang.domain.SecKill.SecKillGoods;
import com.pangpang.repository.SecKillGoodsRepository;
import com.pangpang.service.SecKillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by leewake on 2017/8/10 0010.
 */

@RestController
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private SecKillGoodsRepository secKillGoodsRepository;

    @Autowired
    private SecKillGoodsService secKillGoodsService;

    @Autowired
    private RestTemplate restTemplate;

    private String orderUrl = "http://127.0.0.1:8080/seckill";

    /*@RequestMapping("/buy/{consumer}/{goodsId}/{num}")
    @ResponseBody
    public String secKill(@PathVariable("consumer") String consumer,
                          @PathVariable("goodsId") String goodsId, @PathVariable("num") Integer num) throws InterruptedException {

        //查找出用户要买的商品
        SecKillGoods goods = secKillGoodsRepository.findOne(goodsId);

        //如果有这么多库存
        if (goods.getRemainNum() >= num) {
            //模拟网络延时
            Thread.sleep(1000);

            if (goods.getRemainNum() > 0) {

                //先减去库存
                //此处返回值指的是受影响的行数，当remainNum为0时，结果为0，就不执行接下来的if循环
                int tmpStock = secKillGoodsRepository.reduceStock(goodsId, num);

                if (tmpStock > 0) {
                    //保存订单
                    secKillGoodsService.generateOrder(consumer, goodsId, num);
                    return "购买成功";
                } else {
                    return "购买失败,库存不足";
                }

            } else {
                return "购买失败,库存不足";
            }

        }

        return "购买失败,库存不足";
    }*/

    /**
     * synchronized写法
     *
     * @param consumer
     * @param goodsId
     * @return
     */
    @RequestMapping("/buy/{consumer}/{goodsId}/{num}")
    public String secKill(@PathVariable("consumer") String consumer,
                          @PathVariable("goodsId") String goodsId, @PathVariable("num") Integer num) throws InterruptedException {

        synchronized (SecKillController.class) {
            //查找出用户要买的商品
            SecKillGoods goods = secKillGoodsRepository.findOne(goodsId);
            //如果有这么多库存
            if (goods.getRemainNum() >= num) {
                //模拟网络延时
                Thread.sleep(1000);
                //先减去库存
                secKillGoodsRepository.reduceStock(goodsId, num);
                //保存订单
                secKillGoodsService.generateOrder(consumer, goodsId, num);
                return "购买成功";
            }
            return "购买失败,库存不足";
        }
    }

    @RequestMapping("/simulationCocurrentTakeOrder")
    public String simulationCocurrentTakeOrder() {
        //httpClient工厂
        final SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        //开50个线程模拟并发秒杀下单
        for (int i = 0; i < 50; i++) {
            //购买人姓名
            final String consumerName = "consumer" + i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ClientHttpRequest request;
                    try {
                        URI uri = new URI(orderUrl + "/buy/" + consumerName + "/123456/1");
                        request = httpRequestFactory.createRequest(uri, HttpMethod.POST);
                        InputStream body = request.execute().getBody();
                        BufferedReader br = new BufferedReader(new InputStreamReader(body));
                        String line = "";
                        String result = "";
                        while ((line = br.readLine()) != null) {
                            result += line;//获得页面内容或返回内容
                        }
                        System.out.println(consumerName + ":" + result);

                        //RestTenplate写法
                        /*String url = orderUrl + "/buy/" + consumerName + "/123456/1";

                        String responseBody = restTemplate.postForObject(url, null, String.class);
                        if (responseBody != null){
                            System.out.println(responseBody);
                        }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        return "simulationCocurrentTakeOrder";
    }

}
