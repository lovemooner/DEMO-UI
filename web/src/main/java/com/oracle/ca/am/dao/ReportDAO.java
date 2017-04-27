package com.oracle.ca.am.dao;

import com.oracle.ca.am.model.PBC;
import love.moon.util.NumberUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
@Repository
public class ReportDAO extends BaseDao<PBC> {


    public ReportDAO() {
        super(ReportDAO.class);
    }

    public List<Object[]> getPBC(String workspace,String project,String errorFile,int start, int limit) {
        StringBuilder sb=new StringBuilder("SELECT bugNum,workspace,project,errorFile,validationDate,coord,errorText FROM PBC WHERE 1=1");
        if(StringUtils.isNotEmpty(workspace)){
            sb.append(" AND workspace=:workspace");
        }
        if(StringUtils.isNotEmpty(project)){
            sb.append(" AND project=:project");
        }
        if(StringUtils.isNotEmpty(errorFile)){
            sb.append(" AND errorFile=:errorFile");
        }
        Query query = getSession().createQuery(sb.toString())
                .setFirstResult(start).setMaxResults(limit);
        if(StringUtils.isNotEmpty(workspace)){
            query.setString("workspace",workspace);
        }
        if(StringUtils.isNotEmpty(project)){
            query.setString("project",project);
        }
        if(StringUtils.isNotEmpty(errorFile)){
            query.setString("errorFile",errorFile);
        }
        return query.list();
    }

    public int countPBC(String workspace,String project,String errorFile) {
        StringBuilder sb=new StringBuilder("SELECT count(workspace) FROM PBC WHERE 1=1");
        if(StringUtils.isNotEmpty(workspace)){
            sb.append(" AND workspace=:workspace");
        }
        if(StringUtils.isNotEmpty(project)){
            sb.append(" AND project=:project");
        }
        if(StringUtils.isNotEmpty(errorFile)){
            sb.append(" AND errorFile=:errorFile");
        }
        Query query = getSession().createQuery(sb.toString());
        if(StringUtils.isNotEmpty(workspace)){
            query.setString("workspace",workspace);
        }
        if(StringUtils.isNotEmpty(project)){
            query.setString("project",project);
        }
        if(StringUtils.isNotEmpty(errorFile)){
            query.setString("errorFile",errorFile);
        }
        return NumberUtil.intValue(query.uniqueResult());
    }


    public List<String> getAllWorkSpaces() {
        Query query = getSession().createQuery("SELECT DISTINCT(workspace) FROM PBC");
        List list = query.list();
        return list;
    }

    public List<String> getProjects(String workspace) {
        String sql = "SELECT DISTINCT(project) FROM PBC" + " WHERE workspace =:workspace";
        Query query = getSession().createQuery(sql)
                .setString("workspace", workspace);
        List list = query.list();
        return list;
    }

    public List<String> getErrorFiles(String workspace, String project, int start, int limit) {
        String sql = "SELECT DISTINCT(ERROR_FILE) FROM CA_FMW_12C_PBC" + " WHERE WORKSPACE =:workspace and PROJECT=:project";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setString("workspace", workspace)
                .setString("project", project)
                .setFirstResult(start * limit).setMaxResults(limit);
        List list = query.list();
        return list;
    }

}