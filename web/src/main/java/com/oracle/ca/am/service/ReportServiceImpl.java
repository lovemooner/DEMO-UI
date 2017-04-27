package com.oracle.ca.am.service;

import com.oracle.ca.am.dao.ReportDAO;
import com.oracle.ca.am.model.PBC;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
@Component
public class ReportServiceImpl implements ReportService {
    private final int LIMIT = 10;

    @Autowired
    private ReportDAO dao;


    public List<String> getAllWorkSpaces() {
        return dao.getAllWorkSpaces();
    }

    public List<PBC> getPBC(String workspace,String project,String errorFile,int start, int limit) {
        limit = limit == 0 ? LIMIT : limit;
        List<Object[]> list = dao.getPBC(workspace,project,errorFile,start, limit);
        List<PBC> pbcs = new ArrayList<PBC>();
        for (Object[] data : list) {
            PBC pbc = new PBC();
            pbc.setBugNum(data[0] != null ? Long.parseLong(data[0].toString()) : null);
            pbc.setWorkspace(data[1] != null ? data[1].toString() : null);
            pbc.setProject(data[2] != null ? data[2].toString() : null);
            pbc.setErrorFile(data[3] != null ? data[3].toString() : null);
            pbc.setValidationDate(data[4].toString());
            pbc.setCoord(data[5] != null ? data[5].toString() : null);
            pbc.setErrorText(data[6] != null ? data[6].toString() : null);
            pbcs.add(pbc);
        }
        return pbcs;
    }

    public int countPBC(String workspace,String project,String errorFile) {
        return dao.countPBC(workspace,project,errorFile);
    }

    public List<String> getProjects(String workspace) {
        if (StringUtils.isEmpty(workspace)) {
            return null;
        }
        return dao.getProjects(workspace);
    }

    public List<String> getErrorFiles(String workspace, String project, int start, int limit) {
        limit = limit == 0 ? LIMIT : limit;
        return dao.getErrorFiles(workspace, project, start, limit);
    }
}