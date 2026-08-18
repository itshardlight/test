package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import test.TestDao;
import test.TestEntity;

@ManagedBean(name = "testBean")
@ViewScoped
public class TestBean {

    @EJB
    private TestDao dao;

    TestEntity entity = new TestEntity();
    List<TestEntity> listEntity = new ArrayList<>();

    
    //row part
    @PostConstruct
    public void init() {
        listEntity.add(new TestEntity());
    }

    public void addRow() {
        listEntity.add(new TestEntity());
    }

    public void removeRow(TestEntity item) {
        listEntity.remove(item);
    }

    public List<TestEntity> getItems() {
        return listEntity;
    }

    public void setItems(List<TestEntity> items) {
        this.listEntity = items;
    }

    public List<TestEntity> getShowEntity() {
        return dao.show();
    }

    public void save() {
        dao.save(entity);
        entity = new TestEntity();
    }
    
    public void saveAllrows(){
        for(TestEntity entity : listEntity){
            dao.save(entity);
        }
        listEntity.clear();
        init();
    }
  

    //getter and setter
    public TestDao getDao() {
        return dao;
    }

    public void setDao(TestDao dao) {
        this.dao = dao;
    }

    public TestEntity getEntity() {
        return entity;
    }

    public void setEntity(TestEntity entity) {
        this.entity = entity;
    }

    public List<TestEntity> getListEntity() {
        return listEntity;
    }

    public void setListEntity(List<TestEntity> listEntity) {
        this.listEntity = listEntity;
    }

}
