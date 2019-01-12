package com.dao;

import com.model.Books;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("bookDAO")
@Transactional
public class BookDAOImpl extends AppDAO implements BookDAO {


    @Override
    public Books updateBookById(int id, String name) {
        Criteria criteria = getSession().createCriteria(Books.class);
        criteria.add(Restrictions.eq("id", id));
        Books book = (Books) criteria.uniqueResult();
        book.setName_book(name);
        update(book);
        return book;
    }

    @Override
    public void addBook(Books book) {
        persist(book);
    }

    @Override
    public List<Books> allBooks() {
        Criteria criteria = getSession().createCriteria(Books.class);
        criteria.setFetchMode("purchase", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public long getBooksCount() {
        Object result = getSession().createCriteria(Books.class).setProjection(Projections.rowCount()).uniqueResult();
        long count = Long.parseLong(result.toString());
        return count;
    }


    @Override
    public Books findBookByID(int bookId) {
        Criteria criteria = getSession().createCriteria(Books.class);
        criteria.add(Restrictions.eq("id", bookId));
        return (Books) criteria.uniqueResult();
    }


    @Override
    public List<Books> getSpecialBooks() {
        Criteria criteria = getSession().createCriteria(Books.class);
        Criterion name = Restrictions.like("name_book", "%Windows%");
        Criterion price = Restrictions.gt("price", 20000.00);
        LogicalExpression orExp = Restrictions.or(name, price);
        criteria.add(orExp);
        criteria.addOrder(Order.asc("name_book"));
        criteria.addOrder(Order.desc("price"));
        return criteria.list();
    }

    //////////////////////////////////////////////////
    @Override
    public void bookByIdDelete(int id) {
        Query query = getSession().createQuery("DELETE FROM books WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List getDataAboutBougthBook() {
        String sql = "SELECT b.name_book, b.stock, b.quantity, b.price FROM purchase p, shop s, books b\n" +
                "WHERE p.book=b.id_book AND p.seller=s.idshop AND s.location_shop=b.stock AND b.quantity>10 ORDER BY b.price;";
        SQLQuery query = getSession().createSQLQuery(sql);
        return query.list();
    }
}
