package com.cn.admin.api.gg.service.impl;

import cn.hutool.core.convert.Convert;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.alibaba.fastjson.JSONObject;
import com.cn.admin.api.gg.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author Fengzl
 * @Date 2022/5/30 17:02
 * @Desc
 */
@Slf4j
@Service
public class ElasticServiceImpl {

    @Resource
    private ElasticsearchClient client;


    public Boolean saveDocment() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("timo");
        userDTO.setAge(20);

        try {

            IndexResponse indexResponse = client.index(i -> i
                    .index("posts")
                    .id(Convert.toStr(userDTO.getId()))
                    .document(userDTO)
                    .refresh(Refresh.True)
            );
            Result result = indexResponse.result();
            System.out.println(JSONObject.toJSONString(result));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    public void existindex(){
        try {
            BooleanResponse exists = client.indices().exists(e -> e
                    .index("posts")
                    );
            System.out.println(exists.value());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void searchAllIndex(){

    }

    public void searchIndex(){
        try {
            SearchResponse<UserDTO> search = client.search(s -> s
                    .index("posts")
                            .query(q->q
                                    .ids(i->i.values("1"))
                            )
                    , UserDTO.class);


            for (Hit<UserDTO> hit : search.hits().hits()) {
                System.out.println(hit.source());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchDocment() {

        try {
            SearchResponse<UserDTO> search = client.search(s -> s
                    .index("posts")
                    .query(q -> q
                            .term(t -> t
                                    .field("name")
                                    .value(v -> v.stringValue("tomi")))
                    ), UserDTO.class);


            for (Hit<UserDTO> hit : search.hits().hits()) {
                System.out.println(hit.source());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
