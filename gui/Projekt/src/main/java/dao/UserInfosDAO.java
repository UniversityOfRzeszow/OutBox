package main.java.dao;

import main.java.entity.UserInfos;
import main.java.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserInfosDAO {

    static public List<UserInfos> getUserInfos(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from UserInfos");

        List<UserInfos> listOfUsers = query.list();

        session.close();

        return listOfUsers;
    }

    static public List<UserInfos> getUserInfoByID(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("FROM UserInfos WHERE id = :id");
        query.setParameter("id",id);

        List<UserInfos> userList = query.list();

        session.close();

        return userList;
    }

    static public void addUserInfo(String name, String surname, String email, String phone_number, String street_and_number, String city, String voivodeship, String password, String role){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        UserInfos userInfo = new UserInfos();

        userInfo.setName(name);
        userInfo.setSurname(surname);
        userInfo.setPhoneNumber(phone_number);
        userInfo.setStreetAndNumber(street_and_number);
        userInfo.setCity(city);
        userInfo.setVoivodeship(voivodeship);
        session.save(userInfo);

        Users user = new Users();

        user.setUserInfoId(userInfo.getId());
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
