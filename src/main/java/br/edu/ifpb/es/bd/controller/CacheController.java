package br.edu.ifpb.es.bd.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/save")
    public String saveToCache(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Valor salvo no Redis com chave: " + key;
    }

    @GetMapping("/get/{key}")
    public Object getFromCache(@PathVariable String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? value : "Chave não encontrada no cache.";
    }
}
