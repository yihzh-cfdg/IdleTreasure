package edu.fzu.se.backend.mapper;
import org.apache.ibatis.annotations.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import edu.fzu.se.backend.bean.User_Relationships;
@Mapper
public interface User_RelationshipsMapper {

    @Operation(summary = "根据Like_ID和Beliked_ID查询用户关系记录")
    @Select("SELECT * FROM User_Relationships WHERE Like_ID = #{Like_ID} AND Beliked_ID = #{Beliked_ID}")
    User_Relationships selectByLikeIDAndBelikedID(@Param("Like_ID") Long Like_ID, @Param("Beliked_ID") Long Beliked_ID);

    @Operation(summary = "根据Like_ID查询用户关系记录列表")
    @Select("SELECT * FROM User_Relationships WHERE Like_ID = #{Like_ID}")
    List<User_Relationships> selectByLikeID(@Param("Like_ID") Long Like_ID);

    @Operation(summary = "根据Like_ID查询用户关系记录数量")
    @Select("SELECT COUNT(*) FROM User_Relationships WHERE Like_ID = #{Like_ID}")
    int countByLikeID(@Param("Like_ID") Long Like_ID);

    @Operation(summary = "根据Belike_ID查询用户关系记录列表")
    @Select("SELECT * FROM User_Relationships WHERE Like_ID = #{Like_ID}")
    List<User_Relationships> selectByBelikedID(@Param("Beliked_ID") Long Beliked_ID);
    
    @Operation(summary = "根据Beliked_ID查询用户关系记录数量")
    @Select("SELECT COUNT(*) FROM User_Relationships WHERE Beliked_ID = #{Beliked_ID}")
    int countByBelikedID(@Param("Beliked_ID") Long Beliked_ID);

    @Operation(summary = "插入新的用户关系记录")
    @Insert("INSERT INTO User_Relationships (Like_ID, Beliked_ID) " +
            "VALUES(#{Like_ID}, #{Beliked_ID})")
    int insertUserRelationships(User_Relationships userRelationships);

    @Operation(summary = "更新用户关系记录")
    @Update("UPDATE User_Relationships SET Like_ID = #{Like_ID}, Beliked_ID = #{Beliked_ID} " +
            "WHERE Relationship_ID = #{Relationship_ID}")
    int updateUserRelationships(User_Relationships userRelationships);

    @Operation(summary = "删除用户关系记录")
    @Delete("DELETE FROM User_Relationships WHERE Like_ID = #{Like_ID} AND Beliked_ID = #{Beliked_ID}")
    int deleteUserRelationships(@Param("Like_ID") Long Like_ID, @Param("Beliked_ID") Long Beliked_ID);




}