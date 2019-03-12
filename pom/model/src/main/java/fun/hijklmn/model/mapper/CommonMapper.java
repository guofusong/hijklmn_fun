package fun.hijklmn.model.mapper;

public interface CommonMapper<T> {

	int deleteByPrimaryKey(String itemId);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(String docId);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

}
