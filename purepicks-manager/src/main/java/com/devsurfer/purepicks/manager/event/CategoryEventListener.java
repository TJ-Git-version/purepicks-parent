package com.devsurfer.purepicks.manager.event;


import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.model.event.DeleteCategoryEvent;
import com.devsurfer.purepicks.model.event.UpdateCategoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Dev Surfer
 */
@Component
@Slf4j
public class CategoryEventListener {

    private final RedisTemplate<String, Object> redisTemplate;

    public CategoryEventListener(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @EventListener(UpdateCategoryEvent.class)
    public void updateCategoryEvent(UpdateCategoryEvent updateCategoryEvent) {
        if (updateCategoryEvent.getCategoryList() == null || updateCategoryEvent.getCategoryList().isEmpty()) {
            log.info("分类数据为空, 不更新缓存");
            return;
        }
        redisTemplate.opsForValue().set(RedisKeyConstantEnum.MANAGER_CATEGORY_TREE.getKey(), updateCategoryEvent.getCategoryList(), 7, TimeUnit.DAYS);
    }

    @EventListener(DeleteCategoryEvent.class)
    public void deleteCategoryEvent(DeleteCategoryEvent deleteCategoryEvent) {
        redisTemplate.delete(RedisKeyConstantEnum.MANAGER_CATEGORY_TREE.getKey());
    }

}
