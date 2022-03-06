package lesson6hw;
import db.model.Products;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ExampleMain {

    public static void main( String[] args ) throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        db.model.ProductsExample example = new db.model.ProductsExample();
        Products list = productsMapper.selectByPrimaryKey(26584L);
        System.out.println(list.getTitle());


//        db.dao.CategoriesMapper categoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
//        db.model.CategoriesExample example = new db.model.CategoriesExample();
//        example.createCriteria().andIdEqualTo(800);
//        List<db.model.Categories> list = categoriesMapper.selectByExample(example);
//
//        db.model.Categories categories = new db.model.Categories();
//        categories.getId(800);
//        categories.setTitle("123");
//        categoriesMapper.updateByExample(categories, example);
//
//
//
//        System.out.println(categoriesMapper.selectByPrimaryKey(100));
//
//        System.out.println(list);
    }
}
