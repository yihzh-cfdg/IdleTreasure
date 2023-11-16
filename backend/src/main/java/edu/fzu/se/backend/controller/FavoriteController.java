
package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Favorites;
import edu.fzu.se.backend.mapper.FavoritesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoritesMapper favoritesMapper;

    // 获取所有收藏信息
    @GetMapping
    public List<Favorites> getAll() {
        return favoritesMapper.selectAll();
    }

    // 根据收藏id进行查询
    @GetMapping("/{favoriteId}")
    public Favorites getById(@PathVariable Long favoriteId) {
        return favoritesMapper.selectById(favoriteId);
    }

    // 根据用户id进行查询
    @GetMapping("/user/{userId}")
    public List<Favorites> getByUserId(@PathVariable Long userId) {
        return favoritesMapper.selectByUserId(userId);
    }

    // 根据收藏商品的关键字进行模糊匹配查询
    @GetMapping("/keyword/{keyword}")
    public List<Favorites> getByKeyword(@PathVariable String keyword) {
        return favoritesMapper.selectByKeyword(keyword);
    }

    // 根据收藏id删除收藏
    @DeleteMapping("/{favoriteId}")
    public int deleteById(@PathVariable Long favoriteId) {
        return favoritesMapper.deleteById(favoriteId);
    }

    // 插入收藏信息
    @PostMapping
    public int insertFavorite(@RequestBody Favorites favorite) {
        return favoritesMapper.insertFavorite(favorite);
    }

    // 根据收藏id更新收藏信息
    @PutMapping("/{favoriteId}")
    public int updateById(@PathVariable Long favoriteId, @RequestBody Favorites favorite) {
        favorite.setFavorite_ID(favoriteId);
        return favoritesMapper.updateById(favorite);
    }
}
