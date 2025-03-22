import com.devsurfer.purepicks.model.vo.h5.CategoryVo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/21 22:30
 * description TODO
 */
public class TestJson {
    private static final String content = """
            [
              {
                "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                "id": 1,
                "parentId": 0,
                "name": "家用电器",
                "imageUrl": "https://picsum.photos/100/100",
                "orderNum": 1,
                "status": 1,
                "createTime": [
                  "java.util.Date",
                  1742560972000
                ],
                "children": [
                  "java.util.ImmutableCollections$ListN",
                  [
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 6,
                      "parentId": 1,
                      "name": "电视",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 1,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 19,
                            "parentId": 6,
                            "name": "4K超高清电视",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 1,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          },
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 20,
                            "parentId": 6,
                            "name": "曲面电视",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 2,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          },
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 21,
                            "parentId": 6,
                            "name": "智能电视",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 3,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          }
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 7,
                      "parentId": 1,
                      "name": "空调",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 2,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 8,
                      "parentId": 1,
                      "name": "洗衣机",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 3,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 9,
                      "parentId": 1,
                      "name": "冰箱",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 4,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 10,
                      "parentId": 1,
                      "name": "厨卫大电",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 5,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    }
                  ]
                ]
              },
              {
                "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                "id": 2,
                "parentId": 0,
                "name": "手机通讯",
                "imageUrl": "https://picsum.photos/100/100",
                "orderNum": 2,
                "status": 1,
                "createTime": [
                  "java.util.Date",
                  1742560972000
                ],
                "children": [
                  "java.util.ImmutableCollections$ListN",
                  [
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 11,
                      "parentId": 2,
                      "name": "手机",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 1,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 12,
                      "parentId": 2,
                      "name": "平板电脑",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 2,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 22,
                            "parentId": 12,
                            "name": "5G手机",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 1,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          },
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 23,
                            "parentId": 12,
                            "name": "拍照手机",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 2,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          },
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 24,
                            "parentId": 12,
                            "name": "游戏手机",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 3,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          }
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 13,
                      "parentId": 2,
                      "name": "智能穿戴",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 3,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 14,
                      "parentId": 2,
                      "name": "手机配件",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 4,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    }
                  ]
                ]
              },
              {
                "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                "id": 3,
                "parentId": 0,
                "name": "电脑办公",
                "imageUrl": "https://picsum.photos/100/100",
                "orderNum": 3,
                "status": 1,
                "createTime": [
                  "java.util.Date",
                  1742560972000
                ],
                "children": [
                  "java.util.ImmutableCollections$ListN",
                  [
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 15,
                      "parentId": 3,
                      "name": "笔记本",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 1,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 16,
                      "parentId": 3,
                      "name": "台式机",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 2,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 25,
                            "parentId": 16,
                            "name": "轻薄本",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 1,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          },
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 26,
                            "parentId": 16,
                            "name": "游戏本",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 2,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          },
                          {
                            "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                            "id": 27,
                            "parentId": 16,
                            "name": "商务本",
                            "imageUrl": "https://picsum.photos/100/100",
                            "orderNum": 3,
                            "status": 1,
                            "createTime": [
                              "java.util.Date",
                              1742560972000
                            ],
                            "children": [
                              "java.util.ImmutableCollections$ListN",
                              [
                        
                              ]
                            ]
                          }
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 17,
                      "parentId": 3,
                      "name": "打印机",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 3,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    },
                    {
                      "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                      "id": 18,
                      "parentId": 3,
                      "name": "办公文具",
                      "imageUrl": "https://picsum.photos/100/100",
                      "orderNum": 4,
                      "status": 1,
                      "createTime": [
                        "java.util.Date",
                        1742560972000
                      ],
                      "children": [
                        "java.util.ImmutableCollections$ListN",
                        [
                        
                        ]
                      ]
                    }
                  ]
                ]
              },
              {
                "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                "id": 4,
                "parentId": 0,
                "name": "家居厨具",
                "imageUrl": "https://picsum.photos/100/100",
                "orderNum": 4,
                "status": 1,
                "createTime": [
                  "java.util.Date",
                  1742560972000
                ],
                "children": [
                  "java.util.ImmutableCollections$ListN",
                  [
                        
                  ]
                ]
              },
              {
                "@class": "com.devsurfer.purepicks.model.vo.h5.CategoryVo",
                "id": 5,
                "parentId": 0,
                "name": "服饰鞋帽",
                "imageUrl": "https://picsum.photos/100/100",
                "orderNum": 5,
                "status": 1,
                "createTime": [
                  "java.util.Date",
                  1742560972000
                ],
                "children": [
                  "java.util.ImmutableCollections$ListN",
                  [
                        
                  ]
                ]
              }
            ]
            """;

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY); // 允许所有字段可见
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY // 使用 PROPERTY
        );
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        List<CategoryVo> deserialize = jsonRedisSerializer.deserialize(content.getBytes(), List.class);
        System.out.println(deserialize);

    }
}
