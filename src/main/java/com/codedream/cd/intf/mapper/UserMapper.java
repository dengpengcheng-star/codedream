package com.codedream.cd.intf.mapper;


//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
//import org.apache.ibatis.annotations.Select;
//
//@Mapper
//public interface UserMapper {
//    @Select("select * from yike_user_new where id = #{id}")
//    User loadById(int id);
//
//    @Select("select * from yike_user_new where UserName = #{userName}")
//    User loadByName(String userName);
//
//    /**
//     * @Options 使返回自增的主键。注意插入的id是null
//     * @param user
//     * @return
//     */
//    @Insert("insert into yike_user_new(id,UserName,Password,AddTime,UpdateTime) " +
//            "values(null,#{userName},#{password},now(),now())")
//    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
//    int insert(User user);

//}
