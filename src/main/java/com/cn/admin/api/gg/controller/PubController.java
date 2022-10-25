package com.cn.admin.api.gg.controller;

import com.alibaba.fastjson.JSONObject;
import com.cn.admin.api.gg.dto.option.IntOption;
import com.cn.admin.api.gg.service.GroupService;
import com.cn.admin.api.gg.service.RoleService;
import com.cn.common.exception.FzlException;
import com.cn.common.utils.MyString;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/6/15 15:56
 *@Desc 公共使用
 **/
@Slf4j
@RestController
@RequestMapping("/pub")
public class PubController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private RoleService roleService;


    /**
     * @Author fengzhilong
     * @Desc 获取下拉-组信息
     * @Date 2021/6/29 16:57
     * @param
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getGroupOptions")
    public ResResult getGroupOptions() {

        List<IntOption> list = groupService.listGroupInfo();

        return ResCode.OK.setData(list);
    }

    /**
     * @Author fengzhilong 
     * @Desc 获取下拉-权限信息
     * @Date 2021/7/28 14:25
     * @param  
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getRoleOptions")
    public ResResult getRoleOptions() {

        List<IntOption> list = roleService.listRoleInfo();

        return ResCode.OK.setData(list);
    }


    /**
     * @Author fengzhilong
     * @Desc 地址转换经纬度坐标
     * @Date 2021/6/29 16:57
     * @param addrInfo 完整详细的地址信息
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/addrToCoordinate")
    public ResResult addrToCoordinate(String addrInfo) {
        String serverUrl = "http://test.base.go2click.cn";
        if (MyString.isNotEmpty(addrInfo)) {
            CloseableHttpClient httpClient = null;
            try {
                httpClient = HttpClients.createDefault();
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("addr", addrInfo));
                HttpPost httpPost = new HttpPost(serverUrl + "/v2/lbs/addr2location");
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

                log.info("[地址转经纬度坐标] ## request ->{}", params.toString());
                log.info("[地址转经纬度坐标] ## url ->{}", serverUrl + "/v2/lbs/addr2location");
                CloseableHttpResponse response = httpClient.execute(httpPost);

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String str_res = EntityUtils.toString(response.getEntity(), "utf-8");
                    log.info("[地址转经纬度坐标] ## code ->{}, ## response ->{}", statusCode, str_res);
                    JSONObject res = JSONObject.parseObject(str_res);
                    if (res.get("errno").equals(200)) {
                        Object data = res.get("data");
                        JSONObject resData = JSONObject.parseObject(data.toString());
                        JSONObject location = JSONObject.parseObject(resData.get("location").toString());
                        log.info("[地址转经纬度坐标] ## address_coordinates ->{}", location.get("lng") + "," + location.get("lat"));
                        return ResCode.OK
                                .putData("address_coordinates", location.get("lng") + "," + location.get("lat"));
                    } else {
                        return ResCode.ERROR.msg(res.get("errno") + ": " + res.get("errmsg"));
                    }
                } else {
                    throw new FzlException("请求异常");
                }

            } catch (Exception e) {
                e.printStackTrace();
                return ResCode.ERROR.msg(e.getMessage());
            } finally {
                if (httpClient != null) {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            return ResCode.ERROR.msg("缺少地址信息");
        }
    }
}
