
package edu.fzu.se.backend.controller;

import edu.fzu.se.backend.bean.Favorites;
import edu.fzu.se.backend.mapper.FavoritesMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FavoriteController", description = "收藏控制器")
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoritesMapper favoritesMapper;

    @Operation(summary = "获取所有收藏信息")
    @GetMapping
    public List<Favorites> getAll() {
        return favoritesMapper.selectAll();
    }

    @Operation(summary = "根据收藏id进行查询")
    @Parameters({
            @Parameter(name = "favoriteId", description = "收藏id", required = true)
    })
    @GetMapping("/{favoriteId}")
    public Favorites getById(@PathVariable Long favoriteId) {
        return favoritesMapper.selectById(favoriteId);
    }

    @Operation(summary = "根据用户id进行查询")
    @Parameters({
            @Parameter(name = "userId", description = "用户id", required = true)
    })
    @GetMapping("/user/{userId}")
    public List<Favorites> getByUserId(@PathVariable Long userId) {
        return favoritesMapper.selectByUserId(userId);
    }

    @Operation(summary = "根据收藏商品的关键字进行模糊匹配查询")
    @Parameters({
            @Parameter(name = "keyword", description = "关键字", required = true)
    })
    @GetMapping("/keyword/{keyword}")
    public List<Favorites> getByKeyword(@PathVariable String keyword) {
        return favoritesMapper.selectByKeyword(keyword);
    }

    @Operation(summary = "根据收藏id删除收藏")
    @Parameters({
            @Parameter(name = "favoriteId", description = "收藏id", required = true)
    })
    @DeleteMapping("/{favoriteId}")
    public int deleteById(@PathVariable Long favoriteId) {
        return favoritesMapper.deleteById(favoriteId);
    }

    @Operation(summary = "插入收藏信息")
    @Parameters({
            @Parameter(name = "favorite", description = "收藏信息", required = true)
    })
    @PostMapping
    public int insertFavorite(@RequestBody Favorites favorite) {
        return favoritesMapper.insertFavorite(favorite);
    }

    @Operation(summary = "根据收藏id更新收藏信息")
    @Parameters({
            @Parameter(name = "favoriteId", description = "收藏id", required = true),
            @Parameter(name = "favorite", description = "收藏信息", required = true)
    })
    @PutMapping("/{favoriteId}")
    public int updateById(@PathVariable Long favoriteId, @RequestBody Favorites favorite) {
        favorite.setFavorite_ID(favoriteId);
        return favoritesMapper.updateById(favorite);
    }
}
