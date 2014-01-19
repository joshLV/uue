package lab.uue.biz.demo.dao;

import lab.uue.biz.demo.entity.Demo;
import lab.uue.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public interface DemoDao extends BaseDao<Demo, String> {

}